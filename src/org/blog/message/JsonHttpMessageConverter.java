package org.blog.message;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHttpMessageConverter extends
		AbstractHttpMessageConverter<Object> {

	private static final Logger LOGGER = Logger
			.getLogger(JsonHttpMessageConverter.class);

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private ObjectMapper objectMapper = new ObjectMapper();

	private boolean prefixJson ;

	private Gson gson = new GsonBuilder().setPrettyPrinting()
			.disableHtmlEscaping().create();

	/**
	 * 构造方法 设置默认支持的MediaType为[application/json;charset=UTF-8]
	 */
	public JsonHttpMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET));
	}

	/**
	 * Set the {@code ObjectMapper} for this view. If not set, a default
	 * {@link ObjectMapper#ObjectMapper() ObjectMapper} is used.
	 * <p>
	 * Setting a custom-configured {@code ObjectMapper} is one way to take
	 * further control of the JSON serialization process. For example, an
	 * extended {@link org.codehaus.jackson.map.SerializerFactory} can be
	 * configured that provides custom serializers for specific types. The other
	 * option for refining the serialization process is to use Jackson's
	 * provided annotations on the types to be serialized, in which case a
	 * custom-configured ObjectMapper is unnecessary.
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "ObjectMapper must not be null");
		this.objectMapper = objectMapper;
	}

	/**
	 * Return the underlying {@code ObjectMapper} for this view.
	 */
	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}

	/**
	 * Indicate whether the JSON output by this view should be prefixed with
	 * "{} &&". Default is false.
	 * <p>
	 * Prefixing the JSON string in this manner is used to help prevent JSON
	 * Hijacking. The prefix renders the string syntactically invalid as a
	 * script so that it cannot be hijacked. This prefix does not affect the
	 * evaluation of JSON, but if JSON validation is performed on the string,
	 * the prefix would need to be ignored.
	 */
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		JavaType javaType = getJavaType(clazz);
		return (this.objectMapper.canDeserialize(javaType) && canRead(mediaType));
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return (this.objectMapper.canSerialize(clazz) && canWrite(mediaType));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// 当重写了canRead和canWrite方法之后就不会再使用到该方法了
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) inputMessage;
		HttpServletRequest request = serverRequest.getServletRequest();
		String requestUri = request.getRequestURI();
		String appName = request.getContextPath();
		String url = requestUri.substring(appName.length());
		try {
			String strBody = IOUtils.toString(inputMessage.getBody(), "UTF-8")
					.replaceAll("[\\n\\r]", "");
			JSONObject json = JSONObject.fromObject(strBody);
			LOGGER.info("Calling " + url + "\nRequestBody =>\n"
					+ gson.toJson(json));
			Object obj = gson.fromJson(json.toString(), clazz);
			return obj;
		} catch (Exception ex) {
			throw new HttpMessageNotReadableException(ex.getMessage());
		}
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		String response = gson.toJson(object);
		LOGGER.info("\nResponseBody =>\n" + response);

		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders()
				.getContentType());

		BufferedOutputStream out = null;
		ByteArrayInputStream in = null;

		try {
			in = new ByteArrayInputStream(
					response.getBytes(encoding.toString()));
			out = new BufferedOutputStream(outputMessage.getBody());
			byte[] buff = new byte[1024 * 512];
			int len = -1;
			while ((len = in.read(buff)) > 0) {
				out.write(buff, 0, len);
			}
			out.close();
		} catch (Exception ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: "
					+ ex.getMessage(), ex);
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}
	}

	/**
	 * Return the Jackson {@link JavaType} for the specified class.
	 * <p>
	 * The default implementation returns
	 * {@link TypeFactory#type(java.lang.reflect.Type)}, but this can be
	 * overridden in subclasses, to allow for custom generic collection
	 * handling. For instance:
	 * 
	 * <pre class="code">
	 * protected JavaType getJavaType(Class&lt;?&gt; clazz) {
	 * 	if (List.class.isAssignableFrom(clazz)) {
	 * 		return TypeFactory.collectionType(ArrayList.class, MyBean.class);
	 * 	} else {
	 * 		return super.getJavaType(clazz);
	 * 	}
	 * }
	 * </pre>
	 * 
	 * @param clazz
	 *            the class to return the java type for
	 * @return the java type
	 */
	protected JavaType getJavaType(Class<?> clazz) {
		return TypeFactory.type(clazz);
	}

	/**
	 * Determine the JSON encoding to use for the given content type.
	 * 
	 * @param contentType
	 *            the media type as requested by the caller
	 * @return the JSON encoding to use (never <code>null</code>)
	 */
	protected JsonEncoding getJsonEncoding(MediaType contentType) {
		if (contentType != null && contentType.getCharSet() != null) {
			Charset charset = contentType.getCharSet();
			for (JsonEncoding encoding : JsonEncoding.values()) {
				if (charset.name().equals(encoding.getJavaName())) {
					return encoding;
				}
			}
		}
		return JsonEncoding.UTF8;
	}

}

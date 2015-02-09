package org.blog.message;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StrHttpMessageConverter extends StringHttpMessageConverter {

	private static final Logger LOGGER = Logger
			.getLogger(StrHttpMessageConverter.class);

	private Gson gson = new GsonBuilder().setPrettyPrinting()
			.disableHtmlEscaping().create();

	@Override
	@SuppressWarnings("rawtypes")
	protected String readInternal(Class clazz, HttpInputMessage inputMessage)
			throws IOException {
		ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) inputMessage;
		HttpServletRequest request = serverRequest.getServletRequest();
		String requestUri = request.getRequestURI();
		String appName = request.getContextPath();
		String url = requestUri.substring(appName.length());
		String strBody = IOUtils.toString(inputMessage.getBody(), "UTF-8")
				.replaceAll("[\\n\\r]", "");
		JSONObject json = JSONObject.fromObject(strBody);
		LOGGER.info("Calling " + url + "\nRequestBody =>\n" + gson.toJson(json));
		return json.toString();
	}

	@Override
	protected void writeInternal(String s, HttpOutputMessage outputMessage)
			throws IOException {
		LOGGER.info("\nResponseBody =>\n" + s);
		super.writeInternal(s, outputMessage);
	}

}

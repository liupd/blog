package org.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class BaseMongoDomain implements Serializable {

	private static final long serialVersionUID = 6093709212947270133L;
	@Field
	private Date createTime;
	@Field
	private Date updateTime;
	@Field
	private String createTimeStr;
	@Field
	private String updateTimeStr;

	protected Map<String, Object> dynamicFields = null;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public Map<String, Object> getDynamicFields() {
		if (dynamicFields != null && dynamicFields.size() > 0) {
			Set<String> set = dynamicFields.keySet();
			for (Iterator<?> iterator = set.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (dynamicFields.get(key) != null
						&& dynamicFields.get(key).getClass().isArray()) {
					Object[] objArr = (Object[]) dynamicFields.get(key);
					if (objArr.length == 1) {
						dynamicFields.put(key,
								((Object[]) dynamicFields.get(key))[0]);
					}
				}
			}
		}
		return dynamicFields;
	}

	public void setDynamicFields(Map<String, Object> dynamicFields) {
		this.dynamicFields = dynamicFields;
	}

	public void setField(String fieldName, Object value) {
		if (dynamicFields == null) {
			dynamicFields = new HashMap<String, Object>();
		}
		dynamicFields.put(fieldName, value);
	}

	/**
	 * 返回动态字段值.
	 * 
	 * @param fieldName
	 *            字段名称
	 * @return 对象
	 */
	public Object getField(String fieldName) {
		if (dynamicFields == null) {
			return null;
		}
		return getDynamicFields().get(fieldName);
	}

}

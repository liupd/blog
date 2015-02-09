package org.blog.domain;

public class Photo extends BaseMongoDomain {
	private static final long serialVersionUID = 1L;
	private String _id;
	private String classifyId;
	private String fileId;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
}

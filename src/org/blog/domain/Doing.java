package org.blog.domain;

/**
 * 碎言碎语
 * @author yangfan
 *
 */
public class Doing extends BaseMongoDomain{
	private String _id;
	/**
	 * 配图
	 */
	private String fileId;
	/**
	 * 内容
	 */
	private String content;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

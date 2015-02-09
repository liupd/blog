package org.blog.domain;

public class Blog extends BaseMongoDomain {
	private static final long serialVersionUID = 1L;

	private String _id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 分类
	 */
	private String classifyId;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 浏览量
	 */
	private int viewCount = 0;
	/**
	 * 评论数
	 */
	private int commentCount = 0;
	/**
	 * 展示图片
	 */
	private String fileId;

	/**
	 * 是否发表到首页
	 */
	private String isIndex;

	/**
	 * 简介
	 */
	private String introduce;
	
	public String getIntroduce() {
		return introduce.length() > 75 ? introduce.substring(0, 75) + "..."
				: introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(String isIndex) {
		this.isIndex = isIndex;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}

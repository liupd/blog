package org.blog.domain;

import org.blog.domain.BaseMongoDomain;

/**
 * 博客分类
 * @author yangfan
 *
 */
public class Classify extends BaseMongoDomain {
	
	private static final long serialVersionUID = 1L;
	private String _id;
	private String classifyName;
	
	// 是否前台展示
	private String isDisplay = "0";
	private int blogCount = 0;
	
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	public int getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
}

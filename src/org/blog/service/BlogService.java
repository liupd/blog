package org.blog.service;

import java.util.List;

import org.blog.domain.Blog;
import org.blog.domain.Pagination;

/**
 * 博客
 * @author yangfan
 */
public interface BlogService {
	/**
	 * 插入博客
	 * @param blog
	 */
	void insertBlog(Blog blog);
	/**
	 * 分页查询
	 * @param pagination
	 * @param blog
	 * @return
	 */
	List<Blog> getBlogs(Pagination pagination , Blog blog ,String sort); 
	/**
	 * 更新
	 * @param blog
	 */
	void updateBlog(Blog blog);
	
	/**
	 * 更新推荐
	 * @param blog
	 */
	void updateBlog(String _id , String stauts);
	
	/**
	 * 删除博客
	 * @param blogId
	 */
	void deleteBlog(String blogId);
	/**
	 * 预览
	 * @param blogId
	 * @return
	 */
	Blog selectBlog(String blogId);
	
	void updateViewCount(String blogId);
	
	/**
	 * 更新评论数量
	 * @param blogId
	 */
	void updateCommentCount(String blogId,String method);
	
	/**
	 * 查询上一条和下一条博客
	 * @param blog
	 * @param method
	 * @return
	 */
	Blog getNextOrPreviousBlog(Blog blog , String method);
	
	/**
	 * 删除博客展示图
	 * @param fileId
	 */
	void deleteBlogPhoto(String fileId);
}

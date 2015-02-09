package org.blog.service;

import java.util.List;

import org.blog.domain.Comment;
import org.blog.domain.Pagination;


public interface CommentService {
	/**
	 * 添加
	 */
	void insertComment(Comment comment);
	/**
	 * 查询
	 * @param pagination
	 * @param comment
	 * @return
	 */
	List<Comment> getComments(Pagination pagination ,Comment comment);
	
	void deleteComment(String commentId);
	
	Comment selectComment(String commentId);
}

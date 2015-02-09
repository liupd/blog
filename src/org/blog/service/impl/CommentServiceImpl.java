package org.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.blog.dao.MongoDao;
import org.blog.domain.Comment;
import org.blog.domain.Pagination;
import org.blog.service.CommentService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component("commentService")
public class CommentServiceImpl implements CommentService {
	@Resource
	public MongoDao mongodbDao;

	@Override
	public void insertComment(Comment comment) {
		mongodbDao.insert(comment);
	}

	@Override
	public List<Comment> getComments(Pagination pagination, Comment comment) {
		Query query = new Query();
		if (comment.get_id() != null && !comment.get_id().isEmpty()) {
			query.addCriteria(Criteria.where("_id").is(comment.get_id()));
		}
		if (comment.getBlogId() != null && !comment.getBlogId().isEmpty()) {
			query.addCriteria(Criteria.where("blogId").is(comment.getBlogId()));
		}
		query.sort().on("createTime", Order.ASCENDING);
		return mongodbDao.findPaginationSort(Comment.class, query, pagination);
	}

	@Override
	public void deleteComment(String commentId) {
		mongodbDao.delete(Query.query(Criteria.where("_id").is(commentId)),
				Comment.class);
	}

	@Override
	public Comment selectComment(String commentId) {
		return mongodbDao
				.findOne(Query.query(Criteria.where("_id").is(commentId)),
						Comment.class);
	}
}

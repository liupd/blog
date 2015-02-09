package org.blog.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.blog.dao.MongoDao;
import org.blog.domain.Blog;
import org.blog.domain.Pagination;
import org.blog.service.BlogService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource
	public MongoDao mongodbDao;
	
	@Override
	public void insertBlog(Blog blog) {
		mongodbDao.insert(blog);
	}

	@Override
	public List<Blog> getBlogs(Pagination pagination, Blog blog , String sort) {
		Query query = new Query();
		if(blog.get_id() != null && !blog.get_id().isEmpty()){
			query.addCriteria(Criteria.where("_id").is(blog.get_id()));
		}
		if(blog.getClassifyId() != null && !blog.getClassifyId().isEmpty()){
			query.addCriteria(Criteria.where("classifyId").is(blog.getClassifyId()));
		}
		if(blog.getTitle() != null){
			query.addCriteria(Criteria.where("title").regex(Pattern.compile(".*?"+blog.getTitle()+".*")));
		}
		if(blog.getKeywords() != null && !blog.getKeywords().isEmpty()){
			query.addCriteria(Criteria.where("keywords").is(blog.getKeywords()));
		}
		if(blog.getIsIndex() != null && !blog.getIsIndex().isEmpty()){
			query.addCriteria(Criteria.where("isIndex").is(blog.getIsIndex()));
		}
		query.sort().on(sort, Order.DESCENDING);
		return mongodbDao.findPaginationSort(Blog.class, query, pagination);
	}

	@Override
	public void updateBlog(Blog blog) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(blog.get_id()));
		Update update = new Update();
		update.set("title", blog.getTitle());
		update.set("keywords",blog.getKeywords());
		update.set("classifyId", blog.getClassifyId());
		update.set("content", blog.getContent());
		mongodbDao.updateMulti(query, update, Blog.class);
	}
	
	@Override
	public void updateBlog(String _id, String stauts) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(_id));
		Update update = new Update();
		update.set("isIndex", stauts);
		mongodbDao.updateMulti(query, update, Blog.class);
	}
	@Override
	public void deleteBlog(String blogId) {
		Query query = Query.query(Criteria.where("_id").is(blogId));
		mongodbDao.delete(query,Blog.class);
	}

	@Override
	public Blog selectBlog(String blogId) {
		Query query = Query.query(Criteria.where("_id").is(blogId));
		return mongodbDao.findOne(query, Blog.class);
	}
	
	@Override
	public void updateViewCount(String blogId) {
		Query query = Query.query(Criteria.where("_id").is(blogId));
		Blog blog = this.selectBlog(blogId);
		Update update = new Update();
		update.set("viewCount", blog.getViewCount()+1);
		mongodbDao.updateMulti(query, update, Blog.class);
	}
	
	@Override
	public void updateCommentCount(String blogId, String method) {
		Query query = Query.query(Criteria.where("_id").is(blogId));
		Blog blog = this.selectBlog(blogId);
		Update update = new Update();
		if("add".equals(method)){
			update.set("commentCount", blog.getCommentCount()+1);
		}else{
			update.set("commentCount", blog.getCommentCount()-1);
		}
		mongodbDao.updateMulti(query, update, Blog.class);		
	}
	@Override
	public Blog getNextOrPreviousBlog(Blog blog, String method) {
		Query query ;
		if("next".equals(method)){
			 query = Query.query(Criteria.where("createTime").lt(blog.getCreateTime()));
		}else{
			query = Query.query(Criteria.where("createTime").gt(blog.getCreateTime()));
		}
		query.sort().on("createTime", Order.DESCENDING);
		return this.mongodbDao.findOne(query, Blog.class);
	}
	@Override
	public void deleteBlogPhoto(String fileId) {
		this.mongodbDao.removeFile(fileId);
	}
}

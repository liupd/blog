package org.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.blog.dao.MongoDao;
import org.blog.domain.Classify;
import org.blog.domain.Pagination;
import org.blog.service.ClassifyService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component("classifyService")
public class ClassifyServiceImpl implements ClassifyService {
	
	@Resource
	public MongoDao mongodbDao;
	
	@Override
	public void insertClassify(Classify classify) {
		mongodbDao.insert(classify);
	}

	@Override
	public List<Classify> getClassifys(Pagination pagination,String isDisplay) {
		Query query = new Query();
		if(isDisplay != null){
			query.addCriteria(Criteria.where("isDisplay").is(isDisplay));
		}
		return mongodbDao.findPaginationSort(Classify.class, query , pagination);
	}

	@Override
	public void updateClassify(Classify classify) {
		Update update = new Update();
		update.set("classifyName", classify.getClassifyName());
		update.set("isDisplay", classify.getIsDisplay());
		update.set("blogCount", classify.getBlogCount());
		mongodbDao.updateMulti(Query.query(Criteria.where("_id").is(classify.get_id()))
				, update, Classify.class);
	}

	@Override
	public void deleteClassify(String classifyId) {
		Query query = Query.query(Criteria.where("_id").is(classifyId));
		mongodbDao.delete(query,Classify.class);
	}
	@Override
	public Classify selectOne(String classifyId) {
		Query query = Query.query(Criteria.where("_id").is(classifyId));
		return mongodbDao.findOne(query, Classify.class);
	}
	
	@Override
	public void updateBlogCount(String classifyId,String method) {
		Query query = Query.query(Criteria.where("_id").is(classifyId));
		Classify classify = mongodbDao.findOne(query, Classify.class);
		Update update = new Update();
		if("add".equals(method)){
			update.set("blogCount", classify.getBlogCount()+1);
		}else{
			update.set("blogCount", classify.getBlogCount()-1);
		}
		mongodbDao.updateMulti(query, update, Classify.class);
	}
}

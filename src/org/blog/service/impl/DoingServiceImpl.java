package org.blog.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.blog.dao.MongoDao;
import org.blog.domain.Doing;
import org.blog.domain.Pagination;
import org.blog.service.DoingService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component("doingService")
public class DoingServiceImpl implements DoingService {
	@Resource
	public MongoDao mongodbDao;

	@Override
	public void insertDoing(Doing doing) {
		this.mongodbDao.save(doing);
	}

	@Override
	public List<Doing> getDoings(Doing doing, Pagination pagination) {
		Query query = new Query();
		if (doing.getContent() != null && !doing.getContent().isEmpty()) {
			query.addCriteria(Criteria.where("content").regex(
					Pattern.compile(".*?" + doing.getContent() + ".*")));
		}
		query.sort().on("createTime", Order.DESCENDING);
		return this.mongodbDao.findPaginationSort(Doing.class, query,
				pagination);
	}

	@Override
	public void updateDoing(Doing doing) {
		Update update = new Update();
		update.set("content", doing.getContent());
		this.mongodbDao.updateMulti(
				Query.query(Criteria.where("_id").is(doing.get_id())), update,
				Doing.class);
	}

	@Override
	public void deleteDoing(String doingId) {
		this.mongodbDao.delete(Query.query(Criteria.where("_id").is(doingId)),
				Doing.class);
	}

	@Override
	public Doing selectOne(String doingId) {
		return this.mongodbDao.findOne(
				Query.query(Criteria.where("_id").is(doingId)), Doing.class);
	}
}

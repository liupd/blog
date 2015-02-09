package org.blog.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.blog.dao.MongoDao;
import org.blog.domain.BaseMongoDomain;
import org.blog.domain.Pagination;
import org.blog.utils.DateUtils;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Component("mongodbDao")
public class MongoDaoImpl implements MongoDao {

	private static final Logger LOGGER = Logger.getLogger(MongoDaoImpl.class);

	private static final String PHOTO_NAME = "photo";

	@Resource
	public MongoOperations mongoOperations;
	@Resource
	public MongoDbFactory mongoDbFactory;

	@Override
	public <T extends BaseMongoDomain> void insert(T entity) {
		Date date = new Date();
		entity.setCreateTime(date);
		entity.setUpdateTime(date);
		entity.setCreateTimeStr(DateUtils.getDateString(date));
		entity.setUpdateTimeStr(DateUtils.getDateString(date));
		mongoOperations.insert(entity);
	}

	@Override
	public <T extends BaseMongoDomain> void save(T entity) {
		Date date = new Date();
		entity.setCreateTime(date);
		entity.setUpdateTime(date);
		entity.setCreateTimeStr(DateUtils.getDateString(date));
		entity.setUpdateTimeStr(DateUtils.getDateString(date));
		mongoOperations.save(entity);
	}

	@Override
	public <T> void insertAll(Collection<? extends Object> objects) {
		mongoOperations.insertAll(objects);
	}

	@Override
	public <T> long count(Query query, Class<T> entity) {
		return mongoOperations.count(query, entity);
	}

	@Override
	public <T> void delete(Query query, Class<T> entity) {
		mongoOperations.remove(query, entity);
	}

	@Override
	public <T> Boolean updateMulti(Query query, Update update, Class<T> entity) {
		WriteResult result = mongoOperations.updateMulti(query, update, entity);
		return StringUtils.isEmpty(result.getError());
	}

	@Override
	public <T> T findOne(Query query, Class<T> entity) {
		return mongoOperations.findOne(query, entity);
	}

	@Override
	public <T> List<T> findPaginationSort(Class<T> entity, Query query,
			Pagination page) {
		List<T> resultList = null;

		if (page == null) {
			page = new Pagination();
		}

		Long count = this.count(query, entity);
		int currentPage = page.getCurPage();
		int pageNum = page.getNumPerPage();
		long tempNum = count / pageNum;
		tempNum = count % pageNum > 0 ? (tempNum + 1) : tempNum;
		if (tempNum < currentPage) {
			currentPage = Integer.parseInt(String.valueOf(tempNum));
			page.setCurPage(currentPage);
		}
		query.skip((currentPage - 1) * pageNum);
		query.limit(pageNum);
		page.setTotalCount(count.intValue());
		resultList = this.mongoOperations.find(query, entity);
		return resultList;
	}

	@Override
	public <T extends File> void saveFile(String fileId, byte[] file,
			String contentType) {
		GridFSInputFile gfsInput;
		gfsInput = new GridFS(this.mongoDbFactory.getDb(), PHOTO_NAME)
				.createFile(file);
		gfsInput.setFilename(fileId);
		gfsInput.setContentType(contentType);
		gfsInput.save();
	}

	@Override
	public GridFSDBFile getFile(String fileId) {
		try {
			// 获取fs的根节点
			GridFS gridFS = new GridFS(this.mongoDbFactory.getDb(), PHOTO_NAME);
			GridFSDBFile dbfile = gridFS.findOne(fileId);
			if (dbfile != null) {
				return dbfile;
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	@Override
	public void removeFile(String fileId) {
		GridFS gridFS = new GridFS(this.mongoDbFactory.getDb(), PHOTO_NAME);
		gridFS.remove(gridFS.findOne(fileId));
	}
}

package org.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.blog.dao.MongoDao;
import org.blog.domain.Pagination;
import org.blog.domain.Photo;
import org.blog.service.PhotoService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component("photoService")
public class PhotoServiceImpl implements PhotoService {

	@Resource
	public MongoDao mongodbDao;

	@Override
	public void insertPhoto(Photo photo) {
		this.mongodbDao.insert(photo);
	}

	@Override
	public List<Photo> getPhotos(Photo photo, Pagination pagination) {
		Query query = new Query();
		if (photo.getClassifyId() != null) {
			query.addCriteria(Criteria.where("classifyId").is(
					photo.getClassifyId()));
		}
		return mongodbDao.findPaginationSort(Photo.class, query, pagination);
	}

	@Override
	public void updatePhotos(Photo photo) {
		Update update = new Update();
		update.set("classifyId", photo.getClassifyId());
		mongodbDao.updateMulti(
				Query.query(Criteria.where("fileId").is(photo.getFileId())),
				update, Photo.class);
	}

	@Override
	public void deletePhoto(String photoId) {
		mongodbDao.delete(Query.query(Criteria.where("_id").is(photoId)),
				Photo.class);
	}

	@Override
	public Photo selectOne(String _id) {
		return mongodbDao.findOne(Query.query(Criteria.where("_id").is(_id)),
				Photo.class);
	}
}

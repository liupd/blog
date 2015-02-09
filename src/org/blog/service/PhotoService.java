package org.blog.service;

import java.util.List;

import org.blog.domain.Pagination;
import org.blog.domain.Photo;

public interface PhotoService {
	/**
	 * 插入
	 * @param photo
	 */
	void insertPhoto(Photo photo);
	/**
	 * 展示
	 * @param pagination
	 * @return
	 */
	List<Photo> getPhotos(Photo photo , Pagination pagination);
	/**
	 * 修改
	 * @param photo
	 */
	void updatePhotos(Photo photo);
	/**
	 * 删除
	 * @param photoId
	 */
	void deletePhoto(String photoId);
}

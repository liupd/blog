package org.blog.dao;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.blog.domain.BaseMongoDomain;
import org.blog.domain.Pagination;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * mongodb操作
 * @author yangfan
 *
 */
public interface MongoDao {
	/**
	 * 插入
	 * @param entity
	 */
	<T extends BaseMongoDomain> void insert(T entity);
	/**
	 * 单条数据保存或者更新
	 * @param entity
	 */
	<T extends BaseMongoDomain> void save(T entity);
	
	/**
	 * 批量保存
	 * @param Objects
	 */
	<T> void insertAll(Collection<? extends Object> objects);
	
	/**
	 * 计算数量
	 * @param query
	 * @param entity
	 * @return
	 */
	<T> long count(Query query,Class<T> entity);
	
	/**
	 * 根据条件删除
	 * @param query
	 * @param entuty
	 */
	<T> void delete(Query query,Class<T> entity);
	
	/**
	 * 根据条件批量更新
	 * @param query
	 * @param update
	 * @param entity
	 * @return
	 */
	<T> Boolean updateMulti(Query query ,Update update ,Class<T> entity);
	
	/**
	 * 单个查询
	 * @param query
	 * @param entity
	 * @return
	 */
	<T> T findOne(Query query , Class<T> entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @param query
	 * @param page
	 * @return
	 */
	<T> List<T> findPaginationSort(Class<T> entity , Query query , Pagination page) ;
	
	/**
	 * 文件存储
	 * @param fileId
	 * @param file
	 */
	<T extends File> void saveFile(String fileId, byte[] file ,String contentType);
	
	/**
	 * 取文件
	 * @param fileId
	 * @return
	 */
	GridFSDBFile getFile(String fileId) ;
	
	/**
	 * 删除文件
	 * @param fileId
	 */
	void removeFile(String fileId);
}

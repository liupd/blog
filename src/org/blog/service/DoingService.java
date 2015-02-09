package org.blog.service;

import java.util.List;

import org.blog.domain.Doing;
import org.blog.domain.Pagination;
import org.springframework.data.mongodb.core.query.Query;

public interface DoingService {
	/**
	 * 插入
	 * @param doing
	 */
	void insertDoing(Doing doing);
	/**
	 * 展示
	 * @param pagination
	 * @return
	 */
	List<Doing> getDoings(Doing doing , Pagination pagination);
	/**
	 * 修改
	 * @param doing
	 */
	void updateDoing(Doing doing);
	/**
	 * 删除
	 * @param doingId
	 */
	void deleteDoing(String doingId);
	
	Doing selectOne(String doingId);
	
}

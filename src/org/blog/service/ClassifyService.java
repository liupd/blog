package org.blog.service;

import java.util.List;

import org.blog.domain.Classify;
import org.blog.domain.Pagination;

/**
 * 博客分类
 * @author yangfan
 *
 */
public interface ClassifyService {
	
	/**
	 * 新建文章分类
	 * @param classify
	 */
	void insertClassify(Classify classify);
	
	/**
	 * 展示文章分类
	 * @return
	 */
	List<Classify> getClassifys(Pagination pagination,String isDisplay);
	
	/**
	 * 修改
	 * @param classify
	 */
	void updateClassify(Classify classify);
	
	/**
	 * 删除
	 * @param classidy
	 */
	void deleteClassify(String classifyId);
	
	/**
	 * 根据id查询分类
	 * @param _id
	 * @return
	 */
	Classify selectOne(String classifyId);
	
	/**
	 * 更新分类数量
	 * @param classifyId
	 */
	void updateBlogCount(String classifyId,String method);
	
}

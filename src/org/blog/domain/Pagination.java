package org.blog.domain;

import java.io.Serializable;
import java.util.List;

import org.blog.utils.CollectionUtils;


public final class Pagination implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer curPage = 1; // 当前页
	
	private Integer numPerPage = 10; // 每页数量
	
	private Integer totalCount = 0; // 总数
	
	private Integer totalPage = 0; // 总页数
	
//	private int[] pagesGroup = new int[5]; // 分页
	private List<Integer> pagesGroup = null; // 分页
	
	public List<Integer> getPagesGroup() {
		return pagesGroup;
	}

	public void setPagesGroup(List<Integer> pagesGroup) {
		this.pagesGroup = pagesGroup;
	}

	public Integer getFirstRow() {
		return ((getCurPage()-1) * getNumPerPage()) + 1;
	}
	
	public Integer getLastRow() {
		Integer lastRow = getCurPage() * getNumPerPage();
		return (lastRow > getTotalCount()) ? getTotalCount() : lastRow;
	}
	
	public List<Integer> getPageGroup() {
		int mod = getCurPage()%5;
		int basePage = -1;
		if (mod != 0) {
			basePage = (getCurPage()/5)*5;
		} else {
			basePage = (getCurPage()/5 - 1) * 5;
		}
		List<Integer> pageGroup = CollectionUtils.newArrayList();
		int num = getTotalPage() - basePage;
		num = num >=5 ? 5 : num;
		for (int i = 1; i <= num; i++) {
			pageGroup.add(basePage + i);
		}
		return pageGroup;
	}

	public Integer getCurPage() {
		if (curPage <= 0) {
			curPage = 1;
		} else if (curPage > getTotalPage() && getTotalPage() > 0) {
			curPage = totalPage;
		}
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getNumPerPage() {
		if (numPerPage <= 0)
			numPerPage = 10;
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		if (totalCount == 0) {
			totalPage = 0;
		} else {
			if (totalCount%numPerPage == 0) {
				totalPage = (totalCount / numPerPage);
			} else {
				totalPage = (totalCount / numPerPage) + 1;
			}
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}

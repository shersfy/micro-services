package org.shersfy.user.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 * @param <T>
 */
public class Page<T> {
	
	/**
	 * 每页大小
	 */
	private int pageSize;
	/**
	 * 总条数
	 */
	private long totalCount;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 当前页左边显示currentPageLeftLength页
	 */
	private int currentPageLeftLength;
	/**
	 * 当前页右边显示currentPageRightLength页
	 */
	private int currentPageRightLength;
	/**
	 * 每页数据
	 */
	private List<T> data;

	public Page() {
		currentPageLeftLength = 2;
		currentPageRightLength = 2;
		currentPage = 1;
		pageSize = Integer.MAX_VALUE;
	}
	
	public Page(int pageNum, int pageSize) {
		this();
		this.setCurrentPage(pageNum);
		this.setPageSize(pageSize);
	}

	/**
	 * 获取开始索引
	 * @return
	 */
	public int getStartIndex() {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * 获取结束索引
	 * @return
	 */
	public int getEndIndex() {
		return currentPage * pageSize;
	}

	/**
	 * 是否第一页
	 * @return
	 */
	public boolean isFirstPage() {
		return currentPage <= 1;
	}

	/**
	 * 是否末页
	 * @return
	 */
	public boolean isLastPage() {
		return currentPage >= getTotalPage();
	}

	/**
	 * 获取下一页页码
	 * @return
	 */
	public int getNextPage() {
		int nextPage = currentPage + 1;
		int totalPage = getTotalPage();
		if (nextPage > totalPage) {
			nextPage = totalPage;
		} 
		return nextPage;
	}

	/**
	 * 获取上一页页码
	 * @return
	 */
	public int getPreviousPage() {
		if (isFirstPage()) {
			return 1;
		}
		return currentPage - 1;
	}

	/**
	 * 获取当前页页码
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 取得总页数
	 * @return
	 */
	public int getTotalPage() {
		int totalPage = (int)totalCount / pageSize;
		if (totalCount % pageSize != 0) {
			totalPage += 1;
		}
		return totalPage;
	}

	/**
	 * 取总记录数.
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置当前页
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获取每页数据容量.
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置每页大小
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 该页是否有下一页.
	 * @return
	 */
	public boolean hasNextPage() {
		return currentPage < getTotalPage();
	}

	/**
	 * 该页是否有上一页.
	 * @return
	 */
	public boolean hasPreviousPage() {
		return currentPage > 1;
	}

	/**
	 * 获取数据集
	 * @return
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * 设置数据集
	 * @param data
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * 设置总记录条数
	 * @param totalCount
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获取当前页左边显示多少页的长度
	 * @return
	 */
	public int getCurrentPageLeftLength() {
		return currentPageLeftLength;
	}

	/**
	 * 设置当前页左边显示多少页的长度
	 * @return
	 */
	public void setCurrentPageLeftLength(int currentPageLeftLength) {
		this.currentPageLeftLength = currentPageLeftLength;
	}

	/**
	 * 获取当前页右边边显示多少页的长度
	 * @return
	 */
	public int getCurrentPageRightLength() {
		return currentPageRightLength;
	}

	/**
	 * 设置当前页右边显示多少页的长度
	 * @param currentPageRightLength
	 */
	public void setCurrentPageRightLength(int currentPageRightLength) {
		this.currentPageRightLength = currentPageRightLength;
	}
	
	/**
	 * 获取左边的页码列表
	 * @return
	 */
	public List<Integer> getLeftPageList(){
		List<Integer> pageLeftList = new ArrayList<Integer>();
		int currentPageToLeft = currentPage - currentPageLeftLength;
		if(currentPageToLeft < 1){
			currentPageToLeft = 1;
		}
		for(int i = currentPageToLeft; i < currentPage; i++){
			pageLeftList.add(i);
		}
		return pageLeftList;
	}
	
	/**
	 * 获取右边边的页码列表
	 * @return
	 */
	public List<Integer> getRightPageList(){
		List<Integer> pageRightList = new ArrayList<Integer>();
		int currentPageToRight = currentPage + currentPageRightLength + 1;
		int totalPage = getTotalPage();
		if(currentPageToRight > totalPage){
			currentPageToRight = totalPage + 1;
		}
		for(int i = currentPage + 1; i < currentPageToRight; i++){
			pageRightList.add(i);
		}
		return pageRightList;
	}
	
	/**
	 * 显示左边省略号
	 * @return
	 */
	public boolean showFirstPage(){
		boolean show = false;
		if(currentPage - currentPageLeftLength > 1){
			show = true;
		}
		return show;
	}
	
	/**
	 * 显示左边省略号
	 * @return
	 */
	public boolean showLeftPoint(){
		boolean show = false;
		if(currentPage- 2 > currentPageLeftLength){
			show = true;
		}
		return show;
	}
	
	/**
	 * 显示显示最后一页
	 * @return
	 */
	public boolean showLastPage(){
		boolean show = false;
		int totalPage = getTotalPage();
		if(currentPage + currentPageRightLength < totalPage){
			show = true;
		}
		return show;
	}
	
	/**
	 * 显示右边省略号
	 * @return
	 */
	public boolean showRightPoint(){
		boolean show = false;
		if(currentPage + currentPageRightLength < getTotalPage() - 1){
			show = true;
		}
		return show;
	}
	
	/**
	 * 设置除了data以外的其它属性
	 * 
	 * @author PengYang
	 * @date 2016-08-18
	 * 
	 * @param newp
	 * @param page
	 * @return Page
	 */
	public static <T> Page<T> getPageInstance(Class<T> clazz, Page<?> page){
		Page<T> newp = new Page<T>();
		
		newp.setCurrentPage(page.getCurrentPage());
		newp.setCurrentPageLeftLength(page.getCurrentPageLeftLength());
		newp.setCurrentPageRightLength(page.getCurrentPageRightLength());
		newp.setPageSize(page.getPageSize());
		newp.setTotalCount(page.getTotalCount());
		
		return newp;
	}
}

package com.info.util.page;

import org.springframework.data.domain.Page;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */

public class PageHelper {

	private String para=""; // 联结地址参数
	private int number; // 当前是第几页
	private int totalPages; // 一共有多少页
	private int totalElements; // 一共有多少行
	private int size; // 每页有多少行
	private int firstResult; // 开始取数位置

	private String backPage;
	private String endPage;
	private String firstPage;
	private String nextPage;

	//
	private String link;
	
	
	/**
	 * 构函数
	 */
	public PageHelper(String address, Page page) {

		// 设置当前页
		this.number = page.getNumber()+1; //因为 page 从0页开计计数，传入page的页数减了1，所以加上1
		// 设置总记录数
		this.totalElements = page.getTotalPages()*page.getSize();
		// 设置每页记录数
		this.size = page.getSize();
		// 根据总行数计算总页数
		countMaxPage();
		// 计算开始取得数据位置
		countFirstResult();

		link = address ;
	}
	
	/**
	 * 构函数
	 */
	public PageHelper(String address, String para, Page page) {

		// 设置联结地址参数
		this.para = para;
		// 设置当前页
		this.number = page.getNumber()+1; //因为 page 从0页开计计数，传入page的页数减了1，所以加上1
		// 设置总记录数
		this.totalElements = page.getTotalPages()*page.getSize();
		// 设置每页记录数
		this.size = page.getSize();
		// 根据总行数计算总页数
		countMaxPage();
		// 计算开始取得数据位置
		countFirstResult();

		link = address ;
	}
	

	/**
	 * 构函数
	 * 
	 * @param count总记录数
	 * @param pageNum每页记录数
	 * @param curPage当前页号
	 */
	public PageHelper(String address, String para, int count, int curPage, int pageNum) {
		// 设置联结地址

		// 设置联结地址参数
		this.para = para;
		// 设置当前页
		this.number = curPage;
		// 设置总记录数
		this.totalElements = count;
		// 设置每页记录数
		this.size = pageNum;
		// 根据总行数计算总页数
		countMaxPage();
		// 计算开始取得数据位置
		countFirstResult();

		link = address ;
	}

	/**
	 * 构函数
	 * 
	 * @param count总记录数
	 * @param pageNum每页记录数
	 * @param curPage当前页号
	 */
	public PageHelper(String address, int count, int curPage, int pageNum) {

		// 设置当前页
		this.number = curPage;
		// 设置总记录数
		this.totalElements = count;
		// 设置每页记录数
		this.size = pageNum;
		// 根据总行数计算总页数
		countMaxPage();
		// 计算开始取得数据位置
		countFirstResult();

		link = address;
	}

	// 根据总行数计算总页数
	private void countMaxPage() {
		if (this.totalElements % this.size == 0) {
			this.totalPages = this.totalElements / this.size;
		} else {
			this.totalPages = this.totalElements / this.size + 1;
		}
	}

	// 计算开始取得数据位置
	private void countFirstResult() {
		this.firstResult = (this.number - 1) * this.size;
	}

	/** 取得总记录数 */
	public int getCount() {
		return this.totalElements;
	}

	/** 取得总页数 */
	public int getMaxPage() {
		return this.totalPages;
	}

	/** 取得当前页 */
	public int getCurPage() {
		return this.number;
	}

	/** 取每页行数 */
	public int getPageNum() {
		return this.size;
	}

	/** 取得开始查询数据位置 */
	public int getFirstResult() {
		return this.firstResult;
	}

	public String getPara() {
		return para;
	}

	/** 取下一页联结 */
	public String getNextPage() {
		String addtrss;
		int nextValue = this.getCurPage() + 1;
		if (this.number < this.totalPages)
			addtrss = "<li><A HREF=\"" + link + "/" + nextValue+  para + "\">下一页</A></li>";
		else
			addtrss = "<li class=\"disabled\" ><A HREF=\"#\">下一页</A></li>";
		return addtrss;
	}

	/** 取上一页联结 */
	public String getBackPage() {
		String addtrss;
		int backValue = this.getCurPage() - 1;
		if (backValue > 0)
			addtrss = "<li><A HREF=\"" + link + "/" + backValue + para+ "\">上一页</A></li>";
		else
			addtrss = "<li class=\"disabled\"><A HREF=\"#\">上一页</A></li>";
		return addtrss;
	}

	/** 转到第一页 */
	public String getFirstPage() {
		String addtrss;
		int firstValue = 1;
		if (this.getCurPage() > 1)
			addtrss = "<li><A HREF=\"" + link + "/" + firstValue  + para+ "\">首页</A></li>";
		else
			addtrss = "<li class=\"disabled\"><A HREF=\"#\">首页</A></li>";
		return addtrss;

	}

	/** 转到最后一页 */
	public String getEndPage() {
		String addtrss;
		int endValue = this.totalPages;
		if (this.number < this.totalPages)
			addtrss = "<li><A HREF=\"" + link + "/" + endValue  + para+ "\">尾页</A></li>";
		else
			addtrss = "<li class=\"disabled\"><A HREF=\"#\">尾页</A></li>";
		return addtrss;

	}

	/**
	 * 显示分页联结
	 * 
	 * @param number显示联结数
	 * @return String
	 */
	public String getLinkPage(int number) {
		String str = "";

		int num;
		int pageNumber;
		if (number < 3)
			number = 3;
		num = number / 2;
		if (number > totalElements)
			number = totalElements;
		for (int i = 0; i < number; i++) {
			
				
			pageNumber = getCurPage() - num + i;
			if (getCurPage() <= num)
				pageNumber = 1 + i;
			if (getCurPage() > getMaxPage() - num)
				pageNumber = getMaxPage() - number + i + 1;
			if (pageNumber > getMaxPage() || pageNumber < 1)
				continue;
			if (this.getCurPage() != pageNumber)
				str += "<li><A HREF=\"" + link + "/" + pageNumber   + para+ "\">" + pageNumber + "</A></li>";
			else
				str += "<li class='active'><A  HREF=\"" + link + "/" + pageNumber+ para+ "\">" + pageNumber + "</A></li>";
		}
		return str;
	}

	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}

	public void setCurPage(int curPage) {
		this.number = curPage;
	}

	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public void setMaxPage(int maxPage) {
		this.totalPages = maxPage;
	}

	public void setCount(int count) {
		this.totalElements = count;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public void setPageNum(int pageNum) {
		this.size = pageNum;
	}

}

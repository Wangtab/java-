package com.lamp.utils;

public class DealPage
{


	/**
	 * 如果 当前页数 为Null
	 * 自动赋值为1 
	 * 如果 当前页小于 最小页数（通常设置为 1 即 第一页） 就把当前页设置为第一页
	 * 如果 当前页大于 最大页数 就把当前页设置为最大页
	 * 如果 传入的当前页不是 数字 就设置为第一页
	 * 
	 * @param curpage 页面中的 当前页
	 * @param maxPage 最大的页数
	 * @return
	 */
	public static int dealPage(String curpage,int maxPage)
	{
		int page = 1;
		
		if(null == curpage || curpage.length() == 0) 
			curpage = "1";

		if(maxPage < 1)
			maxPage = 1;

		try
		{
			page = Integer.parseInt(curpage);
		} catch (Exception e){}
		
		int minpage = 1;
		
		if(page < minpage) page = minpage;
		if(page > maxPage) page = maxPage;
		
		return page;
	}
}

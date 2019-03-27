package com.lamp.utils;

import java.util.List;

public class PageBean<T> {
	private int index = 1;//起始页数
	private int size;//每页显示多少条数据
	private int count;//一共有多少条数据
	@SuppressWarnings("unused")
	private int total;//有多少页
	private boolean pre;//是否有上一页
	private boolean next;//是否有下一页
	private List<T> list;
	private String hql;
	private String chql;

	public int getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		
		if(null == index)
		{
			index = 1;
		}
		this.index = index;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		
		return (int) Math.ceil(getCount() / (getSize() * 1.0));
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isPre() {
		pre = index > 1 ? true : false;
		return pre;
	}

	public void setPre(boolean pre) {
		this.pre = pre;
	}

	public boolean isNext() {
		next = index < this.getTotal() ? true : false;
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getChql()
	{
		return chql;
	}

	public void setChql(String chql)
	{
		this.chql = chql;
	}

}

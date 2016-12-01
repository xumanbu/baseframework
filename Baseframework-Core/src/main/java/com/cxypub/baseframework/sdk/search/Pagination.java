package com.cxypub.baseframework.sdk.search;

import java.io.Serializable;

/**
 * <b>类名</b>：Pagination.java<br>
 *
 * <p><b>标题</b>：品恩产品研发  </p>
 * <p><b>描述</b>：
 *
 *           描述数据库分页信息的基类
 * </p>
 * <p><b>版权声明</b>：Copyright (c) 2012</p>
 * <p><b>公司</b>：品恩科技 </p>
 * @author <font color='blue'>Administrator</font> 
 * @version 1.0
 * @date  2012-1-4 下午03:42:51
 */
public class Pagination implements Serializable {

	/** serialVersionUID */

	private static final long serialVersionUID = -6718411666719657016L;

	/**
	 * 当前第几页，从第1页开始。
	 */
	protected int pageNo = 1;

	/**
	 * 每页显示的数据个数
	 */
	protected int pageSize = 15;

	/**
	 * 构造一个Page
	 * @param pageSize 每页的记录数
	 */
	public Pagination(int pageSize) {
		setPageSize(pageSize);
	}

	/**
	 * 构造一个Page
	 * @param pageSize 每页的记录数
	 * @param pageNo 从第几页开始显示，默认从第1页开始。
	 */
	public Pagination(int pageSize, int pageNo) {
		setPageSize(pageSize);
		setPageNo(pageNo);
	}

	/**
	 * @description 获得当前页的页号,序号从1开始,默认为1.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 当前页数
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @description 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 * @date 2010-8-13
	 * @author liuhh 
	 * @param pageNo  前页的页号
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Pagination pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * @description 获得每页的记录数量,默认为1.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 记录数量
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @description 设置每页的记录数量,低于1时自动调整为1.
	 * @date 2010-8-13
	 * @author liuhh
	 * @param pageSize 每页数量
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Pagination pageSize(final int thePageSize) {
		setPageSize(thePageSize);

		return this;
	}

	/**
	 * @description 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 所在位置号
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}
}

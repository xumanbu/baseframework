package com.cxypub.baseframework.sdk.search;

/**
 * <p> Title: 智能营区综合管控系统-Page.java </p>
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-10-24 下午2:48:28
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description  描述数据库分页信息的Java类
 * 
 * @author liuhh
 * 
 */
public class Page<T> extends Pagination implements Serializable {

	/** serialVersionUID */

	private static final long serialVersionUID = -637461860820111335L;

	/**
	 * 每页显示条数在配置信息中的key(page.size)
	 */
	public static final String PROP_KEY_PAGE = "page.size";

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数
	 */
	protected boolean autoCount = true;

	/**
	 * 查询结果集
	 */
	protected List<T> result = new ArrayList<T>();

	/**
	 * 是否set结果集
	 */
	protected boolean isSetResult = false;

	/**
	 * 总结果集
	 */
	protected List<T> totalResult = new ArrayList<T>();

	/**
	 * 是否set总结果集
	 */
	protected boolean isSetTotalResult = false;

	/**
	 * 查询总记录数
	 */
	protected long totalCount = 0;

	/**
	 * 构造一个每页15条记录autoCount为true的Page。
	 */
	public Page() {
		this(15, 1, true);
	}

	/**
	 * 构造一个autoCount为true的Page。
	 * @param pageSize 每页的记录数
	 */
	public Page(int pageSize) {
		this(pageSize, 1, true);
	}

	/**
	 * 构造一个autoCount为true的Page。
	 * @param pageSize 每页的记录数
	 * @param pageNo 从第几页开始显示，默认从第1页开始。
	 */
	public Page(int pageSize, int pageNo) {
		this(pageSize, pageNo, true);
	}

	/**
	 * 构造一个Page
	 * @param pageSize 每页的记录数
	 * @param pageNo 从第几页开始显示，默认从第1页开始。
	 * @param isAutoCount 取结果集时，是否自动计算总条数
	 */
	public Page(int pageSize, int pageNo, boolean isAutoCount) {
		super(pageSize, pageNo);
		this.autoCount = isAutoCount;
	}

	/**
	 * @description 查询对象时是否自动另外执行count查询获取总记录数, 默认为true.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 是：true，否：false
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * @description 查询对象时是否自动另外执行count查询获取总记录数.
	 * @date 2010-8-13
	 * @author liuhh
	 * @param autoCount true/false
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * @description 查询对象时是否自动另外执行count查询获取总记录数.
	 * @date 2011-4-1
	 * @author liuls
	 * @param theAutoCount true或false
	 * @return page自动查询总页数
	 */
	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	/**
	 * <b>方法名</b>：getResult<br>
	 * <b>功能</b>：取得页内的记录列表。<br>
	 * 如果未设置{@link #setResult(List)}，将使用{@link #getTotalResult()}进行分页。<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2012-10-16 下午05:06:17
	 * @return
	 */
	public List<T> getResult() {
		if (!isSetResult && isSetTotalResult) {
			if (totalResult.isEmpty()) {
				return totalResult;
			}
			int startIndex, endIndex;
			startIndex = pageSize * (pageNo - 1);
			endIndex = pageSize * (pageNo) - 1;
			if (startIndex <= 0) {
				startIndex = 0;
			}
			if (startIndex >= totalResult.size()) {
				startIndex = totalResult.size() - 1;
			}
			if (endIndex <= 0) {
				endIndex = 0;
			}
			if (endIndex >= totalResult.size()) {
				endIndex = totalResult.size() - 1;
			}
			endIndex++;//subList，不包括toIndex元素，需++
			return totalResult.subList(startIndex, endIndex);
		}
		return result;
	}

	/**
	 * @description 设置页内的记录列表.
	 * @date 2010-8-13
	 * @author liuhh
	 * @param result  页内的记录列表
	 */
	public void setResult(final List<T> result) {
		this.result = result;
		isSetResult = true;
	}

	/**
	 * <b>方法名</b>：getTotalResult<br>
	 * <b>功能</b>：取得总结果集<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2012-10-16 下午05:05:12
	 * @return
	 */
	public List<T> getTotalResult() {
		return totalResult;
	}

	/**
	 * <b>方法名</b>：setTotalResult<br>
	 * <b>功能</b>：设置总结果集<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2012-10-16 下午05:05:23
	 * @param totalResult
	 */
	public void setTotalResult(List<T> totalResult) {
		if (totalResult == null) {
			isSetTotalResult = true;
			setTotalCount(0);
		} else {
			this.totalResult = totalResult;
			isSetTotalResult = true;
			setTotalCount(totalResult.size());
		}
	}

	/**
	 * @description 取得总记录数, 默认值为-1.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 总记录数
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @description 设置总记录数.
	 * @date 2010-8-13
	 * @author liuhh
	 * @param totalCount总记录数
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @description 根据pageSize与totalCount计算总页数, 默认值为-1.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return  总页数
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * @description 是否还有下一页.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 有下一页：true，无：false
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * @description 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 下页的页号
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * @description 是否还有上一页.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 有上一页：true，无：false
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * @description 取得上页的页号, 序号从1开始.当前页为首页时返回首页序号.
	 * @date 2010-8-13
	 * @author liuhh
	 * @return 上页的页号
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * <b>方法名</b>：fixPageNo<br>
	 * <b>功能</b>：对fixPageNo进行修正<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-12-19 下午12:09:29
	 * @return true:说明已经对Page修正过；false:说明没有对Page修正过。
	 */
	public boolean fixPageNo() {
		if (this.getTotalPages() < this.pageNo) {
			setPageNo(Long.valueOf(this.getTotalPages()).intValue());
			return true;
		} else {
			if (this.result.isEmpty() && Long.valueOf(this.getTotalPages()).intValue() > 1) {
				setPageNo(Long.valueOf(this.getTotalPages()).intValue() - 1);
				return true;
			}
		}
		return false;

	}

	/**
	 * 
	 * <b>方法名</b>：reset<br>
	 * <b>功能</b>：重置分页内的信息<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-12-28 下午04:04:59
	 * @return
	 */
	public void reset() {
		this.result.clear();
		this.totalCount = 0;
		this.pageNo = 1;
	}
}

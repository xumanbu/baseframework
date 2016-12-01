package com.cxypub.baseframework.sdk.dao;

import java.io.Serializable;
import java.util.List;

import com.cxypub.baseframework.sdk.search.Page;

public interface IReadDAO {

	/**
	 * <b>方法名</b>：get<br>
	 * <b>功能</b>：通过id得到entity实体<br>
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	<X> X get(Class<X> clazz, Serializable id);

	/**
	 * <b>方法名</b>：get<br>
	 * <b>功能</b>：通过id的数组得到entity实体集合<br>
	 * @param <T>
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	<X> List<X> get(Class<X> clazz, Serializable... ids);

	/**
	 * <b>方法名</b>：count<br>
	 * <b>功能</b>：通过hql语句得到结果集的数量<br>
	 * 例如：count("select count(*) from UserInfo where name in (?)", "'3', '4', '5'");<br>
	 * count("from UserInfo where name in (?)", "'3', '4', '5'");
	 * @see #find(String, Object...)
	 * @param hql hql查询语句
	 * @param values hql的参数
	 * @return
	 */
	long count4Hql(String hql, Object... values);

	/**
	 * <b>方法名</b>：count<br>
	 * <b>功能</b>：通过hql语句得到结果集的数量<br>
	 * 例如：count("select count(*) from UserInfo where name in (?)", "'3', '4', '5'");<br>
	 * count("from UserInfo where name in (?)", "'3', '4', '5'");
	 * @see #find(String, Object...)
	 * @param hql hql查询语句
	 * @param values hql的参数
	 * @return
	 */
	long count4Sql(String sql, Object... values);

	/**
	 * 查询方式：hql
	 * 查询结果集
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:45:40
	 * @param dtoClass
	 * @param hql
	 * @param page
	 * @param values
	 * @return
	 */
	<X> List<X> find4Hql(String hql, Object... values);

	/**
	 * 查询方式：hql
	 * 查询带分页的结果集
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:45:40
	 * @param dtoClass
	 * @param hql
	 * @param page
	 * @param values
	 * @return
	 */
	<X> List<X> findAsPage4Hql(String hql, Page<X> page, Object... values);

	/**
	 * 查询方式：hql
	 * 查询的的结果集设置到指定javabean中
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:45:40
	 * @param dtoClass
	 * @param hql
	 * @param page
	 * @param values
	 * @return
	 */
	<X> List<X> findToDTO4Hql(Class<X> dtoClass, String hql, Object... values);

	/**
	 * 查询方式：hql
	 * 查询的带分页的结果集设置到指定javabean中
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:45:40
	 * @param dtoClass
	 * @param hql
	 * @param page
	 * @param values
	 * @return
	 */
	<X> List<X> findAsPageToDTO4Hql(Class<X> dtoClass, String hql, Page<X> page, Object... values);

	/**
	 * 查询方式：原生sql
	 * 根据sql查询结果集
	 * @author xufei
	 * @date  2014-10-29 上午11:33:46
	 * @param sql
	 * @param values
	 * @return
	 */
	<X> List<X> find4Sql(String sql, Object... values);

	/**
	 * 查询方式：原生sql
	 * 根据sql查询带分页的结果集并将结果集放入page.result对象中
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:34:54
	 * @param sql
	 * @param page
	 * @param values
	 * @return
	 */
	<X> List<X> findAsPage4Sql(String sql, Page<X> page, Object... values);

	/**
	 * 查询方式：原生sql
	 * 根据sql查询带分页的结果集，将结果集设置到指定javabean
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:35:43
	 * @param dtoClass
	 * @param sql
	 * @param values
	 * @return
	 */
	<X> List<X> findToDTO4Sql(Class<X> dtoClass, String sql, Object... values);

	/**
	 * 查询方式：原生sql
	 * 根据sql查询带分页的结果集，将结果集设置到指定javabean中并将指定javabean放入page.result对象中
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:36:57
	 * @param dtoClass
	 * @param sql
	 * @param page
	 * @param values
	 * @return
	 */
	<X> List<X> findAsPageToDTO4Sql(Class<X> dtoClass, String sql, Page<X> page, Object... values);

	/**
	 * 查询方式：原生hql
	 * 查询结果集中第一条数据
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:51:56
	 * @param hql
	 * @param values
	 * @return
	 */
	<X> X findUnique4Hql(String hql, Object... values);

	/**
	 * 查询方式：原生hql
	 * 查询结果集中第一条数据,可以将结果转换成dto
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:51:56
	 * @param hql
	 * @param values
	 * @param dtoClass
	 * @return
	 */
	<X> X findUnique4Hql(Class<X> dtoClass, String hql, Object... values);

	/**
	 * 查询方式：原生sql
	 * 查询结果集中第一条数据
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:51:56
	 * @param hql
	 * @param values
	 * @return
	 */
	<X> X findUnique4Sql(String sql, Object... values);

	/**
	 * 查询方式：原生sql
	 * 查询结果集中第一条数据,可以将结果转换成dto
	 * @功能说明：
	 * @author xufei
	 * @date  2014-10-29 上午11:51:56
	 * @param hql
	 * @param values
	 * @param dtoClass
	 * @return
	 */
	<X> X findUnique4Sql(Class<X> dtoClass, String sql, Object... values);

}

package com.cxypub.baseframework.sdk.dao;

import java.io.Serializable;
import java.util.List;

public interface IWriteDAO {

	/**
	 * <b>方法名</b>：remove<br>
	 * <b>功能</b>：删除一个或一组实体<br>
	 * @param o
	 */
	void remove(Object... o);

	/**
	 * <b>方法名</b>：removeById<br>
	 * <b>功能</b>：通过id删除一个或一组实体<br>
	 * @param <T>
	 * @param id
	 */
	void removeById(Class<? extends Object> entityClass, Serializable... id);

	/**
	 * <b>方法名</b>：batchRemove<br>
	 * <b>功能</b>：批量删除一个容器中所有的对象<br>
	 * @param o
	 */
	void batchRemove(List<? extends Object> list);

	/**
	 * <b>方法名</b>：saveOrUpdate<br>
	 * <b>功能</b>：保存或更新一个或一组实体<br>
	 * 根据{@link javax.persistence.Id}在o中进行查找id字段，如果id的内容是""，那么将调用set方法，将id替换成null。
	 * @param o
	 */
	void saveOrUpdate(Object... o);

	/**
	 * <b>方法名</b>：batchMarge<br>
	 * <b>功能</b>：保存或者更新一个容器中的对象<br>
	 * @param o
	 */
	void batchSaveOrUpdate(List<? extends Object> list);

	/**
	 * <b>方法名</b>：executeHql<br>
	 * <b>功能</b>：通过hql语句进行批量更新、删除。<br>
	 * @author <font color='blue'>xiyf</font> 
	 * @date  2012-2-14 上午09:19:54
	 * @param hql hql语句
	 * @param o 查询参数值对象集合
	 * @return 执行hql影响数据库记录数
	 */
	int executeHql(final String hql, Object... o);

	/**
	 * <b>方法名</b>：executeHql<br>
	 * <b>功能</b>：通过sql语句进行批量更新、删除。<br>
	 * @author <font color='blue'>xiyf</font> 
	 * @date  2012-2-14 上午09:19:54
	 * @param hql hql语句
	 * @param o 查询参数值对象集合
	 * @return 执行hql影响数据库记录数
	 */
	int executeSql(final String sql, Object... o);

}

package com.cxypub.baseframework.sdk.dao.hibernate.impl;

/*
 * <b>文件名</b>：HibernateWriteDAOImpl.java
 *
 * 文件描述：
 *
 *
 * 2014-4-1  上午09:32:42
 */

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cxypub.baseframework.sdk.dao.IWriteDAO;

@Repository("hibernateWriteDAOImpl")
public class HibernateWriteDAOImpl extends BaseHibernateOperate implements IWriteDAO {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	@PostConstruct
	protected void setSessionFactory() {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void remove(Object... o) {
		for (Object obj : o) {
			super.getHibernateTemplate().delete(obj);
		}
	}

	@Override
	public void removeById(Class<? extends Object> entityClass, Serializable... ids) {
		for (Serializable id : ids) {
			remove(getHibernateTemplate().get(entityClass, id));
		}
	}

	@Override
	public void batchRemove(List<? extends Object> list) {
		super.getHibernateTemplate().deleteAll(list);

	}

	@Override
	public void saveOrUpdate(Object... o) {
		for (Object obj : o) {
			super.getHibernateTemplate().saveOrUpdate(obj);
		}
	}

	@Override
	public void batchSaveOrUpdate(List<? extends Object> list) {
		for (Object obj : list) {
			super.getHibernateTemplate().saveOrUpdate(obj);
		}
	}

	@Override
	public int executeHql(String hql, Object... o) {
		Query query = this.getSession().createQuery(hql);
		setQueryParameters(query, o);
		return query.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Object... o) {
		Query query = this.getSession().createSQLQuery(sql);
		setQueryParameters(query, o);
		return query.executeUpdate();
	}

}

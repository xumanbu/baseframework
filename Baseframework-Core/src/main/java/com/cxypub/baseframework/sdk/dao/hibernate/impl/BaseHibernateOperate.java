package com.cxypub.baseframework.sdk.dao.hibernate.impl;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cxypub.baseframework.sdk.dao.hibernate.IBaseHibernateSessionOperate;
import com.cxypub.baseframework.sdk.search.Pagination;

/**
 * <p> Title: 智能营区综合管控系统-BaseHibernateSessionOperate.java </p>
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-10-29 上午11:05:07
 */
public class BaseHibernateOperate extends HibernateDaoSupport implements IBaseHibernateSessionOperate {

	/**
	 * 默认使用spring配置的sessionFactory做为DAO的sessionFactory
	 */
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@PostConstruct
	protected void setSessionFactory() {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void flush() {
		super.getHibernateTemplate().flush();
	}

	@Override
	public void evict(Object entity) {
		super.getHibernateTemplate().evict(entity);

	}

	@Override
	public void clear() {
		super.getHibernateTemplate().clear();
	}

	protected void setQueryParameters(Query query, Object[] paramValues) {
		if (paramValues == null || paramValues.length <= 0 || paramValues[0] == null) {
			return;
		}
		for (int i = 0; i < paramValues.length; i++) {
			Object param = paramValues[i];
			if (param instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, ?> paramMap = (Map<String, ?>) param;
				for (Map.Entry<String, ?> entry : paramMap.entrySet()) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			} else {
				query.setParameter(i, paramValues[i]);
			}
		}
	}

	protected void setQueryPage(Query query, Pagination page) {
		query.setMaxResults(page.getPageSize());
		query.setFirstResult(page.getFirst() - 1);
	}

	protected <T> void setResultTransformer(Query query, Class<T> dtoClass) {
		if (dtoClass != null) {
			query.setResultTransformer(Transformers.aliasToBean(dtoClass));
		}
	}

}

package com.cxypub.baseframework.sdk.dao.hibernate.impl;

/*
 * <b>文件名</b>：HibernateReadDAOImpl.java
 *
 * 文件描述：
 *
 *
 * 2014-4-1  上午09:51:28
 */

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.cxypub.baseframework.sdk.dao.IReadDAO;
import com.cxypub.baseframework.sdk.search.Page;

@SuppressWarnings("unchecked")
@Repository("hibernateReadDAOImpl")
public class HibernateReadDAOImpl extends BaseHibernateOperate implements IReadDAO {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	@PostConstruct
	protected void setSessionFactory() {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public <X> X get(Class<X> clazz, Serializable id) {
		return super.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public <X> List<X> get(Class<X> clazz, Serializable... ids) {
		List<X> list = new ArrayList<X>();
		for (Serializable id : ids) {
			list.add(this.get(clazz, id));
		}
		return list;
	}

	@Override
	public long count4Hql(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		this.setQueryParameters(query, values);
		return query.list().size();
	}

	@Override
	public long count4Sql(String sql, Object... values) {
		Query query = this.getSession().createSQLQuery(sql);
		this.setQueryParameters(query, values);
		return query.list().size();
	}

	@Override
	public List<?> find4Hql(String hql, Object... values) {
		Query query = super.getSession().createQuery(hql);
		setQueryParameters(query, values);
		return query.list();
	}

	@Override
	public <X> List<X> findAsPage4Hql(String hql, Page<X> page, Object... values) {
		return this.findAsPageToDTO4Hql(null, hql, page, values);
	}

	@Override
	public <X> List<X> findToDTO4Hql(Class<X> dtoClass, String hql, Object... values) {
		return this.findAsPageToDTO4Hql(dtoClass, hql, null, values);
	}

	@Override
	public <X> List<X> findAsPageToDTO4Hql(Class<X> dtoClass, String hql, Page<X> page, Object... values) {

		Query query = super.getSession().createQuery(hql);
		setQueryParameters(query, values);
		if (page != null) {
			setQueryPage(query, page);
		}
		// 下面这段代码是纯粹对hql进行字符解析，有可能会出现考虑不全的情况。TODO 待更好的解决方案
		if (dtoClass != null && !hql.toLowerCase().trim().startsWith("from")) {// 如果直接以from开头，会直接查询出对象，不需要进行转换，否则会出现错误。
			String lowerHql = hql.toLowerCase();
			if (lowerHql.trim().startsWith("select")) {
				String strBetweenSelectAndFrom = hql.substring(lowerHql.indexOf("select") + "select".length(), lowerHql.indexOf("from")).trim();
				String strBetweenFromAndWhere;
				if (lowerHql.indexOf("where") < 0) {
					strBetweenFromAndWhere = hql.substring(lowerHql.indexOf("from") + "from".length()).trim();
				} else {
					strBetweenFromAndWhere = hql.substring(lowerHql.indexOf("from") + "from".length(), lowerHql.indexOf("where")).trim();
				}
				if ((strBetweenFromAndWhere + " ").indexOf(" " + strBetweenSelectAndFrom + " ") < 0) {// 不是直接select出一个对象的方式
					setResultTransformer(query, dtoClass);
				}
			}
		}
		return query.list();
	}

	@Override
	public <X> List<X> find4Sql(final String sql, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback<List<X>>() {
			@Override
			public List<X> doInHibernate(Session session) {
				Query query = session.createSQLQuery(sql);
				setQueryParameters(query, values);
				return query.list();
			}
		});
	}

	@Override
	public <X> List<X> findAsPage4Sql(String sql, Page<X> page, Object... values) {
		return this.findAsPageToDTO4Sql(null, sql, page, values);
	}

	@Override
	public <X> List<X> findToDTO4Sql(Class<X> dtoClass, String sql, Object... values) {
		return this.findAsPageToDTO4Sql(dtoClass, sql, null, values);
	}

	@Override
	public <X> List<X> findAsPageToDTO4Sql(final Class<X> dtoClass, final String sql, final Page<X> page, final Object... values) {
		@SuppressWarnings("rawtypes")
		List result = super.getHibernateTemplate().execute(new HibernateCallback<List<X>>() {

			@Override
			public List<X> doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (page != null) {
					query.setFirstResult(page.getFirst() - 1);
					query.setMaxResults(page.getPageSize());
				}
				if (dtoClass != null) {
					scalarChange(dtoClass, sql, query);
					query.setResultTransformer(Transformers.aliasToBean(dtoClass));
				}
				setQueryParameters(query, values);
				return query.list();
			}

		});
		page.setResult(result);
		page.setTotalCount(count4Sql(sql, values));
		return result;
	}

	@Override
	public <X> X findUnique4Hql(String hql, Object... values) {
		Page<X> page = new Page<X>();
		page.setPageSize(1);
		page.setPageNo(1);
		List<X> list = this.findAsPage4Hql(hql, page, values);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public <X> X findUnique4Hql(Class<X> dtoClass, String hql, Object... values) {
		Page<X> page = new Page<X>();
		page.setPageSize(1);
		page.setPageNo(1);
		List<X> list = this.findAsPageToDTO4Hql(dtoClass, hql, page, values);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public <X> X findUnique4Sql(Class<X> dtoClass, String sql, Object... values) {
		Page<X> page = new Page<X>();
		page.setPageSize(1);
		page.setPageNo(1);
		List<X> list = this.findAsPageToDTO4Sql(dtoClass, sql, page, values);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public <X> X findUnique4Sql(String sql, Object... values) {
		Page<X> page = new Page<X>();
		page.setPageSize(1);
		page.setPageNo(1);
		List<X> list = this.findAsPage4Sql(sql, page, values);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * select 字段 转换并根据类属性进行scalar设置
	 * @param clazz
	 * @param sql
	 * @param query
	 */
	protected static void scalarChange(final Class<?> clazz, final String sql, SQLQuery query) {
		String selectStr = "select";
		String fromStr = "from";
		if (sql.indexOf("select") == -1) {
			selectStr = "SELECT";
		}
		if (sql.indexOf("from") == -1) {
			fromStr = "FROM";
		}
		String sqlSelect = sql.substring(sql.indexOf(selectStr), sql.indexOf(fromStr));
		final String[] scalars = sqlSelect.replace(selectStr, "").split(",");
		for (String scalar : scalars) {
			scalar = scalar.trim();
			int asIndex = scalar.lastIndexOf(" as");
			int spaceIndex = scalar.lastIndexOf(' ');
			int tabIndex = scalar.lastIndexOf('\t');
			int dotIndex = scalar.indexOf('.');
			if (asIndex != -1) { // 有as
				scalar = scalar.substring(asIndex + 3).trim();
			} else if (spaceIndex != -1 || tabIndex != -1) { // 没使用as, 使用了空格或tab
				if (spaceIndex != -1 && spaceIndex > tabIndex) {
					scalar = scalar.substring(spaceIndex).trim();
				} else {
					scalar = scalar.substring(tabIndex).trim();
				}
			} else if (dotIndex != -1) { // 没有使用alias 看看是不是使用了 .
				scalar = scalar.substring(dotIndex + 1).trim();
			} // 所有都不满足 就是scalar自己了
			query.addScalar(scalar, getScalarType(clazz, scalar));
		}
	}

	/**
	 * 得到类属性的 基本数据类型 对应的的 hibernate数据类型
	 * @param clazz Class
	 * @param field String
	 * @return Type
	 */
	@SuppressWarnings("deprecation")
	private static Type getScalarType(Class<?> clazz, String fieldName) {
		Class<?> fieldType = null;
		Field field = getClassField(clazz, fieldName);
		if (field == null) {
			throw new RuntimeException(clazz.getName() + "没有名为:" + fieldName + "的属性出错");
		}
		fieldType = field.getType();
		if (fieldType == String.class) {
			return Hibernate.STRING; // 字符串
		} else if (fieldType == Integer.class || fieldType == Integer.TYPE) {
			return Hibernate.INTEGER; // 整形数字
		} else if (fieldType == Long.class || fieldType == Long.TYPE) {
			return Hibernate.LONG; // 长整形数字
		} else if (fieldType == Double.class || fieldType == Double.TYPE) {
			return Hibernate.DOUBLE; // 双精度小数
		} else if (fieldType == Float.class || fieldType == Float.TYPE) {
			return Hibernate.FLOAT; // 单精度小数
		} else if (fieldType == Character.class || fieldType == Character.TYPE) {
			return Hibernate.CHARACTER; // 字符
		} else if (fieldType == java.util.Date.class || fieldType == java.sql.Date.class || fieldType == java.sql.Timestamp.class) {
			return Hibernate.TIMESTAMP; // 日期参数
		} else {
			return Hibernate.OBJECT; // 其他
		}
	}

	private static Field getClassField(Class<?> clazz, String fieldName) {
		if (clazz == null) {
			return null;
		}
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			field = getClassField(clazz.getSuperclass(), fieldName);
		}
		return field;
	}
}

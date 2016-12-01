package com.cxypub.baseframework.sdk.dao.hibernate.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cxypub.baseframework.sdk.container.SpringContextUtil;
import com.cxypub.baseframework.sdk.dao.IReadDAO;
import com.cxypub.baseframework.sdk.dao.IWriteDAO;
import com.cxypub.baseframework.sdk.dao.hibernate.IBaseHibernateDAO;
import com.cxypub.baseframework.sdk.search.Page;

@Repository("genericHirbernateDAO")
/** 去掉定义成非单例的注解，在Base基类，使用单例模式。 */
// @Scope("prototype")
public class HibernateGenericDAO extends BaseHibernateOperate implements IBaseHibernateDAO {

	@Resource(name = "hibernateWriteDAOImpl")
	protected IWriteDAO writeDao;

	@Resource(name = "hibernateReadDAOImpl")
	protected IReadDAO readDao;

	@Override
	public <X> X get(Class<X> clazz, Serializable id) {
		return readDao.get(clazz, id);
	}

	@Override
	public <X> List<X> get(Class<X> clazz, Serializable... ids) {
		return readDao.get(clazz, ids);
	}

	@Override
	public long count4Hql(String hql, Object... values) {
		return readDao.count4Hql(hql, values);
	}

	@Override
	public long count4Sql(String sql, Object... values) {
		return readDao.count4Sql(sql, values);
	}

	@Override
	public void remove(Object... o) {
		writeDao.remove(o);
	}

	@Override
	public void removeById(Class<? extends Object> entityClass, Serializable... id) {
		writeDao.removeById(entityClass, id);
	}

	@Override
	public void batchRemove(List<? extends Object> list) {
		writeDao.batchRemove(list);

	}

	@Override
	public void saveOrUpdate(Object... o) {
		writeDao.saveOrUpdate(o);

	}

	@Override
	public void batchSaveOrUpdate(List<? extends Object> list) {
		writeDao.batchSaveOrUpdate(list);
	}

	@Override
	public int executeHql(String hql, Object... o) {
		return writeDao.executeHql(hql, o);
	}

	@Override
	public int executeSql(String sql, Object... o) {
		return writeDao.executeSql(sql, o);
	}

	@Override
	public <X> List<X> find4Hql(String hql, Object... values) {
		return readDao.find4Hql(hql, values);
	}

	@Override
	public <X> List<X> findAsPage4Hql(String hql, Page<X> page, Object... values) {
		return readDao.findAsPage4Hql(hql, page, values);
	}

	@Override
	public <X> List<X> findToDTO4Hql(Class<X> dtoClass, String hql, Object... values) {
		return readDao.findToDTO4Hql(dtoClass, hql, values);
	}

	@Override
	public <X> List<X> findAsPageToDTO4Hql(Class<X> dtoClass, String hql, Page<X> page, Object... values) {
		return readDao.findAsPageToDTO4Hql(dtoClass, hql, page, values);
	}

	@Override
	public <X> List<X> find4Sql(String sql, Object... values) {
		return readDao.find4Sql(sql, values);
	}

	@Override
	public <X> List<X> findAsPage4Sql(String sql, Page<X> page, Object... values) {
		return readDao.findAsPage4Sql(sql, page, values);
	}

	@Override
	public <X> List<X> findToDTO4Sql(Class<X> dtoClass, String sql, Object... values) {
		return readDao.findToDTO4Sql(dtoClass, sql, values);
	}

	@Override
	public <X> List<X> findAsPageToDTO4Sql(Class<X> dtoClass, String sql, Page<X> page, Object... values) {
		return readDao.findAsPageToDTO4Sql(dtoClass, sql, page, values);
	}

	@Override
	public <X> X findUnique4Hql(String hql, Object... values) {
		return readDao.findUnique4Hql(hql, values);
	}

	@Override
	public <X> X findUnique4Sql(String sql, Object... values) {
		return readDao.findUnique4Sql(sql, values);
	}

	@Override
	public String[] execProcedure(final String procedure, final Object[] inputParams, final int[] outParamsTypes) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) SpringContextUtil.getBean("dataSource"));
		String result = jdbcTemplate.execute(procedure, new CallableStatementCallback<String>() {
			@Override
			public String doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				int inputIndex = 0;
				if (inputParams != null) {
					for (int i = 0; i < inputParams.length; i++) {
						cs.setObject(i + 1, inputParams[i]);
						inputIndex++;
					}
				}
				if (outParamsTypes != null) {
					for (int i = 0; i < outParamsTypes.length; i++) {
						cs.registerOutParameter(inputIndex + i + 1, outParamsTypes[i]);// Types.VARCHAR
					}
				}
				cs.executeUpdate();
				String outResult = "";
				if (outParamsTypes.length == 1) {
					outResult = cs.getString(inputIndex + 1);
				} else {
					for (int i = 0; i < outParamsTypes.length; i++) {
						outResult += cs.getString(inputIndex + i + 1) + "_";
					}
				}
				return outResult;
			}
		});
		return result.split("_");
	}

	@Override
	public <X> X findUnique4Hql(Class<X> dtoClass, String hql, Object... values) {
		return readDao.findUnique4Hql(dtoClass, hql, values);
	}

	@Override
	public <X> X findUnique4Sql(Class<X> dtoClass, String sql, Object... values) {
		return readDao.findUnique4Sql(dtoClass, sql, values);
	}
}

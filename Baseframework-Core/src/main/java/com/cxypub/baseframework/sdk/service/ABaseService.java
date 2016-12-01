package com.cxypub.baseframework.sdk.service;

import javax.annotation.Resource;

import com.cxypub.baseframework.sdk.dao.hibernate.IBaseHibernateDAO;

public abstract class ABaseService {

	@Resource(name = "genericHirbernateDAO")
	protected IBaseHibernateDAO dao;

}

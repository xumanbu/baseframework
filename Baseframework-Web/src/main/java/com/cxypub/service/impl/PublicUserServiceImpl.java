/**
 * @Title: UserServiceImpl.java
 * @Package com.cxypub.shop.user.service.impl
 * @Description: TODO
 * Copyright: Copyright (c) 2015 
 * Company:上海追月信息科技有限公司
 * 
 * @author oaoCoder-徐飞
 * @date 2015年7月27日 下午4:48:45
 * @version V1.0
 */

package com.cxypub.service.impl;

import org.springframework.stereotype.Service;

import com.cxypub.baseframework.sdk.search.Page;
import com.cxypub.baseframework.sdk.service.ABaseService;
import com.cxypub.entity.PublicUser;
import com.cxypub.service.IPublicUserService;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @author Comsys-徐飞
 * @date 2015年7月27日 下午4:48:45
 *
 */
@Service
public class PublicUserServiceImpl extends ABaseService implements IPublicUserService {

	/**
	 * <p>Title: patchDelete</p>
	 * <p>Description: </p>
	 * @param ids
	 * @see com.cxypub.service.IPublicUserService#patchDelete(java.lang.String)
	 */

	@Override
	public void patchDelete(String ids) {
		System.out.println("this userservice patchDelete");

	}

	/**
	 * <p>Title: getUsers</p>
	 * <p>Description: </p>
	 * @param user
	 * @param pageParam
	 * @return
	 * @see com.cxypub.service.IPublicUserService#getUsers(com.cxypub.user.entity.User, com.cxypub.baseframework.sdk.search.Page)
	 */

	@Override
	public Page<PublicUser> getUsers(PublicUser user, Page<PublicUser> pageParam) {
		// TODO Auto-generated method stub

		return null;
	}

	/**
	 * <p>Title: save</p>
	 * <p>Description: </p>
	 * @param user
	 * @see com.cxypub.service.IPublicUserService#save(com.cxypub.user.entity.User)
	 */

	@Override
	public void save(PublicUser user) {
		// dao.saveOrUpdate(user);

		StringBuilder sql = new StringBuilder("select * from fc_user");

		long c = dao.count4Sql(sql.toString());
		System.out.println("c" + c);
	}

	@Override
	public PublicUser getPublicUserById(String userId) {
		return this.dao.get(PublicUser.class, userId);
	}

}

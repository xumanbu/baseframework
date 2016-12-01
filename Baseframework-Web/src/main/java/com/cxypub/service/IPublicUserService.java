package com.cxypub.service;

import com.cxypub.baseframework.sdk.search.Page;
import com.cxypub.entity.PublicUser;

/**
 * 
 * @ClassName: UserService
 * @Description: TODO
 * @author Comsys-徐飞
 * @date 2015年7月27日 下午4:47:31
 *
 */
public interface IPublicUserService {
	/**
	 * @Title: getPublicUserById
	 * @Description: 根据id获取用户
	 * @param userId
	 * @return  传人参数
	 * @author 徐飞
	 */
	PublicUser getPublicUserById(String userId);

	/**
	 * @Title: save
	 * @Description: 保存一个用户信息
	 * @param user  传人参数
	 * @author 徐飞
	 */
	void save(PublicUser user);

	/**
	 * @Title: patchDelete
	 * @author:徐飞
	 * @Description: 批量删除用户的方法
	 * @param ids 用户id字符串，多个id用逗号分隔的形式
	 * @return 
	 * @throws
	 */
	void patchDelete(String ids);

	/**
	* find the user
	*
	* @param user      the queryUser
	* @param pageParam the pageParram
	* @return the user
	*/
	Page<PublicUser> getUsers(PublicUser user, Page<PublicUser> pageParam);
}

package com.cxypub.baseframework.sdk.security.service;

import com.cxypub.baseframework.sdk.security.base.AuthUser;
import com.cxypub.baseframework.sdk.security.base.LoginToken;

public interface LoginUserService {

	public static final String HASH_ALGORITHM = "SHA-1";

	public static final int HASH_INTERATIONS = 1024;

	/**
	 * @Title: getMasterUserByLoginName
	 * @Description: 根据用户名获取到LoginUser对象
	 * @param loginName
	 * @return  传人参数
	 * @author 徐飞
	 */
	LoginToken getSysUserByLoginName(String loginName);

	/**
	 * @Title: getLoginUserByLoginName
	 * @Description: 获取登陆用户的封装对象
	 * @param loginName
	 * @return  传人参数
	 * @author 徐飞
	 */
	AuthUser getLoginUserByLoginName(String loginName);
}

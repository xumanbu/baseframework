package com.cxypub.baseframework.sdk.security.service.impl;

import org.springframework.stereotype.Service;

import com.cxypub.baseframework.sdk.security.base.AuthUser;
import com.cxypub.baseframework.sdk.security.base.LoginToken;
import com.cxypub.baseframework.sdk.security.service.LoginUserService;

@Service("loginUserServiceImpl")
public class LoginUserServiceImpl implements LoginUserService {

	@Override
	public LoginToken getSysUserByLoginName(String loginName) {
		LoginToken loginToken = new LoginToken();
		loginToken.setId("1");
		loginToken.setLoginName("shuwenqi");
		loginToken.setUserName("束文齐");
		loginToken.setPassword("be2d66f0829a10af29ac0a83504fe25d468abc28");
		loginToken.setSalt("d03dd227a1458b46");
		return loginToken;
	}

	@Override
	public AuthUser getLoginUserByLoginName(String loginName) {
		AuthUser authUser = new AuthUser();
		authUser.setLoginToken(this.getSysUserByLoginName(loginName));
		return authUser;
	}

}

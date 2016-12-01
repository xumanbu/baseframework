package com.cxypub.baseframework.sdk.security.realm;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxypub.baseframework.sdk.security.base.AuthUser;
import com.cxypub.baseframework.sdk.security.base.LoginToken;
import com.cxypub.baseframework.sdk.security.service.LoginUserService;
import com.cxypub.baseframework.sdk.util.EmptyUtils;
import com.cxypub.baseframework.sdk.util.Encodes;

/**
 * @description:		shiro权限验证 	                                                                                          
 * @author: 			束文奇                                     
 * @createDate：		2014-7-2
 * @version: 			v1.0
 */
public class ShiroDbRealmImpl extends AuthorizingRealm implements CacheManagerAware {

	@Autowired
	protected LoginUserService loginUserService;

	/**
	 * 认证,登录时调用.
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		LoginToken loginToken = loginUserService.getSysUserByLoginName(token.getUsername());
		if (!EmptyUtils.isNullOrEmpty(loginToken)) {
			byte[] salt = Encodes.decodeHex(loginToken.getSalt());
			AuthUser authUserInfo = loginUserService.getLoginUserByLoginName(token.getUsername());
			System.out.println(authUserInfo);
			return new SimpleAuthenticationInfo(authUserInfo, loginToken.getPassword(), ByteSource.Util.bytes(salt), loginToken.getUserName());
		} else {
			return null;
		}
	}

	/**
	 * 授权, 进行鉴权时调用.
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return getAuthorizationInfo(principals.getPrimaryPrincipal());
	}

	/**
	 * @Title: getAuthorizationInfo
	 * @Description: 授权
	 * @param principal
	 * @return
	 * @author 徐飞
	 */
	private SimpleAuthorizationInfo getAuthorizationInfo(Object principal) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;

	}

	/** 重写退出时缓存处理方法 */
	@Override
	public void doClearCache(PrincipalCollection principalcollection) {

	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(LoginUserService.HASH_ALGORITHM);
		matcher.setHashIterations(LoginUserService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
}
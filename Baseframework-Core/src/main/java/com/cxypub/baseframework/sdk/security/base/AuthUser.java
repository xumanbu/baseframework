package com.cxypub.baseframework.sdk.security.base;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AuthUser
 * @Description: 这个对象是代表当前登陆用户信息的封装，会放入当前会话中，供系统使用，当需要扩展登陆用户信息可以继承这个类
 * @author Comsys-徐飞
 * @date 2015年11月30日 下午4:00:16
 *
 */
public class AuthUser implements Serializable {

	private static final long serialVersionUID = 5348151864168376511L;

	/**
	 * 登陆账号信息
	 */
	private LoginToken loginToken;

	/**
	 * 角色
	 */
	private List<Role> roleList;

	/**
	 * 资源集合
	 */
	private List<Resource> resourceList;

	/**
	 * 部门
	 */
	private Dept dept;

	public LoginToken getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(LoginToken loginToken) {
		this.loginToken = loginToken;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "AuthUser [loginToken=" + loginToken + ", roleList=" + roleList + ", resourceList=" + resourceList + ", dept=" + dept + "]";
	}

}

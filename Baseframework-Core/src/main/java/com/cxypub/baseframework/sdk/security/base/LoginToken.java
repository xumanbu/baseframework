package com.cxypub.baseframework.sdk.security.base;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>类名</b>：User.java<br>
 *
 * <p><b>标题</b>：品恩产品研发  </p>
 * <p><b>描述</b>：
 *           描述用户信息的接口
 * </p>
 * <p><b>版权声明</b>：Copyright (c) 2012</p>
 * <p><b>公司</b>：品恩科技 </p>
 * @author <font color='blue'>Administrator</font> 
 * @version 1.0
 * @date  2012-7-26 下午01:55:52
 */
public class LoginToken implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = -3248667612924145599L;

	/**
	 * 登陆用户id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 登陆用户名
	 */
	private String loginName;

	/**
	 * 登陆密码
	 */
	private String password;

	/**
	 * 加密salt
	 */
	private String salt;

	/**
	 * 最后登陆时间
	 */
	private Date LastLoginTime;

	/**
	 * 是否启用
	 */
	private boolean enabled;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "LoginToken [id=" + id + ", userName=" + userName + ", loginName=" + loginName + ", password=" + password + ", salt=" + salt + ", LastLoginTime=" + LastLoginTime + ", enabled=" + enabled + "]";
	}

}

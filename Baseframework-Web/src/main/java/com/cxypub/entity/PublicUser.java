package com.cxypub.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.cxypub.baseframework.sdk.entity.IdEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * <b>类名：</b>PublicUser.java<br>
 * <p><b>标题：</b>OAO商城定制系统</p>
 * <p><b>描述：</b>OAO商城定制系统</p>
 * <p><b>版权声明：</b>Copyright (c) 2015</p>
 * <p><b>公司：</b>上海追月信息科技有限公司 </p>
 * @author <font color='blue'>陈鹏</font> 
 * @version 1.0.1
 * @date  2015年9月17日 下午4:43:12
 * @Description 行业使用者用户表(主表)
 */

@Entity
@Table(name = "PBC_USER")
public class PublicUser extends IdEntity {

	/** serialVersionUID */

	private static final long serialVersionUID = -210753503450265731L;

	/** 产品序列号 */
	private String productSerialNumber;

	/** 账号 */
	private String loginName;

	/** 密码 */
	private String password;

	/**过度密码**/
	private String plainPassword;

	/** 加密码 */
	private String salt;

	/** 邮箱 */
	private String email;

	/** 手机号 */
	private String mobile;

	/** 性别(MAN,男，WOMEN，女) */
	private String sex;

	/** 真实姓名 */
	private String realName;

	/** 用户头像 */
	private String headerPic;

	/** 是否冻结状态 NO：正常，YES:冻结*/
	private String isFreeze = "NO";

	/** 创建时间 */
	private Date createTime;

	/** 创建人ID */
	private String createUserId;

	/** 更新时间 */
	private Date updateTime;

	/** 更新人ID */
	private String updateUserId;

	/** 上次登录时间 */
	private Date lastLoginTime;

	/** 当前登录时间 */
	private Date currentLoginTime;

	/** 是否行业使用者 **/
	private String isProductOwner = "NO";

	/** 是否商城店铺维护人员  */
	private String isOperatoer = "NO";

	/** 是否商家、卖家  */
	private String isSeller = "NO";

	/** 是否分销商  */
	private String isDistribution = "NO";

	/** 是否会员 **/
	private String isMember = "NO";

	/** 运维用户类型【行业使用者运维:TERRACE_CLERK 、店铺店员:SALES_CLERK】 */
	private String operatoerType;

	/**
	 * getter method
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "com.cxypub.baseframework.sdk.dao.util.UIDGenerator")
	public String getId() {
		return id;
	}

	/**
	 * getter method
	 * @return the plainPassword
	 */
	@Transient
	public String getPlainPassword() {
		return plainPassword;
	}

	/**
	 * setter method
	 * @param plainPassword the plainPassword to set
	 */
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getHeaderPic() {
		return headerPic;
	}

	public void setHeaderPic(String headerPic) {
		this.headerPic = headerPic;
	}

	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCurrentLoginTime() {
		return currentLoginTime;
	}

	public void setCurrentLoginTime(Date currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}

	public String getIsProductOwner() {
		return isProductOwner;
	}

	public void setIsProductOwner(String isProductOwner) {
		this.isProductOwner = isProductOwner;
	}

	public String getIsOperatoer() {
		return isOperatoer;
	}

	public void setIsOperatoer(String isOperatoer) {
		this.isOperatoer = isOperatoer;
	}

	public String getIsSeller() {
		return isSeller;
	}

	public void setIsSeller(String isSeller) {
		this.isSeller = isSeller;
	}

	public String getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(String isDistribution) {
		this.isDistribution = isDistribution;
	}

	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	/**
	 * @return the operatoerType
	 */
	public String getOperatoerType() {
		return operatoerType;
	}

	/**
	 * @param operatoerType the operatoerType to set
	 */
	public void setOperatoerType(String operatoerType) {
		this.operatoerType = operatoerType;
	}

	/**
	 * Constructor for PublicUser
	 */
	public PublicUser() {
		super();
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
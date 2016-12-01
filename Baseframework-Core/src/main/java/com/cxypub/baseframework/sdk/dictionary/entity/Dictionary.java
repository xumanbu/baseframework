package com.cxypub.baseframework.sdk.dictionary.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.cxypub.baseframework.sdk.entity.IdEntity;

/**
 * 
 * @ClassName: Dictionary
 * @Description: 数据字典实体类
 * @author 徐飞
 * @date 2016年1月19日 上午11:01:44
 *
 */
@Entity
@Table(name = "MASTER_DICTIONARY")
public class Dictionary extends IdEntity {

	private static final long serialVersionUID = -8977962941083658104L;

	/** 父节点ID */
	private String dictParentId;

	/** 字典名称 */
	private String dictName;

	/** 数据字典全名称 */
	private String dictFullName;

	/** 名称拼音 */
	private String dictChineseName;

	/** 字典值 */
	private String dictValue;

	/** 备注 */
	private String dictRemark;

	/**数据字典代码，用于记录上下级关系,目前采用的形式是：prparentid/parentid/id TODO 有待改进*/
	private String dictCode;

	/** 数据字典的标识代码唯一 */
	private String dictNo;

	/** 字典是否启用：YES:启用;NO:不启用 */
	private String dictIsStart = "YES";

	/** 是否是叶子节点：默认是叶子节点 */
	private String isParent;

	/** 排序号 */
	private Integer dictLevel = 1;

	/** 排序号 */
	private Integer dictSort;

	/** 如：由XX系统、XX模块进行维护 */
	private String dictOwner;

	/** 创建时间 */
	private java.util.Date createTime;

	/** 创建者id */
	private String createUserId;

	/** 创建者名称 */
	private String createUserName;

	/** 更新者id */
	private String updateUserId;

	/** 更新者名称 */
	private String updateUserName;

	/** 更新时间 */
	private java.util.Date updateTime;

	/** 备用字段0 : 当数据为银行时，该字段值代表单笔限额,当为提现参数时，该字段代表，最大限额*/
	private String blank0;

	/** 备用字段1 ： 当数据为银行时，该字段值代表每日限额，当为提现参数时，该字段代表，每月免费提现次数*/
	private String blank1;

	/** 备用字段2 ： 当数据为银行时，该字段值代表客服电话*，当为提现参数时，该字段代表，每月超过指定提现次数后，收取每笔交易的百分比*/
	private String blank2;

	public String enableFlag;

	// 非PO
	/** 父节点*/
	private Dictionary parent;

	/** 父节点id**/
	private String pid;

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

	public String getDictParentId() {
		return dictParentId;
	}

	/** @param newDictParentId */
	public void setDictParentId(String newDictParentId) {
		dictParentId = newDictParentId;
	}

	public String getDictName() {
		return dictName;
	}

	/** @param newDictName */
	public void setDictName(String newDictName) {
		dictName = newDictName;
	}

	public String getDictFullName() {
		return dictFullName;
	}

	public Integer getDictLevel() {
		return dictLevel;
	}

	public void setDictLevel(Integer dictLevel) {
		this.dictLevel = dictLevel;
	}

	public void setDictFullName(String dictFullName) {
		this.dictFullName = dictFullName;
	}

	public String getDictChineseName() {
		return dictChineseName;
	}

	/** @param newDictChineseName */
	public void setDictChineseName(String newDictChineseName) {
		dictChineseName = newDictChineseName;
	}

	public String getDictValue() {
		return dictValue;
	}

	/** @param newDictValue */
	public void setDictValue(String newDictValue) {
		dictValue = newDictValue;
	}

	public String getDictRemark() {
		return dictRemark;
	}

	/** @param newDictRemark */
	public void setDictRemark(String newDictRemark) {
		dictRemark = newDictRemark;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictNo() {
		return dictNo;
	}

	/** @param newDictNo */
	public void setDictNo(String newDictNo) {
		dictNo = newDictNo;
	}

	public String getDictIsStart() {
		return dictIsStart;
	}

	/** @param newDictIsStart */
	public void setDictIsStart(String newDictIsStart) {
		dictIsStart = newDictIsStart;
	}

	public Integer getDictSort() {
		return dictSort;
	}

	/** @param newDictSort */
	public void setDictSort(Integer newDictSort) {
		dictSort = newDictSort;
	}

	public String getDictOwner() {
		return dictOwner;
	}

	/** @param newDictOwner */
	public void setDictOwner(String newDictOwner) {
		dictOwner = newDictOwner;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	/** @param newCreateTime */
	public void setCreateTime(java.util.Date newCreateTime) {
		createTime = newCreateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	/** @param newCreateUserId */
	public void setCreateUserId(String newCreateUserId) {
		createUserId = newCreateUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	/** @param newCreateUserName */
	public void setCreateUserName(String newCreateUserName) {
		createUserName = newCreateUserName;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	/** @param newUpdateUserId */
	public void setUpdateUserId(String newUpdateUserId) {
		updateUserId = newUpdateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	/** @param newUpdateUserName */
	public void setUpdateUserName(String newUpdateUserName) {
		updateUserName = newUpdateUserName;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	/** @param newUpdateTime */
	public void setUpdateTime(java.util.Date newUpdateTime) {
		updateTime = newUpdateTime;
	}

	public String getBlank0() {
		return blank0;
	}

	/** @param newBlank0 */
	public void setBlank0(String newBlank0) {
		blank0 = newBlank0;
	}

	public String getBlank1() {
		return blank1;
	}

	/** @param newBlank1 */
	public void setBlank1(String newBlank1) {
		blank1 = newBlank1;
	}

	public String getBlank2() {
		return blank2;
	}

	/** @param newBlank2 */
	public void setBlank2(String newBlank2) {
		blank2 = newBlank2;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	@Transient
	public Dictionary getParent() {
		return parent;
	}

	public void setParent(Dictionary parent) {
		this.parent = parent;
	}

	@Transient
	public boolean isRoot() {
		if (id != null && !"".equals(id) && id.equals("root_id")) {
			return true;
		}
		return false;
	}

	@Transient
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Dictionary [dictParentId=" + dictParentId + ", dictName=" + dictName + ", dictFullName=" + dictFullName + ", dictChineseName=" + dictChineseName + ", dictValue=" + dictValue + ", dictRemark=" + dictRemark
				+ ", dictCode=" + dictCode + ", dictNo=" + dictNo + ", dictIsStart=" + dictIsStart + ", isParent=" + isParent + ", dictLevel=" + dictLevel + ", dictSort=" + dictSort + ", dictOwner=" + dictOwner + ", createTime="
				+ createTime + ", createUserId=" + createUserId + ", createUserName=" + createUserName + ", updateUserId=" + updateUserId + ", updateUserName=" + updateUserName + ", updateTime=" + updateTime + ", blank0="
				+ blank0 + ", blank1=" + blank1 + ", blank2=" + blank2 + ", enableFlag=" + enableFlag + ", parent=" + parent + ", pid=" + pid + ", id=" + id + "]";
	}

}
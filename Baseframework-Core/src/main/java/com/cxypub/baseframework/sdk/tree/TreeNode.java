package com.cxypub.baseframework.sdk.tree;

/**
 * @Title: TreeVo.java
 * @Package com.oao.oao_coreservice.common.base.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2015 
 * Company:上海追月信息科技有限公司
 * 
 * @author oaoCoder-束文奇
 * @date 2015-5-8 上午11:40:19
 * @version V1.0
 */

import java.io.Serializable;

/**
 * @ClassName: TreeNode
 * @Description: 树节点
 * @author 徐飞
 * @date 2015-5-8 上午11:40:19
 *
 */

public class TreeNode implements Serializable {

	private static final long serialVersionUID = -2080226772874209002L;

	/**本节点Id**/
	private String id;

	/**父节点Id**/
	private String parentTId;

	/**节点名称**/
	private String name;

	/**节点url**/
	private String url;

	/**节点是否被选中**/
	private String checked;

	/**节点是否是父节点**/
	private String isParent;

	/**
	 * getter method
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * setter method
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getParentTId() {
		return parentTId;
	}

	public void setParentTId(String parentTId) {
		this.parentTId = parentTId;
	}

	/**
	 * getter method
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter method
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter method
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * setter method
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * getter method
	 * @return the isParent
	 */
	public String getIsParent() {
		return isParent;
	}

	/**
	 * setter method
	 * @param isParent the isParent to set
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", parentTId=" + parentTId + ", name=" + name + ", url=" + url + ", checked=" + checked + ", isParent=" + isParent + "]";
	}

}

package com.cxypub.baseframework.sdk.dictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cxypub.baseframework.sdk.dictionary.entity.Dictionary;
import com.cxypub.baseframework.sdk.dictionary.service.IDictionaryService;
import com.cxypub.baseframework.sdk.search.Page;
import com.cxypub.baseframework.sdk.service.ABaseService;
import com.cxypub.baseframework.sdk.tree.TreeNode;
import com.cxypub.baseframework.sdk.util.EmptyUtils;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: DictionaryServiceImpl
 * @Package com.cxypub.baseframework.sdk.dictionary.service.impl
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/1/6 13:54
 */
@Service
public class DictionaryServiceImpl extends ABaseService implements IDictionaryService {

	@Override
	public List<Dictionary> findByParentId(String parentId) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("select a.id as id from Dictionary a where a.dictParentId = :parentId order by a.dictSort asc");
		Map<String, Object> map = new HashMap<String, Object>();
		if (EmptyUtils.isNullOrEmpty(parentId)) {
			parentId = "0";
		}
		map.put("parentId", parentId);
		List<Object> result = this.dao.find4Hql(jpql.toString(), map);
		map.put("parentId", parentId);
		return this.dao.find4Hql(jpql.toString(), map);
	}

	@Override
	public List<TreeNode> getTreeNodesByParentId(String parentId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select a.id as id, a.dictParentId as parentTId,a.dictName as name,a.isParent as isParent from Dictionary a where a.dictParentId = :parentId order by a.dictSort asc");
		Map<String, Object> map = new HashMap<String, Object>();
		if (EmptyUtils.isNullOrEmpty(parentId)) {
			parentId = "0";
		}
		map.put("parentId", parentId);
		List<TreeNode> result = this.dao.findToDTO4Hql(TreeNode.class, hql.toString(), map);
		return result;
	}

	@Override
	public Page<Dictionary> findByPage(Dictionary dictionaryVo, Page<Dictionary> page) {
		return null;
	}

	@Override
	public Dictionary get(String id) {
		return this.dao.get(Dictionary.class, id);
	}

	@Override
	public void save(Dictionary dictionary) {

	}

	@Override
	public void delete(String ids) {

	}

	@Override
	public void updateStatus(String id, String status) {

	}

	@Override
	public Dictionary getByDicNo(String dictNo) {
		return null;
	}

	@Override
	public List<TreeNode> findTreeByParentIdCash(String parentId) {
		return null;
	}

	@Override
	public List<Dictionary> findAllSiteDictionaryByDictNo(String dictNo) {
		return null;
	}

	@Override
	public Integer getMaxSortNo(String positionId) {
		return null;
	}

	@Override
	public Integer getMinSortNo(String positionId) {
		return null;
	}

	@Override
	public void initCacheAll() {

	}
}

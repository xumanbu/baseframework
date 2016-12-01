package com.cxypub.baseframework.sdk.dictionary.service;

import java.util.List;

import com.cxypub.baseframework.sdk.dictionary.entity.Dictionary;
import com.cxypub.baseframework.sdk.search.Page;
import com.cxypub.baseframework.sdk.tree.TreeNode;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: IDictionaryService
 * @Package com.cxypub.baseframework.sdk.dictionary.service
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/1/6 13:49
 */
public interface IDictionaryService {
	/**
	 * @Title: findByParentId
	 * @Description: 根据父Id获取所有子节点id
	 * @param parentId
	 * @return  传人参数
	 * @throws
	 */
	List<Dictionary> findByParentId(String parentId);

	/**
	 * @Title: getTreeNodesByParentId
	 * @Description: 获取父节点下的所有子节点
	 * @param parentId
	 * @return
	 * @author 徐飞
	 */
	List<TreeNode> getTreeNodesByParentId(String parentId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查询数据字典
	 * @param dictionaryVo
	 * @param page
	 * @return  传人参数
	 * @throws
	 */
	Page<Dictionary> findByPage(Dictionary dictionaryVo, Page<Dictionary> page);

	/**
	 * @Title: get
	 * @Description: 根据ID获得指定数据字典
	 * @param id
	 * @return  传人参数
	 * @throws
	 */
	Dictionary get(String id);

	/**
	 *
	 * @Title: save
	 * @Description: 保存一个数据字典
	 * @param dictionary  传人参数
	 * @throws
	 */
	void save(Dictionary dictionary);

	/**
	 *
	 * @Title: save
	 * @Description: 删除一个指定ids的数据，多个id用，分割:1123,2312,12312
	 * @param dictionary  传人参数
	 * @throws
	 */
	void delete(String ids);

	/**
	 * @Title: updateStatus
	 * @Description: 启用或禁用数据字典
	 * @param id
	 * @param status  传人参数
	 * @throws
	 */
	void updateStatus(String id, String status);

	/**
	 * @Title: getByDicNo
	 * @Description: 根据数据字典的code获得数据集，按照规定dictno唯一
	 * @param dictNo
	 * @return  传人参数
	 * @throws
	 */
	Dictionary getByDicNo(String dictNo);

	/**
	 *
	 *
	 * @Title: findTreeByParentIdCash
	 * @Description: 根据 父id 查询树形结构 带缓存查询
	 * @param parentId
	 * @return   传入参数
	 * @author   pm-陈鹏
	 * @createTime 2015-7-10 下午04:28:59
	 * @throws
	 */
	public List<TreeNode> findTreeByParentIdCash(String parentId);

	/**
	 *
	 *
	 * @Title: findAllSiteDictionaryByDictNo
	 * @Description: 根据站点 no 查询所有数据字典数据
	 * @param dictNo
	 * @return   传入参数
	 * @author   pm-陈鹏
	 * @createTime 2015-7-16 上午11:51:50
	 * @throws
	 */
	public List<Dictionary> findAllSiteDictionaryByDictNo(String dictNo);

	/**
	 *
	 * @Title: getMaxSortNo
	 * @author:徐飞
	 * @Description: 获取一个同一位置下最大的排序号
	 * @param pId
	 * @return
	 * @throws
	 */
	Integer getMaxSortNo(String positionId);

	/**
	 *
	 * @Title: getMaxSortNo
	 * @author:徐飞
	 * @Description: 获取一个同一位置下最小的排序号
	 * @param pId
	 * @return
	 * @throws
	 */
	Integer getMinSortNo(String positionId);

	void initCacheAll();
}

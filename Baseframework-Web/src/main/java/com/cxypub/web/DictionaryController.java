package com.cxypub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxypub.baseframework.sdk.action.spring.BaseController;
import com.cxypub.baseframework.sdk.dictionary.service.IDictionaryService;
import com.cxypub.baseframework.sdk.tree.TreeNode;

/**
 * @ClassName: DictionaryController
 * @Description: 数据字典controller
 * @author 徐飞
 * @date 2016年1月19日 上午11:12:31
 *
 */
@Controller
@RequestMapping("/system/dictionary")
public class DictionaryController extends BaseController {

	@Autowired
	protected IDictionaryService dictionaryService;

	/**
	 * @Title: manageView
	 * @Description: 数据字段管理首页页面
	 * @param model
	 * @return
	 * @author 徐飞
	 */
	@RequestMapping("/manage")
	public String manageView(Model model) {

		return "/system/dictionary/dictionary-manage";
	}

	/**
	 * @Title: inputView
	 * @Description: 数据字典添加页面
	 * @param model
	 * @return
	 * @author 徐飞
	 */
	@RequestMapping("/input")
	public String inputView(String id, Model model) {

		return "/system/dictionary/dictionary-input";
	}

	/**
	 * @Title: tree
	 * @Description: 获取树节点
	 * @param pid
	 * @return
	 * @author 徐飞
	 */
	@RequestMapping("/treeNodes")
	@ResponseBody
	public List<TreeNode> tree(String pid) {
		List<TreeNode> resultList = dictionaryService.getTreeNodesByParentId(pid);
		return resultList;
	}
}

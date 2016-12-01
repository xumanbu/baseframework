package com.cxypub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxypub.baseframework.sdk.dictionary.entity.Dictionary;
import com.cxypub.baseframework.sdk.dictionary.service.IDictionaryService;

/**
 * @ClassName: IndexContorller
 * @Description: 登录后系统首页
 * @author 徐飞
 * @date 2015年12月30日 上午9:40:52
 *
 */
@Controller
@RequestMapping("/system")
public class IndexController {

	@Autowired
	private IDictionaryService dictionaryService;

	@RequestMapping("/index")
	public String index() {
		List<Dictionary> result = dictionaryService.findByParentId(null);
		System.out.println(result.size() + "=======");
		return "index/index";
	}

	@RequestMapping("/home")
	public String home() {
		return "index/home";
	}
}

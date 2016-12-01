//package com.cxypub.baseframework.sdk.action.demo.spring;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.cxypub.user.entity.PublicUser;
//import com.cxypub.user.service.IPublicUserService;
//
///**
// * <p> Title: 智能营区综合管控系统-AttendDeviceAction.java </p>
// * <p> Description: Intelligent Camp Monitoring System </p>
// * <p> Copyright: Copyright (c) 2014 </p>
// * <p> Company: www.pingtech.com.cn </p>
// * @author xuf
// * @version 4.0
// * @date  2014-5-22 上午10:11:57
// */
//@Controller
//@RequestMapping("/system/user")
//public class UserController {
//
//	@Autowired
//	private IPublicUserService userService;
//
//	@RequestMapping("/list.do")
//	public String list() {
//		Subject subject = SecurityUtils.getSubject();
//		Session session = subject.getSession();
//		session.setAttribute("xufei", "111111111111111111");
//		System.out.println("======================222");
//		userService.patchDelete("");
//		return "system/user/user-list";
//	}
//
//	@RequestMapping("get.json")
//	@ResponseBody
//	public PublicUser get(String id) {
//		System.out.println(userService.getPublicUserById(id));
//		return userService.getPublicUserById(id);
//	}
// }

//package com.cxypub.baseframework.sdk.action.demo.struts2;
//
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.Result;
//import org.apache.struts2.convention.annotation.Results;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.cxypub.baseframework.sdk.action.struts2.Struts2Action;
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
//@Namespace("/user")
//@Results({ @Result(name = "list", location = "index.jsp"), @Result(name = "input", location = "/znyq/attendance/device/device-input.jsp") })
//public class UserAction extends Struts2Action {
//
//	private static final long serialVersionUID = 8558361052653998727L;
//
//	@Autowired
//	private IPublicUserService userService;
//
//	private PublicUser user;
//
//	public String list() {
//		// userService = SpringContextUtil.getBean("userServiceImpl");
//		userService.patchDelete("");
//		user = new PublicUser();
//		// userService.save(user);
//		// userService.save(user);
//		return LIST;
//	}
//
//	public PublicUser getUser() {
//		return user;
//	}
//
//	public void setUser(PublicUser user) {
//		this.user = user;
//	}
//
// }

package com.cxypub.web;

import java.io.IOException;
import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxypub.baseframework.sdk.action.AjaxMessage;
import com.cxypub.baseframework.sdk.action.spring.BaseController;
import com.cxypub.baseframework.sdk.cache.SimpleCache;
import com.cxypub.baseframework.sdk.security.authcode.AuthCodeBuilder;
import com.cxypub.baseframework.sdk.security.base.LoginToken;
import com.cxypub.baseframework.sdk.solrj.SolrClientConnecter;
import com.cxypub.baseframework.sdk.util.EmptyUtils;

/**
 * @ClassName: LoginController
 * @Description: 登录，退出，功能
 * @author 徐飞
 * @date 2015年12月30日 上午10:47:39
 *
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	@Qualifier("authCodeStyle1")
	private AuthCodeBuilder authCodeBuilder;

	@Autowired
	@Qualifier("shardedJedisClient")
	private SimpleCache simpleCache;

	@Autowired
	private SolrClientConnecter solrClientConnecter;

	/**
	 * @Title: loginView
	 * @Description: 跳转到登录页面
	 * @return
	 * @author 徐飞
	 */
	@RequestMapping(value = "/login")
	public String loginView() {
		solrClientConnecter.search("你好");
		return "login/login";
	}

	/**
	 * @Title: authCode
	 * @Description: 生成验证码图片
	 * @author 徐飞
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login/authCode")
	public void authCode() {
		System.out.println(SecurityUtils.getSubject().getPrincipal());
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字串
		String verifyCode = authCodeBuilder.generateAuthCode(4);
		Session session = this.getSession();
		session.setAttribute("loginAuthCode", verifyCode.toLowerCase());
		// 生成图片
		int w = 200, h = 80;
		try {
			authCodeBuilder.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: loginout
	 * @Description: 退出页面
	 * @return
	 * @author 徐飞
	 */
	@RequestMapping(value = "/loginout")
	public String loginout() {
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isAuthenticated());
		System.out.println(subject.isRemembered());
		subject.logout();
		return "login/login";
	}

	/**
	 * @Title: dologin
	 * @Description: 执行登录的接口
	 * @param loginToken：LoginName、Password
	 * @return
	 * @author 徐飞
	 */
	@RequestMapping(value = "/dologin")
	@ResponseBody
	public AjaxMessage dologin(LoginToken loginToken, String authCode) {
		String authCodeSession = (String) this.getSession().getAttribute("loginAuthCode");
		if (EmptyUtils.isNullOrEmpty(authCode) || !authCode.toLowerCase().equals(authCodeSession)) {
			return AjaxMessage.error("验证码错误！");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(loginToken.getLoginName(), loginToken.getPassword());
		token.setRememberMe(false);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("用户名或密码错误");
		}
		Serializable sessionId = subject.getSession().getId();
		System.out.println(sessionId);
		return AjaxMessage.success("登录成功！");
	}
}

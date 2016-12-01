<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/meta.jsp"%>
<%@ include file="/WEB-INF/includes/csslibs.jsp"%>
</head>

<body class="ts_login_bg">
	<div class="ts_login_con">
		<div class="ts_login_logo"></div>
		<div class="ts_login_content">
			<div class="ts_login_content_in">
				<ul class="ts_login_list clearfix">
					<li><span class="iconfont icon-13dengluyonghuming"></span><input type="text" type="text" id="loginName"
						name="loginName" value="${loginName}" class="ts_login_input validate[required,maxSize[20]]" maxlength="20" /></li>
					<li><span class="iconfont icon-mima"></span><input type="password" id="passWord" name="passWord"
						value="${passWord}" class="ts_login_input validate[required,maxSize[20]]" maxlength="20" /></li>
					<li><span class="iconfont icon-code"></span><input type="text" id="authCode" name="authCode"
						class="ts_login_input w90 validate[required],custom[onlyLetterNumber],maxSize[4]" maxlength="4" /> <img src=""
						class="ts_login_yzm" id="randompicture" /> <a href="javascript:login.changePicture();" class="ts_login_change">换一张？</a></li>
					<li><input id="loginBtn" type="button" class="ts_login_btn_blue" value="登录" /> <input
						type="button" onclick="myReset();" class="ts_login_btn_white btn_gradual" value="重置" /></li>
					<li><span class="ts_red" id="failText">${shiroLoginFailure}</span></li>
				</ul>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/includes/jslibs.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/login/login.js"></script>
</html>

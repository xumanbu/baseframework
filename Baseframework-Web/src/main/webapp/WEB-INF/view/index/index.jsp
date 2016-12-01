<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/meta.jsp"%>
<%@ include file="/WEB-INF/includes/csslibs.jsp"%>
<%@ include file="/WEB-INF/includes/jslibs.jsp"%>
<style type="text/css">
#pop-bgs{position: absolute;left: 0;top: 0;width: 100%;height: 100%;background: #4a6b8a; opacity: 0.5;filter:alpha(opacity:50);display: none;}
</style>
<title>OAO定制后台</title>
<script type="text/javascript">
window.onload=function(){
	if(window.top !== window.self){ 
		window.top.location = window.location;
	}
}
</script>
</head>
<body bgcolor="f6f8fa" class="clearfix" style="overflow: hidden;">
    <!-- body-start -->
    <!-- head-start -->
    <div class="ts_header w_p100 bg_base f_yh clearfix">
        <div class="ts_logo fl"><a href="#"><img src="${ctx}/resources/img/globleimg/logo.jpg" alt="logo" class="fl"></a></div>
        <div class="ts_set fr pt20 f16">
            <a href="javascript:;"><span class="iconfont icon-guanli f24 mr3"></span>设置<i class="iconfont icon-xiangxia f12"></i></a>
            <div class="ts_set_list f12">
                <a href="javascript:void(0);" class="js_ts_reset_password">更改密码</a>
                <a href="${ctx}/loginout" class="js_ts_exit">退出</a>
            </div>
        </div>
        <div class="ts_login_time fr pr20 pt20"><p class="f16 plr5 b_d_r3">上次登录时间：${lastLoginTime}   ${weekOfDate} </p></div>
        <div class="ts_info_bar_l pl20 pt20 fr f16" style="color: #cddbee;">
            <div class="fl"><span class="iconfont icon-wo2 f24 mr3"></span>您好欢迎您登录，用户名：${shiroUser.loginName}<span class="pl30"></span></div>
        </div>
     </div>
    <!-- <div class="ts_info_bar w_p100 clearfix">
        <div class="ts_info_bar_l pl20 fl">
            <div class="fl"><span class="iconfont icon-wo2 f24"></span>您好欢迎您登录，用户名：鑫源世家<span class="pl30">角色：</span><span class="ts_blue">管理员</span> </div>        
            <div class="fl pl30"><span class="iconfont icon-tuichu f20"></span> <a href="#" class="ts_blue">退出</a></div>
        </div>
        <div class="ts_add_person fr plr15">
            <a href="javascript:;" class="btn_gradual"><span class="iconfont icon-add f12 f_b"></span>添加产品</a>
        </div>
    </div> -->
    <!-- head-end -->
    <!--left nav-start-->
    <div id="leftNav" class="ts_sidebar fl " style="height: 808px;">
         <ul class="ts_side_list f14 js-side-scroll" data-nav="tab" data-target="#tabUI" id="menuList">
        	<li class="ts_s_i_0 f12">管理所有类目</li>
            <li data-tit="代理商管理" data-url="${ctx}/agent/list">
            	<a href="javascript:void(0);"><span class="iconfont icon-guanyuwo"></span>代理商管理<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="代理商业绩" data-url="${ctx}/agent/performance/list" class="active">
            	<a href="javascript:void(0);"><span class="iconfont icon-yiyoubiaodan"></span>代理商业绩<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="代理商设置" data-url="${ctx}/agentSettings/list">
            	<a href="javascript:void(0);"><span class="iconfont icon-wodedailishang"></span>代理商设置<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="使用者管理" data-url="${ctx}/owner/list">
           	 	<a href="javascript:void(0);"><span class="iconfont icon-shiyongguanli"></span>使用者管理<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="产品管理" data-url="${ctx}/product/list">
            	<a href="javascript:void(0);"><span class="iconfont icon-chanpin"></span>产品管理<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="模块管理" data-url="${ctx }/module/list">
            	<a href="javascript:void(0);"><span class="iconfont icon-gongnengzujian1"></span>模块管理<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="组件管理" data-url="${ctx}/component/list">
            	<a href="javascript:void(0);"><span class="iconfont icon-gongnengzujian"></span>组件管理<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="资源管理" data-url="${ctx}/resource/initialize">
            	<a href="javascript:void(0);"><span class="iconfont icon-scguanliyuanfenpei"></span>资源管理<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="管理员管理" data-url="${ctx}/user/list">
            	<a href="javascript:void(0);"><span class="iconfont icon-iconfontguanliyuanguanl"></span>管理员管理<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="地区管理" data-url="${ctx}/area/initialize">
            	<a href="javascript:void(0);"><span class="iconfont icon-iconfontdiquguanli"></span>地区管理<i>&gt;&gt;</i></a>
            </li>
         	<li data-tit="计划任务" data-url="${ctx}/schedule/listInitialize">
            	<a href="javascript:void(0);"><span class="iconfont icon-scguanliyuanfenpei"></span>计划任务<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="数据字典" data-url="${ctx}/system/dictionary/manage">
            	<a href="javascript:void(0);"><span class="iconfont icon-chanpincanshu"></span>数据字典<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="提现审核" data-url="${ctx}/agent/checklist">
            	<a href="javascript:void(0);"><span class="iconfont icon-chanpinshenhe"></span>提现审核<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="官方设置" data-url="${ctx}/office/set/bannerList">
                <a href="javascript:void(0);"><span class="iconfont icon-pc"></span>官网设置<i>&gt;&gt;</i></a>
            </li>
            <li data-tit="更新日志" data-url="${ctx}/productupdatelog/list">
                <a href="javascript:void(0);"><span class="iconfont icon-yonghuxieyi"></span>更新日志<i>&gt;&gt;</i></a>
            </li>
        </ul>
    </div>
    <!--left nav-end-->
	<!--content-start -->
    <div class="ts_content fl" style="width: 1701px;">
    	<div class="ts_content_in">

            <div class="ts_tab w_p100" id="tabUI">
                <!--tab nav-->
                <ul class="ts_tab_list clearfix mt15 js_tab_list js-tab-nav">
                    <li class="active" data-tit="首页"><span>首页</span></li>
                </ul>
                <!--tab content-strat-->
                <div class="ts_tab_plane clearfix js_tab_plane js-tab-con">
                    <!--tab pane-strat-->
                    <div class="ts_tab_pane js_tab_pane active js-tab-panel" style="z-index: 101;position: relative;">
                        <iframe class="tabIframe" src="" style="width:100%;height: 100%;border: 0px; z-index: 101;position: relative;"></iframe>
                    </div>
                </div>
                <!--tab content-end-->
            </div>
        </div>
    </div>
    <!--content-end-->
    <!--个人资料弹窗-start-->
    <div class="ts_pop_up js_ts_person">
        <a href="javascript:;" class="ts_pop_close">×</a>
		<div class="ts_pop_tit">
        	个人资料
        </div>
        <div class="ts_pop_con p15 f14 clearfix">
        	<ul class="ts_person w_p100 clearfix">
            	<li class="ts_person_l t_r fl">用户名：</li>
                <li class="ts_person_r fl pl40">鑫苑世家</li>
            </ul>
        	<ul class="ts_person w_p100 clearfix">
            	<li class="ts_person_l t_r fl">角  色：</li>
                <li class="ts_person_r fl pl40 ts_red">超级管理员</li>
            </ul>
        	<ul class="ts_person w_p100 clearfix">
            	<li class="ts_person_l t_r fl">登录次数：</li>
                <li class="ts_person_r fl pl40">56896次</li>
            </ul>
        	<ul class="ts_person w_p100 clearfix">
            	<li class="ts_person_l t_r fl">登录时间：</li>
                <li class="ts_person_r fl pl40">2015年07月19日</li>
            </ul>
       		<a href="javascript:void(0);" class="ts_modfiy_password fl mt15 f12 js_ts_reset_password">修改密码</a>
        </div>
    </div>
    <!--个人资料弹窗-end-->
    
    <!--更改密码-start-->
    <form id="userForm" action="${ctx }/user/save" method="post">
    <input type="hidden" id="loginId" name="id" value="${shiroUser.id }">
    <div class="ts_pop_up ts_pop_540 js_ts_pscon">
        <a href="javascript:;" class="ts_pop_close">×</a>
		<div class="ts_pop_tit">更改密码</div>
        <div class="ts_pop_con p15 f14">
        	<ul class="ts_password_list w_p100 clearfix">
            	<li class="w_p25 t_r fl">原 密 码：</li>
                <li class="fl pl10"><input autocomplete="off" type="password" name="oldpassword" id="oldpassword"  placeholder="输入您的旧密码" 
                		class="validate[required,funcCall[checkPassword],minSize[6]] ts_input_40 ts_int270" maxlength="20"></li>
            </ul>
        	<ul class="ts_password_list w_p100 clearfix">
            	<li class="w_p25 t_r fl">新 密 码：</li>
                <li class="fl pl10"><input autocomplete="off" type="password" name="password" id="password"  placeholder="输入您要设置的密码" 
                		class="validate[required,minSize[6]] ts_input_40 ts_int270" maxlength="20"></li>
            </ul>
        	<ul class="ts_password_list w_p100 clearfix">
            	<li class="w_p25 t_r fl">确认新密码：</li>
                <li class="fl pl10"><input autocomplete="off" type="password" name="password2" id="password2"  placeholder="请再输入一次密码" 
                		class="validate[required,equals[password],minSize[6]] ts_input_40 ts_int270" ></li>
            </ul>
        	<ul class="ts_password_list w_p100 clearfix">
            	<li class="w_p25 t_r fl">&nbsp;</li>
                <li class="fl pl10"><input type="button" class="ts_blue_btn_40 js_psbtn_com" value="确认修改"></li>
            </ul>

        </div>
    </div>
    </form>
    <!--更改密码-end-->
    
    <!--更改密码成功-start-->
    <div class="ts_pop_up ts_pop_540 js_ts_pscon_suc">
        <a href="javascript:;" class="ts_pop_close">×</a>
		<div class="ts_pop_tit">更改密码</div>
        <div class="ts_pop_con p15 f14 t_c">
        	<p class="pt50 f16"><span class="iconfont icon-zhengque f30 ts_green "></span> 密码修改成功</p>
			<p class="ts_tip f12">您的新密码已修改成功，敬请牢记。</p>
            <input type="button" class="ts_blue_btn_40 f16 ts_comfirm js_ts_pss_com" value="确  认">
        </div>
    </div>
    <!--更改密码成功-end-->
    
    <!--弹窗
    <div class="ts_pop_up">
        <a href="javascript:;" class="ts_pop_close">&times;</a>
		<div class="ts_pop_tit">
        	个人资料
        </div>
        <div class="ts_pop_con p15 f14">

        </div>
        <div class="ts_pop_btn">
        
        </div>
    </div>
    -->
    <div id="pop-bgs"></div>
</body>
</html>

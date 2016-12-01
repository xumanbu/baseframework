<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/meta.jsp"%>
<%@ include file="/WEB-INF/includes/csslibs.jsp"%>
<title>数据字典维护</title>
<style>
.top {
	background: #60C;width: 100%;float: left;overflow: auto
}
.left {
	overflow:auto;height:500px; red;width:20%;float:left;border: 1px solid #bdc8dc;
}
.right {
	overflow:auto;height:100%;width:78%;float:left;margin-left: 1%
}
</style>
</head>
<body bgcolor="f6f8fa">
<!--标题 star-->
<h1 class="ts_con_tit clearfix"><span class="iconfont icon-guanyuwo f24 plr15"></span>数据字典维护
    <p class="ts_add_person fr plr15 f12 ">
        <a href="javascript:tools.addData('${ctx}/dictionary/add',true);" class="btn_gradual"><span class="iconfont icon-add f12 f_b mr3"></span>新增</a>
    </p>
</h1>
<!--标题 end-->
<form id ="searchForm" action="${ctx}/dictionary/list" method="post">
<!--查询条件 star-->
<div class="ts_search_form mt15 clearfix">

  <input type="hidden"  id ="pid" name="pid" value="${pid}" />
  <div class="ts_f_short pr20 mb5">字典名称： <span class="show_inline_block">
    <input class="ts_input_28 ts_int135 pr20" type="text"  name="dictName"  value="" />
    </span> </div>
  <div class="ts_f_short pr20 mb5">字典标识： <span class="show_inline_block">
    <input class="ts_input_28 ts_int135 pr20" type="text"  name="dictNo" value="" />
    </span> </div>
  <input type="button" class="ts_blue_btn_30 fl" onClick="tools.restPageNumber();tools.doSearch()" value="搜索" />
  <input type="button" class="ts_white_btn_30 fl" onClick="tools.resetTable('${ctx}/dictionary/index')" style="margin-left:10px" value="重置" />
</div>

<!--查询条件 end-->

<!--主页面 end-->
<div id="main">
	<div id="treeContent" class="left">
		<ul id="treeRoot" data-url= "${ctx}/dictionary/tree" root-id="0" root-name="数据字典" class="ztree" style="display:none"></ul>
	</div>
	<div id="mainContent" class="right dataTable"></div>
</div>
<!--主页面 end-->
</form>
<%@ include file="/WEB-INF/includes/jslibs.jsp"%>
</body>
</html>

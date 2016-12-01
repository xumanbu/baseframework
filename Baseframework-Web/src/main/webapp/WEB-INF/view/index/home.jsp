<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/meta.jsp"%>
<%@ include file="/WEB-INF/includes/csslibs.jsp"%>
<title>总后台系统首页</title>
</head>
<body bgcolor="f6f8fa">
<!--content-start -->
       <div class="index_in pt15">
           <div class="index_tab_li w_p50 pb15 fl">
               <div class="ts_panel">
                   <div class="panel_hd p15">
                       <p>产品销量</p>
                   </div>
                   <div class="panel_bd p15">
                       <ul class="ts_table w_p100 clearfix"> 
                           <li class="tit clearfix">
                               <span class="span_01">添加时间</span>
                               <span class="span_02">产品名称</span>
                               <span class="span_03">销量</span>
                               <span class="span_04">价格</span>
                           </li>
                           <c:forEach items="${topList}" var="product" varStatus="st">
                           
                           		<li class="clearfix">
	                               <span class="span_01"><fmt:formatDate pattern="yyyy-MM-dd" value="${product.createTime}"/>&nbsp;&nbsp;<fmt:formatDate pattern="HH:mm:ss" value="${product.createTime}"/></span>
	                               <span class="span_02"><oao:substring value="${product.name}" length="24"/></span>
	                               <span class="span_03"><fmt:formatNumber pattern="#.#" value="${product.salesVolume}"/></span>
	                               <span class="span_04"><fmt:formatNumber pattern="###,###.## 元" value="${product.salesPrice}"/></span>
	                           </li>
                           </c:forEach>
                       </ul>
                   </div>
               </div>
           </div>
           <div class="index_tab_li w_p50 fl pl15 pb15">
               <div class="ts_panel">
                   <div class="panel_hd p15 clearfix">
                       <p class="fl">代理商变化</p>
                       <a href="${ctx }/agent/list" class="blue_a fr">详情&nbsp;&gt;</a>
                   </div>
                   <div class="panel_bd">
                        <div class="echart_div" id="agentChart" style="width:100%;height:345px;"></div>

                   </div>
               </div>
           </div>
           <div class="index_tab_li w_p100 pb15 fl">
               <div class="ts_panel">
                   <div class="panel_hd p15 clearfix">
                       <p class="fl">年度使用者变化</p>
                       <a href="${ctx }/owner/list" class="green_a fr">详情&nbsp;&gt;</a>
                   </div>
                   <div class="panel_bd">
                       <div class="echart_div" id="yearChart" style="width:100%;height:345px;"></div>
                   </div>
               </div>
           </div>
       </div>
<!--content-end-->
</body>
</html>    

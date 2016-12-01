// JavaScript Document

/***********************宽高获取**************************/	
$(function(){
	var ifrMask = $("iframe").contents().find(".ts_mask");
	if(ifrMask.length == 0){
		$("body").append("<div class=\"ts_mask\"></div>");
	}	
	
	$(window).on({
		load:function(){
//			winSize();	
			clmParWidth();
			ctrlSide();

		},
		resize:function(){
//			winSize();
			clmParWidth();
			ctrlSide();
		}	
		
	});
	
	setPop = {
		mask : $(".ts_mask"),					//遮罩层
		colseBtn : $(".ts_pop_close"),			//关闭按钮
		popUp : $(".ts_pop_up"),				//弹出层
		personBtn : $(".js_ts_person_set"),		//个人信息按钮
		psBtn : $(".js_ts_reset_password"),		//更改密码按钮
		personCon : $(".js_ts_person"),			//个人信息弹出层
		psCon : $(".js_ts_pscon"),				//更改密码弹出层
		psBtnCom : $(".js_psbtn_com"),			//确认修改密码
		psSucCon : $(".js_ts_pscon_suc"),		//更改密码成功弹出层
		psBtnComSuc : $(".js_ts_pss_com")		//更改密码成功弹出层
	};
	/*关闭弹窗按钮*/	
	setPop.colseBtn.click(function(){
		setPop.mask.hide();
		setPop.popUp.hide();
		$("iframe").contents().find(".ts_mask").hide();
		});	
		
	/*弹出个人信息*/
	setPop.personBtn.click(function(){
		setPop.mask.show();
		setPop.personCon.show(300);
		});
	
	
	/*blur*/
	setPop.psCon.find("input").blur(function(){
		var id = $(this).attr("id");
		if(id && $("#userForm").validationEngine("validateField", "#"+id)){
			$("." + id+ "formError").css({"z-index":"10001"});
		};
	});
		
	/*弹出更改密码窗*/
	setPop.psBtn.click(function(){
		$("#userForm")[0].reset();
		$("#userForm").validationEngine({scroll:false,promptPosition: "bottomRight"});
		setPop.mask.show();
		$("iframe").contents().find(".ts_mask").show();
		$("iframe").contents().find(".ts_pop_up").hide();
		setPop.personCon.hide();
		setPop.psCon.show(300);
	});
			
	/*弹出更改密码成功窗*/
	setPop.psBtnCom.click(function(){
		if(!$("#userForm").validationEngine("validate")){
			$(".formError").css({"z-index":"10001"});
			return
		};
		var result = updatePassWord();
		if(result){
			setPop.psCon.hide();
			setPop.psSucCon.show(300);
		}
	});
		
	/*确认-关闭弹窗按钮*/	
	setPop.psBtnComSuc.click(function(){
		setPop.mask.hide();
		setPop.popUp.hide();
		$("iframe").contents().find(".ts_mask").hide();
	});	
	
	//修改密码弹出框关闭按钮点击后取消校验
	$("#userForm").find(".ts_pop_close").click(function(){
		$('#userForm').validationEngine('hide')
	})
});

//计算布局
function winSize(){	
	var screenWH={};
	var sideWH = {};
	screenWH = {

		SW : $(window).width(),          //获取屏幕的宽高

		SH : $(window).height(),
		
		BW : document.body.scrollWidth,
		
		BH : document.body.scrollHeight

	};
	
	sideWH = {
		sideBar : $(".ts_sidebar"),
		ringtCon : $(".ts_content")
		};
	
//	if(screenWH.SH > screenWH.BH){
//		sideWH.sideBar.height(screenWH.SH-124);	
//		  }else{
//			sideWH.sideBar.height(screenWH.BH-124);	
//		}
	
	sideWH.ringtCon.width(screenWH.BW-218);
	/*20151214 调整头部*/
	if(screenWH.BW < 1400){
		$(".ts_login_time").hide();
	}else{
		$(".ts_login_time").show();
	}
	
	ctrlSide();
};

//修改密码功能
function updatePassWord(){
	var result = false;
   if($("#userForm").validationEngine("validate")){
	   var password=$("#password").val();
	   var id=$("#loginId").val();
	   $.ajax({
			type : "POST",
			url : ctx+"/user/updatePassword",
			data :{"id":id,"password":password},
			async : false,
			success : function(data) {
				if(data){
					result = true;
				}else{
					warningPop({
		                title : "提示：",
		                content : '密码修改失败！',
		                btngray : '确定',
		                cancelFn : function(){
							
		                }
		           });
				}
			}
				
		});
   }
   return result;
}

//验证旧密码是否正确
function checkPassword(){
	var password=$("#oldpassword").val();
	var id=$("#loginId").val();
	var flag=false;
	$.ajax({
		type : "POST",
		url : ctx+"/user/checkPassword",
		data :{"id":id,"password":password},
		async : false,
		success : function(data) {
			flag=data;
		}
			
	});
	if(!flag){
		return "* 旧密码输入不正确";
	}
	
}

/***********************设置弹窗**************************/

	//个人信息
	var setPop={};
	

/*************************tab 选项卡*****************************/	
$(function(){
	$("body").on("click",".js_tab_list > li",function(){
		var index = $(this).index();
		var pane = $(this).parents(".js_tab_list").eq(0).parent().find(".js_tab_plane").eq(0).find(" > .js_tab_pane");
		$(this).addClass("active").siblings().removeClass("active");
		pane.eq(index).addClass("active").siblings().removeClass("active");
//		winSize();	
	});
	
	/*20151117*/
	$("body").on("click",".js-open-pop",function(){
		var id = $(this).attr("data-target");
		$(id).show();
		setPop.mask.show();
	});

	$("body").on("click",".js-cancel",function(){
		setPop.mask.hide();
		$(this).parents(".ts_pop_up").hide();
	});
})



/**********************右侧 bg_white 的最小高度******************************/
	var bgWhite={};
	bgWhite = {
		bgHeight : $(".js_bg_white_height"),					//白色背景层
		wHeight : $(window).height(),							//浏览高度
		oHeight : 260											// 头部和标题及底部空白高度
	};

	bgWhite.bgHeight.css("min-height",bgWhite.wHeight-bgWhite.oHeight);
	

/**********************添加成功提示******************************/	
	function tipBase(b){
		if($("#ts_tips").length>0){
			$("#ts_tips").remove();
		}
		var t = b ? "<span class=\"iconfont icon-zhengque pr10\"></span>操作成功":"<span class=\"iconfont icon-cuowu2 pr10\"></span>操作失败"
		var tipText = '<div class="ts_add_success disappear" id="ts_tips">'+t+'</div>';
		//追加内容
		var tips = document.createElement("div");
		tips.id = "ts_tips";
		tips.innerHTML = tipText ;
		(document.documentElement || document.body).appendChild(tips);
	
		//移除
		var tips = document.getElementById("ts_tips");
		function removeTip(){
			(document.documentElement || document.body).removeChild(tips);
		};
		setTimeout(function(){removeTip()},3500);
	}
	
	function tsTipsError(){
		
		 tipBase(false);
		
	}
	
	function tsTips(){
		
		 tipBase(true);
	}	
	
	function warningTips(content){
		if($("#ts_tips").length>0){
			$("#ts_tips").remove();
		}
		var t = "<span class=\"iconfont icon-icontishi pr10\"></span>"+content;
		var tipText = '<div class="ts_add_success disappear" id="ts_tips">'+t+'</div>';
		//追加内容
		var tips = document.createElement("div");
		tips.id = "ts_tips";
		tips.innerHTML = tipText ;
		(document.documentElement || document.body).appendChild(tips);
	
		//移除
		var tips = document.getElementById("ts_tips");
		function removeTip(){
			(document.body || document.documentElement).removeChild(tips);
		};
		setTimeout(function(){removeTip()},3500);
	}

	/**********************添加功能******************************/	
	function clmParWidth(){
		var obj = $(".js-sel");
		var ww = obj.parents(".ts_form_left").width();
		var w = obj.parents(".ts_form_c_list").find(".ts_form_c_list_left").eq(0).outerWidth();
		var wLeft = obj.find(".inner .left").eq(1).outerWidth();
		obj.css({"width": ww - w - 20 +"px"});
		obj.find(".inner .right").css({"width": ww - w - wLeft - 40 +"px"});
	};

/**********************多页面加载******************************/	
$(function(){
	sideTab($("#menuList"));
})

function sideTab(obj){
	var id = obj.attr("data-target");
	var oNavSide = obj.find("[data-tit]");
	var oNav = $(id).find(".js-tab-nav");
	var oCon = $(id).find(".js-tab-con");
	var arr = [oNav.find("li").eq(0).attr("data-tit")];
	oNavSide.click(function(){
		var $menu = $(this);
		var s = $(this).attr("data-tit");
		$(this).addClass("active").siblings("*[data-tit]").removeClass("active");
		if(findInArr(arr, s) == -1){
			//增加
			arr.push($(this).attr("data-tit"));
			var strVar = "";
		    strVar += "<div class=\"ts_tab_pane js_tab_pane active js-tab-panel\">";
		    strVar += "    <iframe class=\"tabIframe\" name=\"tabIframe\"  src=\""+$(this).attr("data-url")+"\" style=\"width:100%;height: 100%;border: 0px; z-index: 101;position: relative;\"></iframe>";
		    strVar += "<\/div>";
			addSideNav($(this), oNav, oCon, strVar, arr);
			oNav.find("li:last .icon-guanbi").click(function(){
				arr = delSideNav($(this),oNav,oCon,arr);
			});
		}else{
			playTab($menu,oNav, oCon, s);
		}
	});
};

function playTab($menu,navObj, conObj, s){
	var n = 0;
	navObj.find(" > li").each(function(){
		if($(this).attr("data-tit") == s){
			n = $(this).index();
		}
	});
	navObj.find(" > li").removeClass("active").eq(n).addClass("active");
	conObj.find(" > .js-tab-panel").removeClass("active").eq(n).addClass("active").find(".tabIframe").attr("src",$menu.attr("data-url"));
};

//添加标签
function addSideNav(thisObj, navObj, conObj, str, arr){ //str -------->  右边加载内容
	var navStr ="<li class=\"active\" data-tit=\""+thisObj.attr("data-tit")+"\"><span>"+thisObj.attr("data-tit")+"</span><span class=\"iconfont icon-guanbi ml5\"></span></li>";
	navObj.find("li").removeClass("active");
	conObj.find(".js-tab-panel").removeClass("active");
	navObj.append(navStr);
	conObj.append(str);
	resetIframe();
	if(colWidth(navObj)){
		delSecond(navObj,conObj,arr);
	}
};

//删除标签
function delSideNav(thisObj, navObj, conObj, arr){
	var oNav = thisObj.parents("li").eq(0);
	var index = oNav.index();
	var oCon = conObj.find(" > .js-tab-panel");
	var prev = oNav.prev();
	var isAddClass = oNav.hasClass("active");
	var s = oNav.attr("data-tit");
	oNav.remove();
	oCon.eq(index).remove();
	if(isAddClass){
		prev.addClass("active");
		oCon.eq(index-1).addClass("active");
	}
	arr.splice(findInArr(arr,s),1);
	return arr;
};

function delSecond(oNav,oCon,arr){
	oNav.find("li").eq(1).remove();
	oCon.find(".js-tab-panel").eq(1).remove();
	arr.splice(1,1);
	
};

function colWidth(oNav){
	var w = $(window).width();
	var oLi = oNav.find("li");
	var sum = 0;
	for(var i = 0; i < oLi.length; i++){
		sum += oLi.eq(i).outerWidth();
	}
	if(sum > (w - 250)){
		
		return true;
		
	}else{
		return false;
	}
	
	
};

$(function(){
	$("body").on("click","#tabUI *[data-tit]",function(){
		//console.log($($(".tabIframe").eq($(this).index()))[0]);
		$($(".tabIframe").eq($(this).index()))[0].contentWindow.location.reload(true);
		var urlStr = $($(".tabIframe").eq($(this).index()))[0].contentWindow.location.href;
		
		$($(".tabIframe").eq($(this).index()))[0].contentWindow.location=urlStr;
	})
	
	$(window).resize(function(){
		resetIframe();
	})
	resetIframe();
})

function resetIframe(){
	$(".tabIframe").css("height",$(top).height()-130);
	$("#leftNav").css("height",$(top).height()-16);
}

//判断是否已经添加
function findInArr(arr, str){
	for(var i = 0; i < arr.length; i++){
		if(arr[i] == str){
			return i;
		}
	}
	return -1;	
};
//重置分页查询 页码
function restPageNumber(){
	$("input[name='pageNumber']").each(function(i){
		$(this).val(1);
	});
};


/*20151209添加左侧栏滚动*/
function ctrlSide(){
	var oWrap = $($(".js-side-scroll").eq(0))[0];
	if(oWrap){
		var oDiv = $($(".js-side-scroll").parent(".ts_sidebar").eq(0))[0];
		var oScroll = document.getElementById("side-scroll")? document.getElementById("side-scroll") : null;
		var oBar = document.getElementById("side-scrollBar")? document.getElementById("side-scrollBar") : null;
		document.getElementById("side-scroll") && oDiv.removeChild(oScroll);
		oDiv.style.height = document.documentElement.clientHeight - 70 + "px";
		if(oWrap.offsetHeight > oDiv.offsetHeight){
			var oSideScroll = document.createElement("div");
			oSideScroll.id = "side-scroll";
			oSideScroll.className = "scroll_bar";
			oSideScroll.innerHTML =  "<div class=\"scroll_bg\"></div><div id=\"side-scrollBar\" class=\"scroll_in\"></div>";
			oDiv.appendChild(oSideScroll);
			oScroll = document.getElementById("side-scroll");
			oBar = document.getElementById("side-scrollBar");
		    oScroll.style.height = document.documentElement.clientHeight - 70 + "px";
			addMouseWheel(oDiv,function (bDown){
				var t = oBar.offsetTop;
				if(bDown){
					t += 20;
				} else {
					t -= 20;
				}
				setLeft(t);
			});	
			oBar.onmousedown = function(ev){
				var oEvent = ev || event;
				var disY = oEvent.clientY - oBar.offsetTop;
				document.onmousemove = function(ev){
					var oEvent = ev || event;
					var t = oEvent.clientY - disY;
					setLeft(t);
				};	
				document.onmouseup = function(){
					document.onmousemove = null;
					document.onmouseup = null;
					oBar.releaseCapture && oBar.releaseCapture();
				};
				oBar.setCapture && oBar.setCapture();
				return false;	
			};
		}else{
			oWrap.style.top = "0px";	
		}
		function setLeft(t){
			var maxHeight = oScroll.offsetHeight - oBar.offsetHeight;
			t = Math.max(0,Math.min(t,maxHeight));
			oBar.style.top = t + "px";
			var scale = t/maxHeight;
			oWrap.style.top = -scale*(oWrap.offsetHeight + 40 - oDiv.offsetHeight) + "px";	
		};
	
	}
};	

//监听滚动事件
function addMouseWheel(obj,fn){
	if(window.navigator.userAgent.toLowerCase().indexOf("firefox") != -1){
		obj.addEventListener("DOMMouseScroll",fnWheel,false);
	} else {//ie chrome
		obj.onmousewheel = fnWheel;
	}
	function fnWheel(ev){
		var oEvent = ev || event;
		var bDown = true;
		if(oEvent.wheelDelta){// ie chrome  //往上 120    //往下 -120
			bDown = oEvent.wheelDelta > 0 ? false : true;

		} else {//firefox   // 往上 -3  // 往下 3
			bDown = oEvent.detail > 0 ? true : false;
		}
		(fn && typeof fn == "function") && fn(bDown);
		oEvent.preventDefault && oEvent.preventDefault();
		return false;
	}
};
/*20151209添加左侧栏滚动*/

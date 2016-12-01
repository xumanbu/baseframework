
if(top.window!=window){
	top.window.location.href=ctx+"/login";
}

$(document).keydown(function(e){
    var currKey=e.keyCode||e.which||e.charCode;
    if(currKey==13){
        if ( e && e.preventDefault ){
           //阻止默认浏览器动作(W3C)
           e.preventDefault();
        }else{
           //IE中阻止函数器默认动作的方式
           window.event.returnValue = false;
        }
        var target=e.srcElement||e.target;
        if(target.type=="reset"){
        	 $('#loginForm')[0].reset()
        }else{
        	login.doLogin();
        }
    }
 });
$(function(){
	$("#loginBtn").click(function(){
		login.doLogin();
	})
	
	$("#randompicture").attr("src",ctx+"/login/authCode");
});

var login = {
	/**
	 * 登录系统
	 */
	doLogin:function(){
		var url = ctx+"/dologin";
		var postData = {"loginName":$("#loginName").val(),"password":$("#passWord").val(),"authCode":$("#authCode").val()};
		$.get(url,postData,function(messge){
			if(messge.type==1){
				window.location.href=ctx+"/system/index";
			}else{
				$("#failText").text(messge.msg);
			}
		})
	},
	/**
	 * 重新更换验证码
	 */
	changePicture:function (){
		$("#randompicture").attr("src",ctx+"/login/authCode?t= "+new Date());
	}
}

function myReset(){
	$("#loginName").val("");
	$("#passWord").val("");
	$("#radompicture").val("");
}
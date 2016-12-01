/**
 * 公共的一些js功能，作为工具类使用
 */

;(function($,win)  {
	
	var _DEBUG = true;
   /**
    * 打印日志的工具类
    */
   $.log = function(val) {
	   if(_DEBUG){
	   }
   };
   
   $.tools = {};
   
   /**
    * 格式化金钱
    * 金额格式的字符串,如'1,234,567.45'
    * comma:是否需要逗号分隔
    */
   $.tools.formatMoney = function(num,comma){
	   	if(comma==undefined)comma = true;
	    num = num.toString().replace(/\$|\,/g,'');
	    if(isNaN(num)){
	    	num = "0";
	    }
	    sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100+0.50000000001);
	    cents = num%100;
	    num = Math.floor(num/100).toString();
	    if(cents<10){
	    	cents = "0" + cents;
	    }
	    if(comma){
	    	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++){
	    		num = num.substring(0,num.length-(4*i+3))+','+num.substring(num.length-(4*i+3));
	    	}
	    }else{
	    	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++){
	    		num = num.substring(0,num.length-(4*i+3))+num.substring(num.length-(4*i+3));
	    	}
	    }
	    return (((sign)?'':'-') + num + '.' + cents);
   }
   
   //是否存在指定函数 
   $.tools.isExitsFunction = function(funcName) {
       try {
           if (typeof(eval(funcName)) == "function") {
               return true;
           }
       } catch(e) {}
       return false;
   }
   
   //是否存在指定变量 
   $.tools.isExitsVariable = function(variableName) {
       try {
           if (typeof(variableName) == "undefined") {
               //alert("value is undefined"); 
               return false;
           } else {
               //alert("value is true"); 
               return true;
           }
       } catch(e) {}
       return false;
   }
   
   /**
    * 金钱四舍五入的方法
    * @param floatNum
    * @returns {Number}
    */
   $.tools.formatFloat = function(floatNum){
	   floatNum = floatNum*100;
	   return Math.round(floatNum)/100;
   }
   
   /**       
    * 对Date的扩展，将 Date 转化为指定格式的String       
    * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
    * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
    * eg:       
    * (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
    * (new Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
    * (new Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
    * (new Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
    * (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
    */          
   win.Date.prototype.format=function(fmt) {           
       var o = {           
	       "M+" : this.getMonth()+1, //月份           
	       "d+" : this.getDate(), //日           
	       "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
	       "H+" : this.getHours(), //小时           
	       "m+" : this.getMinutes(), //分           
	       "s+" : this.getSeconds(), //秒           
	       "q+" : Math.floor((this.getMonth()+3)/3), //季度           
	       "S" : this.getMilliseconds() //毫秒           
       };           
       var week = {           
	       "0" : "/u65e5",           
	       "1" : "/u4e00",           
	       "2" : "/u4e8c",           
	       "3" : "/u4e09",           
	       "4" : "/u56db",           
	       "5" : "/u4e94",           
	       "6" : "/u516d"          
       };           
       if(/(y+)/.test(fmt)){           
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
       }           
       if(/(E+)/.test(fmt)){           
           fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
       }           
       for(var k in o){           
           if(new RegExp("("+ k +")").test(fmt)){           
               fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
           }           
       }           
       return fmt;           
   }    
   
   //字符串替换方法，替换所有，string.replace只能替换第一个，加入全局方法是不是会更好呢？？
   win.String.prototype.replaceAll = function(oldChar,newChar){
   	var reg = new RegExp(oldChar,"g");
   	return this.replace(reg,newChar);
   }
   //兼容zepojs
   if(!$.fn.extend){
		$.fn.extend = function(option){
			$.extend($.fn,option);
		}
	}
   
   /**
    * jquery 全选插件
    * 全选的checkbox 必须有一个groupName属性
    * 被选择的checkbox 有一个group属性,值和groupName保持一致
    * 当有多个checkall时，checkall的input中需要定义统一的groupName
    *
    */
   $.fn.extend({
	   	checkAll: function() {
	   		this.each(function() {
	   			var $this = $(this),
	   			groupName = $this.attr("groupName"),
	   			items = null;
	   			if (!groupName) {
	   				return;
	   			}
	   			var checkAllBox = $("input[groupName='" + groupName + "']");
	   			
	   			items = $("input[group='" + groupName + "']");
	
	   			checkAllBox.change(function() {
	   				for (i = 0; i < items.length; i++) {
	   					if($(items[i]).attr("disabled")=="disabled")continue;
	   					items[i].checked=this.checked;
	   				}
	   				var checkedAll=this.checked;
	   				checkAllBox.each(function(){
	   					this.checked = checkedAll;
	   				});
	   			});
	   			
	   			items.change(function() {
	   				var checkedAll = true, i;
	   				for (i = 0; i < items.length; i++) {
	   					if (!items[i].checked) {
	   						checkedAll = false;
	   						break;
	   					}
	   				}
	   				checkAllBox.each(function(){
	   					this.checked = checkedAll;
	   				});
	   			});
	   		});
	   	}
   });
   
   //获取form表单数据的json对象
   $.fn.serializeJson = function(){
	   if(this[0].tagName=="FORM"||this[0].tagName=="form"){
		   var paramsAndValues = $(this).serialize().replaceAll("\\+"," ");
		   var jsonObj = {};  
		   var param = paramsAndValues.split("&");  
		   for ( var i = 0; param != null && i < param.length; i++) {  
		        var para = param[i].split("=");
		        jsonObj[para[0]] = decodeURIComponent(para[1]);  
		   } 
		   return jsonObj;
	   }
   }
   
   //倒计时的插件
   $.fn.extend({
	   oaoTime:function(){
		   this.each(function() {
			   var dateStr = $(this).attr("end-date");
			   var endDate = new Date(dateStr.replace(/-/g,"/"));//取得指定时间的总毫秒数 
			   var nowTime;
			   //now是在动态页面中取得服务器的时间，如果没有定义使用客户端时间
			   if(typeof(now)=="undefined"){
				   nowTime = new Date().getTime();
			   }else{
				   nowTime = now;
			   }
			   var tms = endDate - nowTime;//得到时间差
			   if(tms<0){
				   $(this).html("时间过期了!");
				   	/*warningPop({
	                    title : "提示：",
	                    content : '时间过期了!',
	                    btngray : '确   定',
	                    cancelFn : function(){
	                    }
	                });*/
				   	return;
			   }
		       $.oaoTime.timers.push({tms:tms,content:$(this)});
			   $.oaoTime.start();
		   });
		 
	   }
   });
   
   //倒计时的插件
	$.oaoTime={
		//倒计时容器，所有需要倒计时的时间都需要注册到这个容器中，容器中放的是一个object，object描述了倒计时的结束时间，以及显示时间的jquery对象(例如div)
		timers:[],
		//全局的一个倒计时状态，init表示初始化状态，start表示运行中状态，stop表示停止状态
		status:'init',
		//计算时间并定时刷新时间的方法，本插件的核心代码
		takeCount:function(){
			//如果定时器没有启动不执行
			if(this.status != 'start')return;
			setTimeout("$.oaoTime.takeCount()", 1000 );
			var timers = this.timers;
			for (var i = 0, j = timers.length; i < j; i++) {
				//计数减一
				timers[i].tms -= 1000;
				//计算时分秒
				var days = Math.floor(timers[i].tms / (1000 * 60 * 60 * 24));
				var hours = Math.floor(timers[i].tms / (1000 * 60 * 60)) % 24;
				var minutes = Math.floor(timers[i].tms / (1000 * 60)) % 60;
				var seconds = Math.floor(timers[i].tms / 1000) % 60;
				if (days < 0)
					days = 0;
				if (hours < 0)
					hours = 0;
				if (minutes < 0)
					minutes = 0;
				if (seconds < 0)
					seconds = 0;
				var newTimeText = days+"天"+hours+"小时"+minutes+"分"+seconds+"秒";
				timers[i].content.text(newTimeText);
			}
		},
		//启动倒计时
		start:function(){
			if(this.status=='init'){
				this.status = 'start';
				this.takeCount();
			}
		},
		//停止倒计时
		stop:function(){
			this.status = 'stop';
		}
	
	}; 
	
	/** 
	 * @version 1.0 
	 * 用于实现页面 Map 对象，Key只能是String，对象随意 
	 */  
	win.Map = function(){  
	    this._entrys = new Array();  
	      
	    this.put = function(key, value){  
	        if (key == null || key == undefined) {  
	            return;  
	        }  
	        var index = this._getIndex(key);  
	        if (index == -1) {  
	            var entry = new Object();  
	            entry.key = key;  
	            entry.value = value;  
	            this._entrys[this._entrys.length] = entry;  
	        }else{  
	            this._entrys[index].value = value;  
	        }          
	    };  
	    this.get = function(key){  
	        var index = this._getIndex(key);  
	        return (index != -1) ? this._entrys[index].value : null;  
	    };  
	    this.remove = function(key){  
	        var index = this._getIndex(key);  
	        if (index != -1) {  
	            this._entrys.splice(index, 1);  
	        }  
	    };  
	    this.clear = function(){  
	        this._entrys.length = 0;;  
	    };  
	    this.contains = function(key){  
	        var index = this._getIndex(key);  
	        return (index != -1) ? true : false;  
	    };  
	    this.getCount = function(){  
	        return this._entrys.length;  
	    };  
	    this.getEntrys =  function(){  
	        return this._entrys;  
	    };  
	   this._getIndex = function(key){  
	        if (key == null || key == undefined) {  
	            return -1;  
	        }  
	        var _length = this._entrys.length;  
	        for (var i = 0; i < _length; i++) {  
	            var entry = this._entrys[i];  
	            if (entry == null || entry == undefined) {  
	                continue;  
	            }  
	            if (entry.key === key) {//equal  
	                return i;  
	            }  
	        }  
	        return -1;  
	    };  
	}  
	
	$.tools.getRandomNum = function(Min,Max)
	{   
		var Range = Max - Min;   
		var Rand = Math.random();   
		return(Min + Math.round(Rand * Range));   
	}   
})($,window);

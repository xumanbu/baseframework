	$.area = {
		init:function(_$obj,province,city,area){//初始化选择地区的select框
			this.province = province;
			this.city = city;
			this.area = area;
			this.getProvince(_$obj);
			if(this.province&&this.province!=''){
				var provId = $(".province option[value='"+this.province+"']",_$obj).attr('value');
				this.getCity(provId,_$obj);
			}
			if(this.city&&this.city!=''){
				var cityId = $(".city option[value='"+this.city+"']").attr('value');
				this.getArea(cityId,_$obj);
			}
		},
		getProvince:function(_$obj){//获取省份的数据
		    var html = '<option value="" name="0">选择省</option>';
		    html += this.getOption('', $.area.province);
		    $(".province",_$obj).html(html);
			$(".city",_$obj).html('<option value="" name="0">选择市</option>');
			$(".area",_$obj).html('<option value="" name="0">选择县</option>');
		},
		getCity:function(id,_$obj){//获取城市的数据
		    var html='<option value="">选择市</option>';
		    html+=this.getOption(id, $.area.city);
		    $(".city",_$obj).html(html);
		},
		getArea:function(id,_$obj){//获取地区的信息
			var html='<option value="">选择县</option>';
			html+=this.getOption(id, $.area.area);
			$(".area",_$obj).html(html);
		},
		getOption:function(id,selectValue){//获取地区信息的optionhtml
			 var optionHtml="";
			 $.ajax({
				  url: this.urls.areasList,
				  async: false,
				  data:{"parentId":id},
				  dataType:"json",
				  success: function(data){
						for(var i=0;i<data.length;i++){
							optionHtml+='<option value="'+data[i].id+'" name="'+data[i].areaName+'"';
							if(selectValue==data[i].id){
								optionHtml+=' selected="selected" ';
							}
							optionHtml+= '>'+data[i].areaName+'</option>';
						}
				   }
			 })
			return optionHtml;
		}
	}
	
	$.area.urls = {
		areasList:ctx+"/area/getChildAreas"
	}
	
	
	 $.fn.extend({
		   area:function(){
			   _$this = $(this);
			   return {
				   init:function(province,city,area){
					   var $parent = _$this;
					    //点击省级数据
						$(".province",$parent).on("change",function(){
							var id=$(this).find("option:checked").attr("value");
							if(id == 0){
								$(".city",$parent).html('<option value="">选择市</option>');
								$(".area",$parent).html('<option value="">选择县</option>');
								return;
							}else{
								$(".city",$parent).html('<option value="">选择市</option>');
								$(".area",$parent).html('<option value="">选择县</option>');
								$.area.getCity(id,$parent);
							}
						})
						
						//点击市级数据
						$(".city",$parent).on("change",function(){
							var id=$(this).find("option:checked").attr("value");
							if(id == 0){
								$(".area",$parent).html('<option value="">选择县</option>');
								return;
							}else{
								$.area.getArea(id,$parent);
							}
						})
						$.area.init($parent,province,city,area);
				   }
			   }
		   }
	   });
	
	

//自定义ztree全局变量，缓存一些数据
var ztreeObject = {};

//ztree的初始化配置信息
var setting = {
	view: {
		selectedMulti: false,
		dblClickExpand: true
	},
	data: {
		simpleData: {
			enable: true,
			pIdKey: "parentTId",
			idKey:"id",
			rootPId: "-1"
		},
		key: {
			name: "name"
		}
	},
	callback: {
		beforeExpand: getChildNodes,
		onClick: nodeClick
	}
};

//节点点击事件执行的方法,如果用户初始化指定了点击事件，调用用户的方法
function nodeClick(event, treeId, treeNode) {
	if(!ztreeObject.treeSetting.onClick){
		return true;
	}
	return ztreeObject.treeSetting.onClick(event, treeId, treeNode);
}

// 展开节点前的处理方法，第一次去后台请求子节点数据，加载子节点
function getChildNodes(treeId, treeNode) {
	if(treeNode.hasGetChildren!=0){
		treeNode.hasGetChildren = 0;
		treeNodes(treeNode.id,function(data){
			if(data=="[null]")return;
			var newNodes = eval(data);
			newNodes = ztreeObject.ztree.addNodes(treeNode, newNodes,true);
		});
	}
};

//获得指定节点下的数据
function treeNodes(treeNodeId,fn){
	//获取子节点的url
	var url = ztreeObject.treeSetting.getNodeUrl+"?"+ztreeObject.treeSetting.pidParam+"="+treeNodeId;
	$.ajax({
		type: "POST",
		url: url,
		async: false,
		dataType:'json',
		success: function(list){
			for(var i=0;i<list.length;i++){
				list[i].isParent = list[i].isParent==0?1:0;
			}
			fn(list);
		}
	});
}


//初始化树结构
function initTree(treeSetting){
	ztreeObject.treeSetting = treeSetting;
	//初始化ztree对象
    var ztree =  $.fn.zTree.init($("#"+treeSetting.treeId), setting, treeSetting.rootNode);
    ztreeObject.ztree = ztree;
    //如果根节点为展开状态，则去加载一级节点
    if(treeSetting.rootNode.open==1){
    	//默认展开第一级
    	var rootZtreeNode = ztree.getNodeByParam('id', treeSetting.rootNode.id);//获取根节点  
    	getChildNodes(treeSetting.treeId, rootZtreeNode);
    	ztree.refresh();
    }
}
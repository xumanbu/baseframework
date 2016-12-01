//初始化树节点
$(function(){
	//初始化一个根节点
    var rootNode = {id:"0", pId:-1, name: "数据字典",isParent:1,open:1};
    var treeSetting = {
    	treeId:"treeRoot",
    	getNodeUrl:ctx+"/system/dictionary/treeNodes",
    	pidParam:"pid",
    	rootNode:rootNode,
    	onClick:function(event, treeId, treeNode) {
			return true;
		}
    }
    //初始化树
    initTree(treeSetting);
});

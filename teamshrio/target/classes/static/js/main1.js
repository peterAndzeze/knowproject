var width = Ext.getBody().getWidth();
Ext.onReady(function() {
	var mainTabPanel = new Ext.TabPanel({
				id : 'mainTabPanel',
				title:'主功能区',
				region : 'center',
				deferredRender : true,
				enableTabScroll : true,
				resizeTabs : false,
				tabWidth : 90,
				activeTab : 0,
        		plugins: new Ext.ux.TabCloseMenu(),
				defaults : {
					autoScroll : true
				}//,     //   plugins: new Ext.ux.TabCloseMenu(),

			});
			
	var dictTree = new Ext.tree.TreePanel({
		region : 'west',
		//title:'管理',
		width:200,
        useArrows:false,//为true时, lines属性无效
        autoScroll:true,
		//autoHeight:true,
		//height:1000,
        animate:true,
        containerScroll: true,
		border:false,
		lines:true,
		loader: new Ext.tree.TreeLoader({
			dataUrl: "menu/getFuncTree",
			baseParams: {"parentId":0},
			listeners:{'beforeload':function(loader,node){
				console.log(node);
				if(node.isRoot){
					 this.baseParams.parentId=0;
				}else{
                     this.baseParams.parentId=node.id
				}
			}}
		}),
		listeners:{
			"click":function(node){
				if(node.attributes.leaf){
					var tabId="tab-"+node.attributes.id;
					var tabs=Ext.getCmp("mainTabPanel");
					var path=node.attributes.path;
					var tab=Ext.getCmp(tabId);
					if(!tab){//不存在
						
						tab=tabs.add({
							id:tabId,
							layout:'fit',
							xtype:'panel',
							closeAction:'hide', //隐藏不关闭  
							'title' : node.attributes.text,
							closable : true,
							html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+path+'"> </iframe>' 

							//url:path,
							//autoLoad:path
							//html : "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='dictionary/main.html'></iframe>"
						});
						//tabs.load();
						//alert(path+"--"+Ext.getCmp(path));
						/*tabs.getItem(tab).add(Ext.getCmp(path));
						tabs.setActiveTab(tab);*/
						tab.show()
						//tabs.add(tab);
					}else{
						tabs.unhideTabStripItem(Ext.getCmp(tabId).show());
						//tabs.setActiveTab(tab);
					}
				}
			}
		},
		rootVisible:false,
        root: new Ext.tree.AsyncTreeNode({
            //nodeType: 'async',
            text: "管理功能",
            draggable:false
        })
    });
	//定义右键菜单
	var dictTreeCtxMenu = new Ext.menu.Menu({
		id :'dictTreeCtxMenu',
		items : [
				sw.menu.createBtn('新建功能菜单（项）','badd',add),
				sw.menu.createBtn('修改功能菜单（项）','bpencil',edit),
				sw.menu.createBtn('删除功能菜单（项）','bdelete',del),
				sw.menu.createBtn('刷新功能菜单（项）','brefresh',refreshNode),
				//sw.menu.createBtn('复制字典菜单（项）','bcopy',copy),
				//sw.menu.createBtn('粘贴（项）','bpaste',paste),
				sw.menu.createBtn('<span style="color:red;font-weight:bold">级联删除字典</span>','bfolder_delete',dels)
		]
	});
    //点击树的节点触发事件
    //增加右键点击事件
	dictTree.on('contextmenu',function(node,event){//声明菜单类型
		event.preventDefault();//这行是必须的
		currentNode=node;
		node.select();
		dictTreeCtxMenu.showAt(event.getXY());//取得鼠标点击坐标，展示菜单
	});

	currentNode = dictTree.getRootNode();
    dictTree.getRootNode().expand();
	var viewport = new Ext.Viewport({
		layout : {
			type : 'border',
			padding : '5'
		},
		items : [new Ext.Panel({
							region : 'north',
							frame : false,
							border : true,
							height : 30,
							id:'topPanel',
							bbar : ['->', {
								text : Ext.get("userName").dom.value+" 您好！",
								iconCls : 'buser'
							}		// 显示用户姓名
							, {
								text : '退出系统',
								iconCls : 'blogout',
								tooltip : '退出系统',
								handler : logout
							}]
						}),dictTree, mainTabPanel, {
					region : 'south',
					collapsible : false,
					html : '<div class="footer" style="text-align:center">© 新开普电子股份有限公司 2018</div>',
					split : false,
					height : 22
				}]
	});
});

// 关闭主界面的遮罩
/*console.log(IndexMask);
 if (null != IndexMask) {
 IndexMask.hide();
 }*/
/*if (userName == "oldoper") {
 sw.Msg.warn('当前登录用户没有操作权限,请重新登录', function() {
 doLogout();
 });
 }*/
function openForWarn(node, url, name, flag) {
	var tabId = 'tab-' + node;
	var tabs = Ext.getCmp(mainTabPanelId);
	var tab = tabs.getItem(tabId);
	if (undefined != tab) {
		sw.util.closeFromMainTab(tabId);
	}
	if (undefined != tab) {
		sw.util.closeFromMainTab(tabId);
	}
	sw.util.openTab(node, url, '', 'mainTabPanel', name);
}

/**
 * 增加tab
 */
/*function addTab(node) {
 var nowTab = Ext.getCmp("tab_" + node.id);
 if (null != nowTab && undefined != nowTab) {
 Ext.getCmp("MainTabPanel").setActiveTab(nowTab);
 return;
 }
 var url;
 if ("1" == node.id) {
 url = "dictionary/main"
 } else if ("2" == node.id) {
 url = ""
 }
 var tab = Ext.getCmp("MainTabPanel").add({
 id : "tab_" + node.id,
 title : node.text,
 layout : 'fit',
 closable : true,
 html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src='
 + node.url + ' id=_tabcontent_' + node.id + '></iframe>'
 }).show();
 }*/

/*****************功能树操作**********************************************/


/**
 * 刷新节点
 */
function refreshNode(){
	var node = currentNode;
	phenix.tree.refreshNode(node);
}

/**
 * 弹出新建窗口
 */
function add(){
	var formWin = createFormWin(type);
	Ext.getCmp('parentId').setValue(currentNode.id);
	Ext.getCmp('state_0').setValue(true);//默认为启用
	formWin.show();
}

/**
 * 从sm取得选择的行，然后弹出编辑窗口
 */
function edit(){
	var node = currentNode;
	var id = node.id;
	if(root.id==id){
		phenix.Msg.warn('不能修改最顶层节点 !');
		return;
	}
	//打开编辑窗口
	var formWin = createFormWin();
	formWin.setTitle('修改'+baseName);
	phenix.ajax.request(menuInfoUrl,{'id': id},function(result){
		phenix.form.setValue(formId, result);
		var statusId = 'state_' + (result.state==0? 0 : 1);
		Ext.getCmp(statusId).setValue(true);
		formWin.show();
		currentOperation = 'edit';
	},true);
}

/**
 * 保存
 */
function save(){
	phenix.ajax.submit(updateMenuUrl,formId,function(result){
		Ext.getCmp(formWinId).close();
		if(root.id==currentNode.id){
			phenix.tree.refreshNode(currentNode);
		}else if('edit'==currentOperation){
			phenix.tree.refreshNode(currentNode.parentNode);
		}else if(!currentNode.leaf){
			phenix.tree.refreshNode(currentNode);
		}else{
			phenix.tree.refreshNode(currentNode.parentNode);
		}
		grid.store.reload();
		currentOperation = null;
	});
}

/**
 * 删除
 */
function del(){
	var node = currentNode;
	if(root.id==node.id){
		phenix.Msg.warn('不能删除最顶层节点 !');
		return;
	}
	if(!node.leaf){
	  phenix.Msg.warn('存在子节点，不能删除');
	  return;
	}
	phenix.Msg.confirm('提示', '您确定要删除字典（项）：'+node.text+' 吗?', function(btn, text){
		if ('yes' != btn) return;
		phenix.ajax.request(delUrl,{'id': node.id,"parentId":node.parentNode.id},function(result){
			if(result.success){
				node.remove();
                phenix.tree.refreshNode(node.parentNode);
				phenix.Msg.info(result.msg);
			}else{
				phenix.Msg.info(result.msg);
			}
			//grid.store.reload();
		});
	});
}

/**
* 删除
*/
function dels(){
    var node = currentNode;
    if(root.id==node.id){
        phenix.Msg.warn('不能删除最顶层节点 !');
        return;
    }

    phenix.Msg.confirm('提示', '您确定要级联删除字典：'+node.text+' 吗?', function(btn, text){
	    if ('yes' != btn) return;
	    phenix.Msg.confirm('提示', '<span style="color:red;font-size:13px;font-weight:bold">请慎用级联删除功能 !</span><br><br>确定后将删除字典本身及子字典 !', function(btn, text){
	    if ('yes' != btn) return;
		    phenix.ajax.request(delUrl,{'pkid': node.id,'delType':'cascade'},function(result){
		        phenix.Msg.info(result.msg);
		        node.remove();
		    },true);
	    });
    });
}

/**
 * 复制
 */
var copyNode;
function copy(){
	copyNode = currentNode;
	if(root.id==copyNode.id){
		phenix.Msg.warn('不能复制最顶层节点 !');
		return;
	}
	phenix.Msg.info("复制成功 !");
}

/**
 * 粘贴
 */
function paste(){
	var pasteNode = currentNode;
	if(copyNode==null){
		phenix.Msg.warn('请选择要复制的节点 !');
		return;
	}
	if(pasteNode.id==copyNode.id){
		phenix.Msg.warn('不能粘贴到本节点 !');
		return;
	}
	if(pasteNode==copyNode.parentNode){
		phenix.Msg.warn('父节点下不能有关键字相同的子节点 !');
		return;
	}
	if(copyNode.contains(pasteNode)){
		phenix.Msg.warn('不允许将父节点粘贴到子节点下 !');
		return;
	}
	phenix.ajax.request(pasteUrl,{'pkid': pasteNode.id,'copyNode':copyNode.id},function(){
		phenix.tree.refreshNode(pasteNode);//刷新节点
	},true);
}

/****************************************业务组件*************************************************************************/
/**
 * 创建编辑窗口
 */
function createFormWin(){
	var fields = [
		{
			id: 'menuName',
			fieldLabel: '名称',
			name: 'menuName',
			allowBlank:false,
			maxLength:255,
			anchor: '95%'
		}, {
			id: 'display',
			fieldLabel: '描述',
			name: 'display',
			allowBlank:false,
			maxLength:64,
			anchor: '95%'
		}, {
			id: 'remark',
			fieldLabel: '说明',
			name: 'remark',
			xtype: 'textarea',
			height: 64,
			anchor: '95%'
		}, {
			id: 'path',
			fieldLabel: '全路径',
			name: 'path',
			readOnly: false,
			anchor: '95%'
		}, {
			xtype:'panel',
			fieldLabel: '状态',
			//isFormField:true,
			baseCls:'x-plain',
			layout:'column',
			items:[{
					id:'state_0',
					xtype:'radio',
					boxLabel:'启用　',
					name: 'state',
					inputValue:'0'
				}, {
					id:'state_1',
					xtype:'radio',
					boxLabel:'禁用　',
					name: 'state',
					inputValue:'1'
				}
			]
		}, {
			id: 'parentId',
			xtype: 'hidden',
			name: 'parentId'
		}, {
			id: 'id',
			xtype: 'hidden',
			name: 'id'
		}];

		var form = new Ext.form.FormPanel({
		id: formId,
		baseCls: 'x-plain',
		labelWidth: 100,
		labelAlign:'right',
		labelPad:10,
		fileUpload: false,
		defaultType: 'textfield',
		items: fields,
		buttonsAlgin:'center',
		buttons: [{
			text: '保存',
			handler: save
		}, {
			text: '关闭',
				handler: function(){
					Ext.getCmp(formWinId).close();
				}
			}]
		});
	var title = '新建'+baseName;
	var formWin = phenix.util.openDialog(formWinId,form,baseIcon,400,300,title);
	return formWin;
}



/**
 * 创建编辑窗口（暂时未使用，显示ID、PID）
 */
function createFormWinNoUse(){
	var fields = [
		{
			id: 'displayName',
			fieldLabel: '名称',
			name: 'displayName',
			allowBlank:false,
			maxLength:255,
			anchor: '95%'
		}, {
			id: 'dictKey',
			fieldLabel: '关键字',
			name: 'dictKey',
			allowBlank:false,
			maxLength:64,
			anchor: '95%'
		},  {
			id: 'value1',
			fieldLabel: '值1',
			anchor: '95%'
		},  {
			id: 'value2',
			fieldLabel: '值2',
			anchor: '95%'
		},  {
			id: 'value3',
			fieldLabel: '值3',
			anchor: '95%'
		},  {
			id: 'value4',
			fieldLabel: '值4',
			anchor: '95%'
		},  {
			id: 'value5',
			fieldLabel: '值5',
			anchor: '95%'
		}, {
			id: 'remark',
			fieldLabel: '说明',
			name: 'remark',
			xtype: 'textarea',
			height: 64,
			anchor: '95%'
		}, {
			id: 'path',
			fieldLabel: '全路径(只读)',
			name: 'path',
			readOnly: true,
			cls: 'x-item-disabled',
			anchor: '95%'
		}, {
			id: 'version',
			xtype: 'hidden',
			name: 'version',
			anchor: '95%'
		}, {
			id: 'iconCls',
			xtype:'hidden',
			name: 'iconCls',
			value:baseIcon,
			anchor: '95%'
		}, {
			id: 'sortIdx',
			fieldLabel: '序号',
			name: 'sortIdx',
			anchor: '95%'
		}, {
			id: 'pkid',
			fieldLabel: 'ID',
			name: 'pkid',
			readOnly: true,
			cls: 'x-item-disabled',
			anchor: '95%'
		}, {
			id: 'pid',
			fieldLabel: '父ID',
			name: 'pid',
			value: currentNode.id,
			readOnly: true,
			cls: 'x-item-disabled',
			anchor: '95%'
		}, {
			xtype:'panel',
			fieldLabel: '状态',
			//isFormField:true,
			baseCls:'x-plain',
			layout:'column',
			items:[{
				id:'status_0',
				xtype:'radio',
				boxLabel:'启用　',
				name: 'systemStatus',
				inputValue:'0'
			}, {
				id:'status_1',
				xtype:'radio',
				boxLabel:'禁用　',
				name: 'systemStatus',
				inputValue:'1'
			}]
		}];

		var form = new Ext.form.FormPanel({
		id: formId,
		baseCls: 'x-plain',
		labelWidth: 100,
		labelAlign:'right',
		labelPad:10,
		fileUpload: false,
		defaultType: 'textfield',
		items: fields,
		buttons: [{
			text: '保存',
			handler: save
		}, {
			text: '关闭',
				handler: function(){
					Ext.getCmp(formWinId).close();
				}
			}]
		});
	var title = '新建'+baseName;
	var formWin = phenix.util.openDialog(formWinId,form,baseIcon,600,500,title,'displayName');
	return formWin;
}

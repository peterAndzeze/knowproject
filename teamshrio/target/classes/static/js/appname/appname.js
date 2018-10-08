var pageSize=20;

//窗口的实际宽度
var iWidth = window.screen.width - 10;
//窗口的实际高度
//重设窗口大小时宽度修正
var widthFix = 2;
//重设窗口大小时高度修正
var heightFix = 80;//原120
// 查询条件
var searchParams = {
	group : null,
	className : null
};
// 清空查询条件
function clearSearch() {
	for (var key in searchParams) {
		var sk = 'search_' + key;
		var cmp = Ext.getCmp(sk);
		if (cmp)
			cmp.setValue('');
	}
}
search = function() {
	var baseParam = createSearchParam();
	var store = grid.getStore();
	store.baseParams = baseParam;
	store.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
}
/**
 * 生成查询条件
 */
function createSearchParam() {
	var prefix = 'search_';
	var params = searchParams;
	for (var key in params) {
		var value = Ext.getCmp(prefix + key).getValue();
		if (value && typeof(value) == 'string')
			value = value.trim();
		params[key] = value;
	}
	return params;
}
var ds = new Ext.data.JsonStore({
	url : 'page',
	id : 'id',
	root : 'records',
	totalProperty : 'rowCount',
	fields : ['id', 'sysName', 'sysDes', 'state', 'sysUrl']
	});
var pageConfig = {
	'pageSize' : pageSize,
	'ds' : ds,
	'record_start' : 0
};

var sm = new Ext.grid.CheckboxSelectionModel({});

var cm = new Ext.grid.ColumnModel([sm, sw.grid.createRowNumberer(pageConfig), {
			header : '编号',
			dataIndex : 'id'
		}, {
			header : '业务系统名称',
			dataIndex : 'sysName'
		}, {
			header : '描述',
			dataIndex : 'sysDes'
		}, {
			header : '系统地址',
			dataIndex : 'sysUrl'
		}, {
			header : '数据状态',
			dataIndex : 'state',
			renderer : function(value) {
				if (value == "2") {
					return '运行中';
				} else {
					return '已停止';
				}
			}

		}, {
			header : '操作',
			dataIndex : 'id',
			width:200,
			renderer : function(value, metadata, record) {
				var btn = '';
				btn += '<button onclick="deleteAppname(\'' + record.data.id
						+ '\')">删除</button>';
				btn += '<button onclick="editAppname(\'' + record.data.id
						+ '\')">编辑</button>';
				btn += '<button onclick="pausedAppname(\'' + record.data.id
						+ '\')">暂停</button>';	
				btn += '<button onclick="resumeAppname(\'' + record.data.id
						+ '\')">恢复</button>';		
						
				return btn;
			}
		}]);

var pageConfig = {
	'pageSize' : pageSize,
	'ds' : ds,
	'record_start' : 0
};
var buttons = [sw.menu.createBtn('查询', 'bsearch', search), '-',
		sw.menu.createBtn('清空条件', 'bapplication_delete', clearSearch), '-',
		sw.menu.createBtn('新增', 'badd', addAppname)];
var pageBar = sw.grid.createNewPagebar(pageConfig);

var topBar = [{
			xtype : 'panel',
			id : 'topBarId',
			border : false,
			width:iWidth,
			items : [ {
						xtype : 'toolbar',
						items : [{
									xtype : 'label',
									html : '&nbsp;组信息：'
								}, {
									id : 'search_group',
									xtype : 'textfield',
									editable : false,
									width : 200,
									anchor : '90%'
								}, {
									xtype : 'label',
									html : '&nbsp;类信息：'
								}, {
									id : 'search_className',
									xtype : 'textfield',
									editable : false,
									width : 200,
									anchor : '90%'
								},buttons]
					}]
		}];
console.log(window.frames);
var grid = new Ext.grid.GridPanel({
			id : "appname",
			layoput:'fit',
			autoScroll : true,
			stripeRows : true,
			loadMask : true,
			ds : ds,
			cm : cm,
			sm : sm,
    		viewConfig : {
				forceFit : true
			},
			tbar : topBar,
    		bbar :　pageBar
		});

// grid.render("sunwei");
// 进入页面即加载
grid.getStore().load({
			params : {
				start : 0,
				limit : pageSize
			}
		});

/** ********************业务操作函数**************************** */
// 新增
function addAppname() {
	var fields = [{
				id : 'type',
				fieldLabel : '类型',
				name : 'type',
				allowBlank : false,
				maxLength : 255,
				anchor : '95%'
			},{
				id : 'group',
				fieldLabel : '组',
				name : 'group',
				allowBlank : false,
				maxLength : 255,
				anchor : '95%'
			}, {
				id : 'className',
				fieldLabel : '类信息',
				name : 'className',
				allowBlank : false,
				maxLength : 255,
				anchor : '95%'
			}, {
				id : 'cron',
				fieldLabel : '表达式',
				name : 'cron',
				anchor : '95%'
			}, {

				xtype : 'panel',
				fieldLabel : '数据状态',
				// isFormField:true,
				baseCls : 'x-plain',
				layout : 'column',
				items : [{
							id : 'state_0',
							xtype : 'radio',
							boxLabel : '运行中　',
							name : 'state',
							inputValue : '2'
						}, {
							id : 'state_1',
							xtype : 'radio',
							boxLabel : '停止　',
							name : 'state',
							inputValue : '1'
						}]

			}, {
				id : 'id',
				name : 'id',
				hidden : true
			}];

	var form = new Ext.form.FormPanel({
				id : "AppnameForm",
				baseCls : 'x-plain',
				labelWidth : 100,
				labelAlign : 'right',
				labelPad : 10,
				fileUpload : false,
				defaultType : 'textfield',
				items : fields,
				buttonAlign : 'center',
				buttons : [{
					text : '保存',
					handler : function() {
						sw.ajax.submit("saveOrUpdateAppname", "AppnameForm",
								function(result) {
									Ext.getCmp("AppnameWin").close();
									grid.store.reload();
									sw.Msg.info(result.msg);
								});
					}
				}, {
					text : '关闭',
					handler : function() {
						Ext.getCmp("AppnameWin").close();
					}
				}]
			});
	var title = '新建字典';
	var formWin = sw.util.openDialog("AppnameWin", form, null, 400, 300, title);
	formWin.show();
}

// 删除

function deleteAppname(id) {
	sw.Msg.confirm("温馨提示", "确认删除吗？", function(btn, text) {
				if ('yes' != btn) {
					return;
				}
				sw.ajax.request('deleteAppname', {
							'id' : id
						}, function(result) {
							sw.Msg.info(result.msg);
							grid.getStore().load({
										params : {
											start : 0,
											limit : pageSize
										}
									});
						});
			})
}

// 编辑

function editAppname(id) {
	var fields = [{
				id : 'className',
				fieldLabel : '类信息',
				name : 'className',
				allowBlank : false,
				maxLength : 255,
				anchor : '95%'
			}, {
				id : 'cron',
				fieldLabel : '表达式',
				name : 'cron',
				anchor : '95%'
			}, {
				id : 'id',
				name : 'id',
				hidden : true
			}];

	var form = new Ext.form.FormPanel({
				id : "AppnameForm",
				baseCls : 'x-plain',
				labelWidth : 100,
				labelAlign : 'right',
				labelPad : 10,
				fileUpload : false,
				defaultType : 'textfield',
				items : fields,
				buttonAlign : 'center',
				buttons : [{
					text : '保存',
					handler : function() {
						sw.ajax.submit("saveOrUpdateAppname", "AppnameForm",
								function(result) {
									Ext.getCmp("AppnameWin").close();
									grid.store.reload();
									sw.Msg.info(result.msg);
								});
					}
				}, {
					text : '关闭',
					handler : function() {
						Ext.getCmp("AppnameWin").close();
					}
				}]
			});
	var title = '更新定时任务';
	var formWin = sw.util.openDialog("AppnameWin", form, null, 300, 200, title);
	sw.ajax.request("getAppnameInfo", {
				'id' : id
			}, function(result) {
				sw.form.setValue("AppnameForm", result);
				var statusId = 'state_' + (result.state==2? 2 : 3);
				Ext.getCmp(statusId).setValue(true);
				formWin.show();
			}, true);
}

function pausedAppname(id){
	sw.ajax.request('paused', {'id' : id}, function(result) {
				sw.Msg.info(result.msg);
				grid.getStore().load({
						params : {
									start : 0,
									limit : pageSize
								}
						});
			});
}

function resumeAppname(id ){
	sw.ajax.request('resume', {'id' : id}, function(result) {
				sw.Msg.info(result.msg);
				grid.getStore().load({
						params : {
									start : 0,
									limit : pageSize
								}
						});
			});
}


var sw_ajax_isStoped = false;//标识服务器是否已停止
var sw_ajax_loginDialogId = Ext.id();
var userName ="";
//全屏幕遮罩
//var IndexMask = sw.Msg.waitMask();
//console.log(IndexMask)

//窗口的实际宽度
var iWidth = window.screen.width - 10;
//窗口的实际高度
var iHeight = window.screen.height - 65;
//重设窗口大小时宽度修正
var widthFix = 2;
//重设窗口大小时高度修正
var heightFix = 80;//原120
//桌面面板
var desktoptabDivId = 'desktopTab';

var mainTabId=Ext.id();
//   msg 相关配置
var msg = {};
msg.isUnderRequest = false;//标识是否正在进行请求
msg.recvTimer = null;//消息接收定时器
msg.refreshTimer = null;//在线状态刷新定时器
msg.localRefreshTimer = null;//本地在线状态刷新定时器
msg.recvTime = 10 * 1000;//接收消息间隔时间
msg.refreshTime = 5 * 1000;//状态刷新间隔时间
msg.localRefreshTime = 50 * 1000;//状态刷新间隔时间
/****************************主界面布局*******************************************/
/*============================================== 配置  ==============================================*/
var queryDate = "";
var sysID = "";
Ext.onReady(function () {
    var mainTabPanel = new Ext.TabPanel({
        id : 'mainTabPanel',
        region : 'center',
        deferredRender : true,
        enableTabScroll : true,
        resizeTabs : false,
        tabWidth : 90,
        activeTab : 0,
        plugins: new Ext.ux.TabCloseMenu(),
        defaults : {
            autoScroll : true
        },
        items:[{
            title: '首页',
            tabTip:'<b>首页</b>',
            xtype:'portal',
            style:'font-weight:bold',
            region:'center',
            margins:'35 5 5 0'
        }]
    });

    function getWarnByCatalogId(catalogId) {
        //增加loadMsg 会把菜单遮住
        //Ext.getCmp('catalog'+catalogId).getEl().mask(loadMsg);
        Ext.getCmp('catalog' + catalogId).getEl().mask();
    }

    /*====================== 主面板 ========================*/
     var clock = new Ext.Toolbar.TextItem('');//右下角时钟
     var week = Date.dayNames[new Date().format('w')];

    themeName = 'default';

    var viewport = new Ext.Viewport({
        layout: 'border',
        items: [new Ext.Panel({
            region: 'north',
            frame: false,
            border: true,
            height: 28,
            listeners: {render: function (obj) {
                loadMenus(obj);
            }
            }, bbar: []
        }),mainTabPanel]
    });
});
//关闭主界面的遮罩
/*  if (null != IndexMask) {
      IndexMask.hide();
  }*/
/*   if (Ext.get("userName").dom.value == "oldoper") {
       sw.Msg.warn('当前登录用户没有操作权限,请重新登录', function () {
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



/***********************主页函数操作****************************************************/
logout = function () {
    sw.Msg.confirm('确认退出', '您确定要退出系统吗？',
        function (btn) {
            if ("yes" == btn) {
                doLogout();
            }
        }
    );
}

function doLogout() {
    window.document.body.onbeforeunload = Ext.emptyFn;// 关闭刷新提示，注：在FireFox下无效
    // msg.stop();//关闭短信系统
    //IndexMask.show();
    window.location = "login";
   // IndexMask.hide();
}


msg.start = function () {
    msg.refresh();// 刷新状态
}

msg.stop = function () {
    window.clearTimeout(msg.refreshTimer);
}

/**
 * 远程家在菜单
 *
 * @param {}
 *            obj
 */
function loadMenus(obj) {
    if (Ext.get("userName").dom.value == "oldoper") {
        sw.Msg.warn('当前登录用户没有操作权限,请重新登录', function () {
            doLogout();
        });
    }
    sw.ajax.request("menu/getFuncMenu", {parentId: null}, function (result) {
        if (null != result.data && true == result.success) {
            var arr = Ext.decode(result.data);
            var bbar = [];
            var tbItem = obj.getBottomToolbar();
            tbItem.removeAll();
            tbItem.add(bbar);
            getFirstItems(tbItem, arr);
            tbItem.add(['->',{text: Ext.get("userName").dom.value+" 您好！",iconCls: 'buser'},
                        {
                            xtype:'combo',
                            store:new Ext.data.JsonStore({
                                url:'',
                                fields:["id","appName"]
                            }),
                            mode:'local',
                            forceSelection : false,
                            triggerAction:'all',
                            displayField:'appName',
                            valueField:'id',
                            selectOnFocus: true,
                            listeners:{"select":function(data){
                                
                            }}
                        },
                        {text: '退出系统', iconCls: 'blogout', tooltip: '退出系统', handler: logout}]);
            obj.doLayout();
        }
    });

}

function getFirstItems(tbItem, list) {
    var items = [];
    if (list == null) {
        return items;
    }
    var index = 0;
    for (var i = 0; i < list.length; i++) {
        var obj = list[i];
        var menu = {
            id: obj.idd,
            text: '<font>' + obj.menuName + '</font>',
            currMyMenu: obj,//把菜单对象放入当前对象中
            iconCls: obj.iconCls ? obj.iconCls : 'icon-modify',
            listeners: {
                mouseover: function () {
                    //鼠标悬浮显示菜单
                    this.showMenu();
                }
            }, handler: function () {
                var curObj = this.currMyMenu;
                if (curObj.leaf == "0" && null != curObj.path) {
                    var path = "${ctxPath}" + curObj.path;
                    var node = {
                        id: curObj.id,
                        text: curObj.menuName,
                        attributes: {
                            openType: 'TAB',
                            path: path
                        }
                    };
                    sw.util.menuHandle(node);
                }
            }
        };

        //创建下级菜单
        getMenu(obj, menu);
        items[index * 2] = menu;
        if (list.length > 1 && i != (list.length - 1)) {
            items[index * 2 + 1] = '-';
        }
        index++;
    }
    tbItem.add(items);
    //return items;
}

/**
 * 递归创建下级菜单
 */
function getMenu(obj, parentMenu) {

    var list = obj.childMenuModels;
    if (list == null || list.length == 0) {
        return;
    }

    var items = [];
    var index = 0;
    for (var i = 0; i < list.length; i++) {

        var currObj = list[i];
        var menu = {
            id: currObj.id,
            text: currObj.menuName,
            currMyMenu: currObj,//把菜单对象放入当前对象中
            handler: function () {
                var curObj = this.currMyMenu;
                if (curObj.leaf == "0" && null != curObj.path) {
                    var path = "${ctxPath}" + curObj.path;
                    var node = {
                        id: curObj.id,
                        text: curObj.menuName,
                        attributes: {
                            openType: 'TAB',
                            path: path
                        }
                    };
                    sw.util.menuHandle(node);
                }
            },
            iconCls: currObj.iconCls ? currObj.iconCls : 'icon-modify'
        };

        //递归创建下级菜单
        getMenu(currObj, menu);

        items[index * 2] = menu;
        if (list.length > 1 && i != (list.length - 1)) {
            items[index * 2 + 1] = '-';
        }
        index++;
    }

    var menu = new Ext.menu.Menu({
        items: items
    });
    parentMenu.menu = menu;
}

function serviceUrl(url) {
    sw.ajax.request(url, null, function (result) {

    });
}
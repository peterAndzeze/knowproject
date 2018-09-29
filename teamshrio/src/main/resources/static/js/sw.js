/*======================== 全局脚本 ============================*/
var loadMsg = '系统处理中，请稍候...';
var loadTitle = '请稍候...';
var sessionInvalidMsg = '您的登录状态已失效，系统将尝试重新登录。';
var connectErrorMsg = '获取数据时发生连接错误！\n\n可能是间歇性网络堵塞，或是服务器重新启动。'
	+'\n\n请片刻后点击确定。';//\n\n如果长时间没有响应，请检查网络是否畅通或联系管理员！
var tabLoadMsg = '<span style="font-size:12px">页面加载中,请稍候...</span>';
var serverOffMsg = '与服务器的通讯中断，请稍候...';
var serverOnMsg = '与服务器的通讯已恢复正常!';

var ORG_ROOT = 'ORG_ROOT';
var APP_ROLE = 'APP_ROLE';
var AJAX_TIMEOUT = 1000000;
var logoutUrl = '/pub/jsp/login.jsp';
var sysTitle = 'sw';

var mainTabPanelId = 'mainTabPanel';
var dynamicDialogWidth = 800;
var dynamicDialogHeight = 600;

var iframeLoadingPrefix = 'frame_loading_';

var sw = {};//riches全局对象

sw.comp = {};
sw.app = {};

var comp = {};
var app = {};

comp.prevOperation = null;//标识上一个操作：系统
app.prevOperation = null;//标识上一个操作：应用

comp.chart = {};

//使grid内容可选择
if (!Ext.grid.GridView.prototype.templates) {
    Ext.grid.GridView.prototype.templates = {};
}
Ext.grid.GridView.prototype.templates.cell = new Ext.Template(
    '<td class="x-grid3-col x-grid3-cell x-grid3-td-{id} x-selectable {css}" style="{style}" tabIndex="0" {cellAttr}>',
    '<div class="x-grid3-cell-inner x-grid3-col-{id}" {attr}>{value}</div>',
    '</td>'
);


//Ext.BLANK_IMAGE_URL = '${ctxPath}/pub/lib/Ext2/resources/images/default/s.gif';
Ext.useShims = true;
//Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
Ext.QuickTips.init();
//Ext.form.Field.prototype.msgTarget = 'side';
//var userTheme = '${sessionScope.USER_CONFIG_THEME}';
//lovcombo

//var globalBasePath = '${basePath}';
var pageSize=20;
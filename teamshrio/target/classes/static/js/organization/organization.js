var rootId="-1";

var baseName = '菜单信息';
var baseIcon = 'bbook';
var tabDivId = 'menuTab';
var formId = Ext.id();
var formWinId = Ext.id();
var gridId = Ext.id();
var topBarId = Ext.id();

var root = {parentId: rootId, text: '功能配置'}
var currentNode = root;
var currentOperation = null;

/**
 * 刷新节点
 */
function refreshNode(){
    var node = currentNode;
    sw.tree.refreshNode(node);
}

/**
 * 弹出新建窗口
 */
function add(){
    var formWin = createFormWin();
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
        sw.Msg.warn('不能修改最顶层节点 !');
        return;
    }
    //打开编辑窗口
    var formWin = createFormWin();
    formWin.setTitle('修改'+baseName);
    sw.ajax.request("menuInfo",{'id': id},function(result){
        sw.form.setValue(formId, result.data);
        var statusId = 'state_' + (result.data.state==0? 0 : 1);
        Ext.getCmp(statusId).setValue(true);
        formWin.show();
        currentOperation = 'edit';
    },true);
}

/**
 * 保存
 */
function save(){
    sw.ajax.submit("saveOrUpdate",formId,function(result){
        Ext.getCmp(formWinId).close();
        if(root.id==currentNode.id){
            sw.tree.refreshNode(currentNode);
        }else if('edit'==currentOperation){
            sw.tree.refreshNode(currentNode.parentNode);
        }else if(!currentNode.leaf){
            sw.tree.refreshNode(currentNode);
        }else{
            sw.tree.refreshNode(currentNode.parentNode);
        }
        currentOperation = null;
    });
}

/**
 * 删除
 */
function del(){
    var node = currentNode;
    if(root.id==node.id){
        sw.Msg.warn('不能删除最顶层节点 !');
        return;
    }
    if(!node.leaf){
        sw.Msg.warn('存在子节点，不能删除');
        return;
    }
    sw.Msg.confirm('提示', '您确定要删除字典（项）：'+node.text+' 吗?', function(btn, text){
        if ('yes' != btn) return;
        sw.ajax.request("deleteMenu",{'id': node.id,"parentId":node.parentNode.id},function(result){
            if(result.success){
                var parentNode=node.parentNode;
                node.remove();
                sw.tree.refreshNode(parentNode);
            }
            sw.Msg.info(result.msg);
        });
    });
}

/**
 * 删除
 */
function dels(){
    var node = currentNode;
    if(root.id==node.id){
        sw.Msg.warn('不能删除最顶层节点 !');
        return;
    }

    sw.Msg.confirm('提示', '您确定要级联删除字典：'+node.text+' 吗?', function(btn, text){
        if ('yes' != btn) return;
        sw.Msg.confirm('提示', '<span style="color:red;font-size:13px;font-weight:bold">请慎用级联删除功能 !</span><br><br>确定后将删除字典本身及子字典 !', function(btn, text){
            if ('yes' != btn) return;
            sw.ajax.request("deleteMenu",{'id': node.id,'parentId':node.parentNode.id,'delType':'cascade'},function(result){
                sw.Msg.info(result.msg);
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
        sw.Msg.warn('不能复制最顶层节点 !');
        return;
    }
    sw.Msg.info("复制成功 !");
}

/**
 * 粘贴
 */
function paste(){
    var pasteNode = currentNode;
    if(copyNode==null){
        sw.Msg.warn('请选择要复制的节点 !');
        return;
    }
    if(pasteNode.id==copyNode.id){
        sw.Msg.warn('不能粘贴到本节点 !');
        return;
    }
    if(pasteNode==copyNode.parentNode){
        sw.Msg.warn('父节点下不能有关键字相同的子节点 !');
        return;
    }
    if(copyNode.contains(pasteNode)){
        sw.Msg.warn('不允许将父节点粘贴到子节点下 !');
        return;
    }
    sw.ajax.request(pasteUrl,{'pkid': pasteNode.id,'copyNode':copyNode.id},function(){
        sw.tree.refreshNode(pasteNode);//刷新节点
    },true);
}
/************************组件******************************************/
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
    var formWin = sw.util.openDialog(formWinId,form,baseIcon,400,300,title);
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
    var formWin = sw.util.openDialog(formWinId,form,baseIcon,600,500,title,'displayName');
    return formWin;
}
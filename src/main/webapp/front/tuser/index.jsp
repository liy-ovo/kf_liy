<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/IconExtension.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/form.validator.rules.js"></script>


<script>
	$(function(){
		
		$("#tuserDataGrid").datagrid({
			url:'/kf/tuser/queryAll',//查询所有用户信息  json  rows total
			fit:true,
			rownumbers:true,
		//	pagination:true,
			fitColumns:true,
			toolbar:'#tb',
			columns:[[
                	 {field:'id',checkbox:true},
			         {title:'用户名',field:'username',width:200,},
			         {title:'密码',field:'password',width:150,},
			         {title:'年龄',field:'age',width:150,
					 	formatter:function (value,row,index) {
			             	console.log(value);
							return value;
                        }
					 },
			         {title:'电话',field:'phone',width:150,},
			         {title:'操作',field:'options',width:200,
			        	formatter:function(value,row,index){
			        		return "<a href='javascript:;' class='btn' onClick=\"delTuserInfo('"+ row.id +"');\"  data-options=\"iconCls:'icon-remove'\" >删除</a>&nbsp;&nbsp;"
                                +"<a data-options=\"iconCls:'icon-edit'\" onClick=\"openUpdateDialog('"+ row.id +"');\" href='javascript:;' class='btn'>修改</a>";
			        	}	 
			         },
			]],
			onLoadSuccess:function(){
				$(".btn").linkbutton({plain:true});
			}
		});
		
		
	});

    //用来处理数据的添加
    function openSaveDialog(){
        //注意:easyui在做页面包含时    1.不能出现id冲突  2.要求被引入的页面不能存在body 以上标签
        //打开对话框
        $("#da").dialog({
            width:600,
            height:400,
            title:'添加用户信息',
            iconCls:'icon-man',
            href:'${pageContext.request.contextPath}/front/tuser/save.jsp',//相当于两张页面的源码包含
            buttons:[{
                text:'保存信息',
                iconCls:'icon-save',
                handler:saveTuserInfo,
            },{
                text:'关闭',
                iconCls:'icon-cancel',
                handler:function(){
                    //关闭对话框
                    $("#da").dialog('close');
                }
            }],

        });
    }

    //保存用户信息
    function saveTuserInfo(){
        $("#saveTuserForm").form({
            url:"${pageContext.request.contextPath}/tuser/add",
            onSubmit: function(){
                return $("#saveTuserForm").form('validate');
            },
            success:function(data){//data是一个json字符串 用时必须转为js对象
                //map json
                $.messager.show({
                    title:'保存提示',
                    msg:'保存用户信息成功.....',
                    timeout:2000,
                    showType:'slide'
                });
                //关闭对话框
                $("#da").dialog('close');
                $("#tuserDataGrid").datagrid('reload',{});
            }
        });
        //提交form
        $("#saveTuserForm").form('submit');
    }

    //删除用户的信息
    function delTuserInfo(id){
        console.log(id);
        $.messager.confirm('提示', '确定要删除这条数据吗?', function(r){
            if (r){
                //发送ajax请求删除数据
                $.post("/kf/tuser/delete",{"id":id},function(result){//原生ajaxjquery封装 获取的是js对象
                    //状态提示
                    //响应回来刷新刷新datagrid
                    $("#tuserDataGrid").datagrid('reload');
                });
            }
        });
    }

    //打开修改的对话框
    function openUpdateDialog(id){

        $("#updateDialog").dialog({
            title:'用户修改信息',
            iconCls:'icon-man',
            width:600,
            height:400,
            href:'${pageContext.request.contextPath}/front/tuser/update.jsp?id='+id,
            buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler:updateTuserInfo,
            },{
                text:'关闭',
                handler:function(){$("#updateDialog").dialog('close');}
            }]
        });
    }

    //处理用户修改信息的函数
    function updateTuserInfo(){

        //form表单提交相关
        $("#updateTuserForm").form({
            url:'${pageContext.request.contextPath}/tuser/update',
            onSubmit:function(){
                return $(this).form('validate');
            },
            success:function(result){//   form表单控件 获取的数据位json格式字符串  使用时必须转为js对象
                //展示消息提示信息
                $.messager.show({
                    title:'修改提示',
                    msg:'修改用户信息成功.....',
                    timeout:2000,
                    showType:'slide'
                });
                //刷新datagrid
                $("#tuserDataGrid").datagrid('reload',{});
            }
        });
        //提交form
        $("#updateTuserForm").form('submit');
        //关闭对话框
        $("#updateDialog").dialog('close');
    }
    
    function downloadTusers() {
		$.ajax({
			url:'/kf/tuser/getXls'
		});
    }

    //处理删除
    //删除用户的信息
    function delTuserInfo(id){
        console.log(id);
        $.messager.confirm('提示', '确定要删除这条数据吗?', function(r){
            if (r){
                //发送ajax请求删除数据
                $.post("/kf/tuser/delete",{"id":id},function(result){//原生ajaxjquery封装 获取的是js对象
                    //状态提示
                    //响应回来刷新刷新datagrid
                    $("#tuserDataGrid").datagrid('reload');
                });
            }
        });
    }
</script>


<table id="tuserDataGrid" class="easyui-datagrid"></table>


<div id="tb">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openSaveDialog();">添加</a>
</div>

<!-- 用来处理保存的对话框 -->
<div id="da">

</div>


<!-- 用户修改的对话框 -->
<div id="updateDialog"></div>


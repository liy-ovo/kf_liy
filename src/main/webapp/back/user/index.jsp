<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>



<script>
	$(function(){
		
		$("#userDataGrid").datagrid({
			url:'/kf/user/queryAllByPage',//查询所有用户信息  json  rows total
			fit:true,
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			toolbar:'#tb',
			columns:[[
                	 {field:'id',checkbox:true},
			         {title:'用户名',field:'username',width:200,},
			         {title:'昵称',field:'nickname',width:150,},
                	 {title:'创建时间',field:'createTime',width:130,
                         formatter:function(value,row,index){
                             var unixTimestamp = new Date(value);
                             return unixTimestamp.toLocaleString();
                         }
					 },
                	 {title:'状态',field:'status',width:100,
					 	formatter:function (value,row,index) {
							if(value==1){
							    return "正常";
							}
							if(value==0){
							    return "冻结";
							}
                        }
					 },
			         {title:'操作',field:'options',width:200,
			        	formatter:function(value,row,index){
			        		return "<a href='javascript:;' class='btn' onClick=\"delUserInfo('"+ row.id +"');\"  data-options=\"iconCls:'icon-remove'\" >删除</a>&nbsp;&nbsp;"
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
            href:'${pageContext.request.contextPath}/back/user/save.jsp',//相当于两张页面的源码包含
            buttons:[{
                text:'保存信息',
                iconCls:'icon-save',
                handler:saveUserInfo,
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
    function saveUserInfo(){
        $("#saveUserForm").form({
            url:"${pageContext.request.contextPath}/user/add",
            onSubmit: function(){
                return $("#saveUserForm").form('validate');
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
                $("#userDataGrid").datagrid('reload',{});
            }
        });
        //提交form
        $("#saveUserForm").form('submit');
    }

    //删除用户的信息
    function delUserInfo(id){
        console.log(id);
        $.messager.confirm('提示', '确定要删除这条数据吗?', function(r){
            if (r){
                //发送ajax请求删除数据
                $.post("/kf/user/delete",{"id":id},function(result){//原生ajaxjquery封装 获取的是js对象
                    //状态提示
                    //响应回来刷新刷新datagrid
                    $("#userDataGrid").datagrid('reload');
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
            href:'${pageContext.request.contextPath}/back/user/update.jsp?id='+id,
            buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler:updateUserInfo,
            },{
                text:'关闭',
                handler:function(){$("#updateDialog").dialog('close');}
            }]
        });
    }

    //处理用户修改信息的函数
    function updateUserInfo(){

        //form表单提交相关
        $("#updateUserForm").form({
            url:'${pageContext.request.contextPath}/user/update',
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
                $("#userDataGrid").datagrid('reload',{});
            }
        });
        //提交form
        $("#updateUserForm").form('submit');
        //关闭对话框
        $("#updateDialog").dialog('close');
    }
    
    function downloadUsers() {
		$.ajax({
			url:'/kf/user/getXls'
		});
    }

    //处理删除选中
    function delSelectRows(){
        $.messager.confirm('提示', '确定要删除这些数据吗?', function(r){
			if(r){
				//返回所有行数
				var rows = $("#userDataGrid").datagrid('getChecked');
				if(rows.length>0){

					//参数格式: ?id=21&id=22&id=23
					var ids = [];
					$.each(rows,function(idx,row){
						console.log(row.id);
						ids.push(row.id);
					});
					console.log(ids);
					//使用ajax方式发送请求删除一组数据
					$.ajax({  //传递数组类型的参数 一定要设置
						url:"/kf/user/deleteUsers",
						method:"post",
						data:{ids:ids},
						dataType:"text",
						traditional:true,//用来传递数据类型的参数
						success:function(result){
							//刷新datagrid数据表格.
                            $("#userDataGrid").datagrid('reload');
						}
					});

				}else{
					$.messager.alert('提示','失少勾选一个要删除的数据!!!','info');
				}
			}
		});
    }
</script>


<table id="userDataGrid" class="easyui-datagrid"></table>


<div id="tb">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openSaveDialog();">添加</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delSelectRows();">删除选中</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="downloadUsers();">下载</a>
</div>

<!-- 用来处理保存的对话框 -->
<div id="da">

</div>


<!-- 用户修改的对话框 -->
<div id="updateDialog"></div>


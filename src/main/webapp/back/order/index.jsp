<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>



<script>
	$(function(){
		
		$("#orderDataGrid").datagrid({
			url:'/kf/order/queryAllByPage',//查询所有用户信息  json  rows total
			fit:true,
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			toolbar:'#tb',
			columns:[[
                	 {field:'id',checkbox:true},
			         {title:'订单编号',field:'no',width:200,},
			         {title:'创建时间',field:'createTime',width:130,
                         formatter:function(value,row,index){
                             var unixTimestamp = new Date(value);
                             return unixTimestamp.toLocaleString();
                         }
					 },
					 {title:'总价',field:'totalPrice',width:80,},
					 {title:'状态',field:'status',width:80,},
					 {title:'地址',field:'addressId',width:250,
                         formatter:function(value,row,index){
					     	if(value==null || value==''){
					     	    return "无";
							}
					     	var address;
					     	$.ajax({
								type:'post',
								url:'/kf/address/queryById?id='+value,
								dataType:'json',
								success:function (data) {
									address = data.house+data.houseNumber;
                                }
							});
					     	return address;
                         }
					 },
			         {title:'操作',field:'options',width:200,
			        	formatter:function(value,row,index){
			        		return "<a href='javascript:;' class='btn' onClick=\"delOrderInfo('"+ row.id +"');\"  data-options=\"iconCls:'icon-remove'\" >删除</a>&nbsp;&nbsp;"
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
            title:'添加订单信息',
            iconCls:'icon-man',
            href:'${pageContext.request.contextPath}/back/order/save.jsp',//相当于两张页面的源码包含
            buttons:[{
                text:'保存信息',
                iconCls:'icon-save',
                handler:saveOrderInfo,
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

    //保存订单信息
    function saveOrderInfo(){
        $("#saveOrderForm").form({
            url:"${pageContext.request.contextPath}/order/add",
            onSubmit: function(){
                return $("#saveOrderForm").form('validate');
            },
            success:function(data){//data是一个json字符串 用时必须转为js对象
                //map json
                $.messager.show({
                    title:'保存提示',
                    msg:'保存订单信息成功.....',
                    timeout:2000,
                    showType:'slide'
                });
                //关闭对话框
                $("#da").dialog('close');
                $("#orderDataGrid").datagrid('reload',{});
            }
        });
        //提交form
        $("#saveOrderForm").form('submit');
    }

    //删除订单的信息
    function delOrderInfo(id){
        console.log(id);
        $.messager.confirm('提示', '确定要删除这条数据吗?', function(r){
            if (r){
                //发送ajax请求删除数据
                $.post("/kf/order/delete",{"id":id},function(result){//原生ajaxjquery封装 获取的是js对象
                    //状态提示
                    //响应回来刷新刷新datagrid
                    $("#orderDataGrid").datagrid('reload');
                });
            }
        });
    }

    //打开修改的对话框
    function openUpdateDialog(id){

        $("#updateDialog").dialog({
            title:'订单修改信息',
            iconCls:'icon-man',
            width:600,
            height:400,
            href:'${pageContext.request.contextPath}/back/order/update.jsp?id='+id,
            buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler:updateOrderInfo,
            },{
                text:'关闭',
                handler:function(){$("#updateDialog").dialog('close');}
            }]
        });
    }

    //处理订单修改信息的函数
    function updateOrderInfo(){

        //form表单提交相关
        $("#updateOrderForm").form({
            url:'${pageContext.request.contextPath}/order/update',
            onSubmit:function(){
                return $(this).form('validate');
            },
            success:function(result){//   form表单控件 获取的数据位json格式字符串  使用时必须转为js对象
                //展示消息提示信息
                $.messager.show({
                    title:'修改提示',
                    msg:'修改订单信息成功.....',
                    timeout:2000,
                    showType:'slide'
                });
                //刷新datagrid
                $("#orderDataGrid").datagrid('reload',{});
            }
        });
        //提交form
        $("#updateOrderForm").form('submit');
        //关闭对话框
        $("#updateDialog").dialog('close');
    }

    //处理删除选中
    function delSelectRows(){
        $.messager.confirm('提示', '确定要删除这些数据吗?', function(r){
			if(r){
				//返回所有行数
				var rows = $("#orderDataGrid").datagrid('getChecked');
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
						url:"/kf/order/deleteOrders",
						method:"post",
						data:{ids:ids},
						dataType:"text",
						traditional:true,//用来传递数据类型的参数
						success:function(result){
							//刷新datagrid数据表格.
                            $("#orderDataGrid").datagrid('reload');
						}
					});

				}else{
					$.messager.alert('提示','失少勾选一个要删除的数据!!!','info');
				}
			}
		});
    }
</script>


<table id="orderDataGrid" class="easyui-datagrid"></table>


<div id="tb">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openSaveDialog();">添加</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delSelectRows();">删除选中</a>
</div>

<!-- 用来处理保存的对话框 -->
<div id="da">

</div>


<!-- 用户修改的对话框 -->
<div id="updateDialog"></div>


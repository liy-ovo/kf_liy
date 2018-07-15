<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>this is easyui</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/black/easyui.css">   
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/icon.css">   
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/IconExtension.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/form.validator.rules.js"></script>
	<script type="text/javascript">
		$(function () {
			
        });
	</script>
</head>

<body class="easyui-layout">
	
	
	<!-- 页面的头部分 -->
	<div data-options="region:'north',split:false,href:'${pageContext.request.contextPath }/front/header.jsp'" style="height:100px;"></div>
	   
	<!-- 页面的尾部 -->


    <!-- 中心面板 -->
    <div data-options="region:'center',split:false">


		<script>
            function getData(data){
                console.log(data);
                $("#goodsLucene").datagrid({
                    url:'/kf/goods/queryGoods?keyword='+data,//查询所有用户信息  json  rows total
                    fit:true,
                    rownumbers:true,
                    pagination:true,
                    fitColumns:true,
                    columns:[[
                        {field:'id'},
                        {title:'商品名',field:'name',width:200,
                            /*formatter:function(value,row,index){
                                return "<a></a>";
                            }*/
                        },
                        {title:'销量',field:'sales',width:100,},
                        {title:'简介',field:'instructions',width:300,},
                        {title:'操作',field:'options',width:150,},
                    ]],
                    onLoadSuccess:function(){
                        $(".btn").linkbutton({plain:true});
                    }
                });


            }
		</script>


		<table id="goodsLucene" class="easyui-datagrid"></table>


	</div>
	

</body>
</html>
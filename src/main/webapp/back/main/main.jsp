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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/js/form.validator.rules.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
	<script type="text/javascript">
		$(function(){
			var menuString;
			var level;
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/admin/getAdmin',
				dataType:'json',
				async:false,
				success:function (data) {
					console.log(data);
                    if(data==null || data==''){

                    }else{
                        level = data.powerLevel;
                    }

                }
			});
            console.log(level);

			if(level==0){
                menuString = '/kf/back/main/menu.json';
            }
            if(level==1){
                menuString = '/kf/back/main/menu1.json';
            }
            if(level==2){
                menuString = '/kf/back/main/menu2.json';
            }
			console.log(menuString);
            //发送ajax请求获取菜单数据
            $.get('/kf/back/main/menu.json',function(menus){
                console.log(menus);
                //遍历一级菜单
                $.each(menus,function(idx,menu){

                    var content = "<div style='text-align:center;'>";
                    //遍历二级菜单
                    $.each(menu.children,function(i,child){

                        //注意:js中不能直接传递对象类型的参数   传递之前需要将对象转为json字符串传递
                        //js 对象    ----->json格式字符串     这里要特别注意：方法格式必须为 onclick='addDj("+zxdxx+")   （单双引号保持一致）
                        content += "<a class='easyui-linkbutton' onclick='addTabs("+ JSON.stringify(child) +")' style='width:90%;margin-top:10px;border:1px red solid;' data-options=\"iconCls:'"+ child.iconCls +"',plain:true\" >"+ child.title +"</a>";
                    });
                    content +="</div>"


                    //遍历一次向accordion追加一次
                    $("#menu").accordion('add',{
                        title:menu.title,
                        iconCls:menu.iconCls,
                        content:content
                    });
                });


            },"JSON");

		});	
		
		
		//用来向中心布局的选项卡加入当前选项
		function addTabs(child){
			if(!$("#tt").tabs('exists',child.title)){//不存在添加
				$("#tt").tabs('add',{
					title:child.title,
					iconCls:child.iconCls,
					closable:true,
					href:'${pageContext.request.contextPath}/'+child.href,//引入当前点击的二级页面到选项卡中
					
				}); 
			}else{//存在切换到当前点击的选项卡中
				$("#tt").tabs('select',child.title)
			}
		};
	</script>
	<script>
        function kedit(keid) {
            var keditor = KindEditor.create('#'+keid, {
                items: ['cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough'],
                afterBlur: function () { this.sync(); }
            });
        }
	</script>
</head>
<body class="easyui-layout">
	
	
	<!-- 页面的头部分 -->
	<div data-options="region:'north',split:false,href:'${pageContext.request.contextPath }/back/main/header.jsp'" style="height:100px;"></div>
	   
	<!-- 页面的尾部 -->
    <div data-options="region:'south',split:false,href:'${pageContext.request.contextPath }/back/main/footer.jsp'" style="height:80px;"></div>
    
    <!-- 页面的左侧边栏 -->
    <div data-options="region:'west',title:'系统菜单',split:false" style="width:200px;">
    	
    	<!-- 渲染菜单 -->
    	<div id="menu" class="easyui-accordion" data-options="fit:true"></div>
    
    </div>   
    
    <!-- 中心面板 -->
    <div data-options="region:'center'">
    	
    	<!-- 中心选项卡 -->
    	<div id="tt" class="easyui-tabs" data-options="fit:true">
    	
    		  
    		
    	</div>
    
    </div>  
	

</body>
</html>
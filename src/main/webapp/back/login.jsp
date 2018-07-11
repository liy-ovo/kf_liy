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
</head>
<body>

	<div id="w" class="easyui-window" title="请先登录" data-options="" style="width:400px;padding:20px 70px 20px 70px;">
		<form method="post" enctype="multipart/form-data" id="adminLoginForm">
		<div style="margin-bottom:10px">
			<input class="easyui-textbox" id="username" name="username" style="width:100%;height:30px;padding:12px" data-options="required:true,prompt:'用户名',iconCls:'icon-man',iconWidth:38"/>
		</div>

		<div style="margin-bottom:20px">
			<input class="easyui-textbox" id="password" name="password" type="password" style="width:100%;height:30px;padding:12px" data-options="required:true,prompt:'登录密码',iconCls:'icon-lock',iconWidth:38"/>
		</div>

		<div style="margin-bottom:20px">
			<input class="easyui-textbox" id="captcha" name="captcha" style="width:50%;height:30px;padding:12px" data-options="required:true,tipPosition:'left',prompt:'验证码'"/>
			<img style=" margin:0 0 0 3px ; vertical-align:middle; height:26px;" src="/kf/admin/imageCode" onclick="this.src='/kf/admin/imageCode?'+Math.random()">
		</div>

		<%--<div style="margin-bottom:20px">
            <input type="checkbox" checked="checked" id="logrem">
            <span>Remember me</span>
        </div>--%>

		<div>
			<a href="javascript:;" onclick="dologin()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
				<span style="font-size:14px;">登录</span>
			</a>
		</div>
		</form>
	</div>

<script type="text/javascript">
    function dologin() {
        console.log(1);
        $("#adminLoginForm").form({
			url:"${pageContext.request.contextPath }/admin/login",
            onSubmit: function(data){
			    var username = $("#username").val();
			    var password = $("#password").val();
			    var captcha = $("#captcha").val();
			    console.log(username);
                console.log(password);
                console.log(captcha);
                if(username==null || username==''){
                    return false;
                }
                if(password==null || password==''){
                    return false;
                }
                if(captcha==null || captcha==''){
                    return false;
                }
                return true;
            },
			success:function (data) {
                console.log(data);
                if(data=='登录成功~~~'){
                    location.href='/kf/back/main/main.jsp';
                }
                $.messager.show({
                    title:'登录提示',
                    msg:data,
                    timeout:2000,
                    showType:'slide'
                });
            }
		});
        console.log(2);
        $("#adminLoginForm").form('submit');
    }
</script>
</body>
</html>
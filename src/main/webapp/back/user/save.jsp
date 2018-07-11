<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>

<<center>
<div style="padding-top: 70px;">

    <form id="saveUserForm" method="post" enctype="multipart/form-data">

        <div style="margin-bottom: 15px;">
            <%--@declare id="username"--%><label for="username">用户名:</label>
            <input class="easyui-textbox" type="text" name="username" />
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="password"--%><label for="password">密码:</label>
            <input class="easyui-textbox" type="text" name="password" value="123456"/>
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="nickname"--%><label for="nickname">昵称:</label>
            <input class="easyui-textbox" type="text" name="nickname" />
        </div>
    </form>
</div>

</center>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>

<<center>
<div style="padding-top: 70px;">

    <form id="saveAdminForm" method="post" enctype="multipart/form-data">

        <div style="margin-bottom: 15px;">
            <%--@declare id="username"--%><label for="username">管理员用户名:</label>
            <input class="easyui-textbox" type="text" name="username" />
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="password"--%><label for="password">密码:</label>
            <input class="easyui-textbox" type="text" name="password" value="123456"/>
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="powerLevel"--%><label for="powerLevel">权限:</label>
            <select class="easyui-combobox" name="status" id="powerLevel">
                <option value="1">普通管理员</option>
                <option value="2">商家管理员</option>
            </select>
        </div>
    </form>
</div>

</center>
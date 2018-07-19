<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<center>
    <div style="padding-top: 70px;">
        <form id="updateTuserForm" method="post">
            <input type="hidden" name="id" value=""/>
            <div style="margin-bottom: 15px;">
                <%--@declare id="username"--%><label for="username">用户名:</label>
                <input class="easyui-textbox" type="text" name="username" />
            </div>
            <div style="margin-bottom: 15px;">
                <%--@declare id="password"--%><label for="password">密码:</label>
                <input class="easyui-textbox" type="text" name="password" value="123456"/>
            </div>
            <div style="margin-bottom: 15px;">
                <%--@declare id="age"--%><label for="age">年龄:</label>
                <input class="easyui-textbox" type="text" name="age" />
            </div>
            <div style="margin-bottom: 15px;">
                <%--@declare id="phone"--%><label for="phone">电话:</label>
                <input class="easyui-textbox" type="text" name="phone" />
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
            $("#updateTuserForm").form('load',"${pageContext.request.contextPath}/tuser/queryById?id=${param.id}");
        });
    </script>
</center>


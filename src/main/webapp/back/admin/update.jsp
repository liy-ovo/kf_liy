<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<center>
    <div style="padding-top: 70px;">
        <form id="updateAdminForm" method="post">
            <input type="hidden" name="id" value=""/>
            <div style="margin-bottom: 15px;">
                <%--@declare id="username"--%><label for="username">用户名:</label>
                <input class="easyui-textbox" type="text" name="username" />
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
    <script type="text/javascript">
        $(function(){
            $("#updateAdminForm").form('load',"${pageContext.request.contextPath}/admin/queryById?id=${param.id}");
        });
    </script>
</center>


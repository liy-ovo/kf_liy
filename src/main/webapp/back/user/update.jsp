<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<center>
    <div style="padding-top: 70px;">
        <form id="updateUserForm" method="post">
            <input type="hidden" name="id" value=""/>
            <div style="margin-bottom: 15px;">
                <%--@declare id="username"--%><label for="username">用户名:</label>
                <input class="easyui-textbox" type="text" name="username" />
            </div>
            <div style="margin-bottom: 15px;">
                <%--@declare id="nickname"--%><label for="nickname">昵称:</label>
                <input class="easyui-textbox" type="text" name="nickname" />
            </div>
            <div style="margin-bottom: 15px;">
                <%--@declare id="status"--%><label for="status">状态:</label>
                <select class="easyui-combobox" name="status" id="status">
                    <option value="1">正常</option>
                    <option value="0">冻结</option>
                </select>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
            $("#updateUserForm").form('load',"${pageContext.request.contextPath}/user/queryById?id=${param.id}");
        });
    </script>
</center>


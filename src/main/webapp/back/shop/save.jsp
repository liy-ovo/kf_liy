<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>

<<center>
<div style="padding-top: 70px;">

    <form id="saveShopForm" method="post" enctype="multipart/form-data">

        <div style="margin-bottom: 15px;">
            <%--@declare id="name"--%><label for="name">商铺名:</label>
            <input class="easyui-textbox" type="text" name="name" />
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="address"--%><label for="address">地址:</label>
            <input class="easyui-textbox" type="text" name="address" />
        </div>
    </form>
</div>

</center>
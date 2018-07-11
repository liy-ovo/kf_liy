<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>

<<center>
<div style="padding-top: 70px;">

    <form id="saveThemeForm" method="post" enctype="multipart/form-data">

        <div style="margin-bottom: 15px;">
            <%--@declare id="name"--%><label for="name">主题名:</label>
            <input class="easyui-textbox" type="text" name="name" />
        </div>
        <div>
            <%--@declare id="img"--%><label for="img">上传缩略图:</label>
            <input class="easyui-filebox" name="img" id="img"/>
            <input type="button" id="imgSubmit" value="上传">
            <input type="hidden" name="thumbnail" id="path">
            <br>
            <img style="width:30px;height:30px;display: none" src="" id="thumbnailImage">
            <script>
                $(function () {
                    $("#imgSubmit").click(function () {
                        console.log(1);
                        $("#saveThemeForm").form({
                            //传递数组类型的参数 一定要设置
                            url:"${pageContext.request.contextPath}/theme/img",
                            success:function(result){
                                //刷新datagrid数据表格.
                                console.log(result);
                                $("#path").val(result);
                                $("#thumbnailImage").attr('src',result).css('display','inline');
                            }

                        })
                        $("#saveThemeForm").submit();
                    });
                });
            </script>
        </div>
    </form>
</div>

</center>
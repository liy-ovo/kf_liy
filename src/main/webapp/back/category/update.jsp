<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<center>
    <div style="padding-top: 70px;">
        <form id="updateUserForm" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id"/>
            <div>
                <%--@declare id="parent"--%><label for="parent">一级分类:</label>
                <input class="easyui-combobox" name="pid" id="parent" data-options="
                        valueField: 'id',
                        textField: 'name',
                        loader:function (param,success,error) {
                            $.ajax({
                                url: '/kf/category/queryFirstLevels',
                                dataType: 'json',
                                success:function(data){
                                    success(data);
                                }
                            });
                        },
                        onLoadSuccess:function(data){
                        }"/>

            </div>
            <br>
            <div style="margin-bottom: 15px;">
                <%--@declare id="name"--%><label for="name">分类名:</label>
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
                            $("#updateUserForm").form({
                                //传递数组类型的参数 一定要设置
                                url:"${pageContext.request.contextPath}/category/img",
                                success:function(result){
                                    //刷新datagrid数据表格.
                                    console.log(result);
                                    $("#path").val(result);
                                    $("#thumbnailImage").attr('src',result).css('display','inline');
                                }

                            })
                            $("#updateUserForm").submit();
                        });
                    });
                </script>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
            $("#updateUserForm").form('load',"${pageContext.request.contextPath}/category/queryById?id=${param.id}");
        });
    </script>
</center>


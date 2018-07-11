<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function(){

        $("#picDataGrid").datagrid({
            url:'/kf/picture/queryByGoodsId?id=${param.goodsId}',//查询所有用户信息  json  rows total
            method:'get',
            rownumbers:true,
            columns:[[
                {title:'图片',field:'path',width:200,
                    formatter:function(value,row,index){
                        if(value==null){
                            return "";
                        }
                        return "<img src='/kf"+value+"' style='width:180px;height:80px'>";
                    }
                },
                {title:'上传时间',field:'createTime',width:150,
                    formatter:function(value,row,index){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }
                },
                {title:'操作',field:'options',width:70,
                    formatter:function(value,row,index){
                        return "<a href='javascript:;' class='btn' onClick=\"delPictureInfo('"+ row.id +"');\"  data-options=\"iconCls:'icon-remove'\" >删除</a>&nbsp;&nbsp;";
                    }
                },
            ]],
            onLoadSuccess:function(){
                $(".btn").linkbutton({plain:true});
            }
        });

        function delPictureInfo(id) {
            $.messager.confirm('提示', '确定要删除该图片吗?', function(r){
                if (r){
                    //发送ajax请求删除数据
                    $.post("/kf/picture/delete",{"id":id},function(result){//原生ajaxjquery封装 获取的是js对象
                        //状态提示
                        //响应回来刷新刷新datagrid
                        $("#picDataGrid").datagrid('reload');
                    });
                }
            });
        }

    });
</script>
<<center>
<div style="padding-top: 70px;">

    <form id="uploadPicsForm" method="post" enctype="multipart/form-data">
        <input type="hidden" id="goodsId" name="goodsId" value="${param.goodsId}"/>
        <div>
            <div>
                <%--@declare id="pics"--%><label for="pics">上传商品图:</label>
                <input class="easyui-filebox" name="pics" id="pics" data-options="multiple:true"/>
                    <input type="button" id="imgSubmit" value="上传">
                    <script>
                        $(function () {
                            $("#imgSubmit").click(function () {
                                console.log($("#goodsId").val());
                                $("#uploadPicsForm").form({
                                    //传递数组类型的参数 一定要设置
                                    url:"${pageContext.request.contextPath}/picture/upload",
                                        success:function(result){
                                        //刷新datagrid数据表格.
                                        $("#picDataGrid").datagrid('reload');
                                    }

                                })
                                $("#uploadPicsForm").submit();
                            });
                        });
                    </script>
            </div>
            <br>
        </div>
    </form>

</div>
<div>
    <table id="picDataGrid" class="easyui-datagrid"></table>
</div>
</center>
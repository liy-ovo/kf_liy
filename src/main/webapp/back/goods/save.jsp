<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<center>
<div style="padding-top: 70px;">

    <form id="saveGoodsForm" method="post" enctype="multipart/form-data">

        <div style="margin-bottom: 15px;">
            <%--@declare id="name"--%><label for="name">商品名:</label>
            <input class="easyui-textbox" type="text" name="name" />
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="specifications"--%><label for="specifications">规格:</label>
            <input class="easyui-textbox" type="text" name="specifications" />
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="price"--%><label for="price">价格:</label>
            <input class="easyui-textbox" type="text" name="price" />
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="sales"--%><label for="sales">月销量:</label>
            <input class="easyui-textbox" type="text" name="sales" value="0"/>
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="seller"--%><label for="seller">销售方:</label>
                <input class="easyui-combobox" name="seller" id="seller" data-options="
                        valueField: 'id',
                        textField: 'name',
                        loader:function (param,success,error) {
                            $.ajax({
                                url: '/kf/shop/queryAll',
                                dataType: 'json',
                                success:function(data){
                                    success(data);
                                }
                            });
                        },
                        onLoadSuccess:function(){
                            $('#seller').combobox('setValue','--无--');
                        }"/>
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="theme"--%><label for="theme">主题:</label>
            <input class="easyui-combobox" id="theme" name="themeId" data-options="
                        valueField: 'id',
                        textField: 'name',
                        loader:function (param,success,error) {
                            $.ajax({
                                url: '/kf/theme/queryAll',
                                dataType: 'json',
                                success:function(data){
                                    success(data);
                                }
                            });
                        },
                        onLoadSuccess:function(){
                            $('#theme').combobox('setValue','--无--');
                        }"/>
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="category"--%><label for="category">商品分类:</label>
            <input class="easyui-combobox" name="category" id="category" data-options="
                valueField: 'id',
                textField: 'name',
                multiple:true,
                url: '/kf/category/querySecondLevels',
                groupField: 'parent'"/>
        </div>
        <div style="margin-bottom: 15px;">
            <%--@declare id="detail"--%><label for="detail">商品简介</label>
                <textarea style="width:500px;height:200px;" id="instructions" name="instructions" style="width:700px;height:200px;"></textarea>
                <script type="text/javascript">
                    $(function () {
                        //详情编辑器
                        kedit("instructions");
                    });
                </script>
        </div>
    </form>
</div>

</center>
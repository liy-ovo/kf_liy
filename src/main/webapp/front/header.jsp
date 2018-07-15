<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div style="height:80px;text-align: center;overflow: hidden;margin: 30px">
	<form method="post" id="keywordForm">
		<input class="easyui-textbox" id="keyword" name="keyword" data-options="buttonAlign:'right',buttonIcon:'icon-reload',buttonText:'查询',width:500,height:40,prompt:'请输入关键字查询'"/>
	</form>
	<script>
		$(function () {
			$("#keyword").textbox({onClickButton:function() {
					console.log(1);
                    getData($("#keyword").val());
                }});
        });
	</script>
</div>
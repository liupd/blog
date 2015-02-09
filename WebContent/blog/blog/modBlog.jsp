<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/blog/common/import.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Pragrma" content="no-cache">
		<meta http-equiv="Expires" content="0">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1.0">
		<title></title>
		<%@ include file="/blog/common/cssjs.jsp" %>
		<script type="text/javascript" charset="utf-8" src="js/ueditor.config.js"></script>
    	<script type="text/javascript" charset="utf-8" src="js/ueditor.all.min.js"> </script>
    	<script type="text/javascript" charset="utf-8" src="js/zh-cn.js"></script>
		<script type="text/javascript">
			var ue = UE.getEditor('editor');
			$.get("classifList.do", null, function(data) {
				if (data.success == true) {
					var classifyList = data.classifyList;
					var options = "<option value=''>--请选择--</option>";
					for (var i = 0; i < classifyList.length; i++) {
						options += "<option value='" + classifyList[i]._id + "'>" + classifyList[i].classifyName + "</option>";
					}
					$("#classifyId").html(options);
					$("#classifyId").val("${blog.classifyId}");
				}
			}, "json");
			function goback(){
				$("#backForm").submit();
			}
			
			// 保存
			function save() {
				$("#content").val(UE.getEditor('editor').getContent());
				$("#addForm").ajaxSubmit({
					beforeSubmit: function() {
						var title = $("#title").val();
						if (title == '') {
							alert("请输入标题");
							$("#title").focus();
							return false;
						}
						var keywords = $("#keywords").val();
						if (keywords == '') {
							alert("请输入关键字");
							$("#keywords").focus();
							return false;
						}
						var classifyId = $("#classifyId").val();
						if (classifyId == '') {
							alert("请选择分类");
							return false;
						}
						var content = $("#content").val();
						if (content == '') {
							alert("请输入内容");
							$("#content").focus();
							return false;
						}
						return true;
					},
					success: function(data, statusText) {
						if (data.success) {
							alert(data.message);
							goback();	
						} else {
							alert(data.message);
						}
					},
					dataType: 'json'
				});
			}
		</script>
	</head>
	<body>
	<div class="panel-heading" style="padding:5px;">修改博客</div>
		<form id="addForm" method="post" action="modBlog.do?_id=${blog._id}">
		<input type="hidden" name="content" id="content" value="">
			<table class="form-table" style="margin:auto; width:100%; height:100%">
                <tr>
                	<td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;标题:</td>
                    <td>
                        <input type="text" class="form-control input-sm" style="width:200px;" id="title" name="title" 
                          value="${blog.title}"	placeholder="请输入标题...">
                    </td>
                </tr>
                <tr>
                    <td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;关键字:</td>
                    <td>
                        <input type="text" class="form-control input-sm" style="width:200px;" id="keywords" name="keywords" 
                        	value="${blog.keywords}" placeholder="请输入关键字...">
                    </td>
                </tr>
                <tr>
                    <td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;博客分类:</td>
                    <td>
                    	<select name="classifyId" id="classifyId" class="form-control input-sm" style="width:160px;"></select>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;内容:</td>
                    <td>
                    	<script id="editor" type="text/plain" style="width:1024px;height:500px;">
							${blog.content}
						</script>
                    </td>
                </tr>
			</table>
		</form>
		<div class="panel-footer" style="text-align:center">
				<button type="button" class="btn btn-primary btn-sm" onclick="javascript:save();"><span class="glyphicon glyphicon-saved"></span>&nbsp;修改</button>
				<button type="button" class="btn btn-success btn-sm" onclick="javascript:goback();"><span class="glyphicon glyphicon-backward"></span>&nbsp;返回</button>
		</div>
		<form id="backForm" action="blogList.htm" method="post">
		</form>
	</body>
</html>
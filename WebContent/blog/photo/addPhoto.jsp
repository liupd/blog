<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/blog/common/import.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragrma" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title></title>
<%@ include file="/blog/common/cssjs.jsp"%>
<script type="text/javascript">
	function save() {
		$("#addForm").ajaxSubmit({
			beforeSubmit : function() {
				var fileId = $("#fileId").val();
				if (fileId == '') {
					alert("请上传图片");
					$("#fileId").focus();
					return false;
				}
				var classifyId = $("#classifyId").val();
				if (classifyId == '') {
					alert("请选择分类");
					$("#classifyId").focus();
					return false;
				}
				return true;
			},
			success : function(data, statusText) {
				if (data.success) {
					alert(data.message);
					goback();
				} else {
					alert(data.message);
				}
			},
			dataType : 'json'
		});
	}

	function goback() {
		$("#backForm").submit();
	}

	$.get("classifList.do", null, function(data) {
		if (data.success == true) {
			var classifyList = data.classifyList;
			var options = "<option value=''>--请选择--</option>";
			for (var i = 0; i < classifyList.length; i++) {
				options += "<option value='" + classifyList[i]._id + "'>"
						+ classifyList[i].classifyName + "</option>";
			}
			$("#classifyId").html(options);
			$("#classifyId").val('${photo.classifyId}');
		}
	}, "json");
</script>
</head>
<body>
	<form id="addForm" method="post" action="createPhoto.do">
		<table class="form-table"
			style="margin: auto; width: 100%; height: 100%">
			<tr>
				<td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;</td>
				<td><input type="hidden" id="fileId" value="" name="fileId">
					<iframe src="upload.htm" width="700px" height="400px"
						scrolling="no" frameborder="no" border="0" marginwidth="0"
						marginheight="0"> </iframe></td>
			</tr>
			<tr>
				<td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;分类:</td>
				<td><select name="classifyId" id="classifyId"
					class="form-control input-sm" style="width: 160px;"></select></td>
			</tr>
		</table>
	</form>
	<div class="panel-footer" style="text-align: center">
		<button type="button" class="btn btn-primary btn-sm"
			onclick="javascript:save();">
			<span class="glyphicon glyphicon-saved"></span>&nbsp;保存
		</button>
		<button type="button" class="btn btn-success btn-sm"
			onclick="javascript:goback();">
			<span class="glyphicon glyphicon-backward"></span>&nbsp;返回
		</button>
	</div>
	<form id="backForm" action="photoList.htm" method="post"></form>
</body>
</html>
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
				var periodName = $("#content").val();
				if (periodName == '') {
					alert("请输入内容");
					$("#content").focus();
					return false;
				}
				return true;
			},
			success : function(data, statusText) {
				if (data.success) {
					alert(data.message);
					window.parent.closeModalDialog();
				} else {
					alert(data.message);
				}
			},
			dataType : 'json'
		});
	}

	function check() {
		$("#modalLabel").text("选择图片");
		$("#modalFrame").attr("src",
				"photoListForCheck.htm?classifyId=54d7f424d4c68cbef82dbb72");
		$("#modalDialog").modal("show");
	}
</script>
</head>
<body>
	<form id="addForm" method="post" action="createDoing.do">
		<table class="form-table"
			style="margin: auto; width: 100%; height: 100%">
			<tr>

				<td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;展示图:</td>
				<td><input type="hidden" id="fileId" value="" name="fileId">
					<button type="button" class="btn btn-primary btn-sm"
						onclick="javascript:check();">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;选择展示图
					</button></td>
			</tr>

			<tr>
				<td align="right" class="alt" nowrap="nowrap"><font color="red">*</font>&nbsp;碎言碎语:</td>
				<!-- <td><input type="text" class="form-control input-sm"
					style="width: 400px;" id="content" name="content"
					placeholder="请输入内容..."></td> -->
				<td><textarea cols="40" rows="5" id="content" name="content"
					placeholder="请输入内容..."></textarea></td>
			</tr>
		</table>
	</form>
		<jsp:include page="/blog/common/modalDialogToCheck.jsp" />
</body>
</html>
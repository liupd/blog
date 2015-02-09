<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/blog/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客分类管理</title>
<%@ include file="/blog/common/cssjs.jsp"%>
</head>
<script type="text/javascript">
	/* 	function add() {
	 $("#modalLabel").text("上传图片");
	 $("#modalFrame").attr("src", "addPhoto.htm");
	 $("#modalDialog").modal("show");
	 } */

	function add() {
		$("#form1").attr("action", "addPhoto.htm");
		$("#form1").submit();
	}

	function check() {
		var index = 0;
		var fileId = "";
		$("input[name=chkbox]").each(function() {
			if ($(this).prop("checked")) {
				index++;
				fileId = $(this).val();
			}
		});
		if (index > 1) {
			alert("请只选择一张图片");
			return;
		} else if (index == 0) {
			alert("请选择图片");
			return;
		} else {
			var fileIdElement = parent.document.getElementById('fileId');
			if (fileIdElement != undefined) {
				fileIdElement.value = fileId;
			}
			window.parent.closeModalDialog();
		}
	}
</script>
<body>
	<form id="form1" name="form1" action="photoList.htm" method="post">
		<input type="hidden" name="returnUrl" value="photoList.htm" />
		<div class="panel panel-primary" style="margin: 0px;">
			<div class="panel-heading" style="padding: 5px;">图库</div>
			<div class="panel-body" style="padding: 5px;">
				<table style="margin-bottom: 5px;">
					<tr>
						<td>
							<button type="button" class="btn btn-primary btn-sm"
								onclick="javascript:check();">
								<span class="glyphicon glyphicon-saved"></span>&nbsp;确定
							</button>
						</td>
					</tr>
				</table>
				<div style="height: 33px; overflow-y: scroll">
					<table class="table table-condensed table-hover table-bordered"
						style="margin: 0px;">
						<tr style="background: #f5f5f5">
							<th style="text-align: center; width: 5%"></th>
							<th style="width: 30%">缩略图</th>
							<th style="width: 30%">分类</th>
							<th style="width: 20%">创建时间</th>
						</tr>
					</table>
				</div>
				<div id="listDiv" style="overflow-y: scroll">
					<table class="table table-condensed table-hover table-bordered"
						style="margin: 0px;">
						<c:if test="${photoList != null && fn:length(photoList) > 0}">
							<c:forEach items="${photoList}" var="photo">
								<tr>
									<td align="center" style="width: 5%"><input
										type="checkbox" name="chkbox" value="${photo.fileId}" /></td>
									<td style="width: 30%"><img
										src="download.do?fileId=${photo.fileId}"></td>
									<td style="width: 30%">${classifyMap[photo.classifyId]}</td>
									<td style="width: 20%"><fmt:formatDate
											value="${photo.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${photoList == null || fn:length(photoList) == 0}">
							<tr>
								<td colspan="6" align="center"><font color="red"><b>对不起，没有可展示的数据...</b></font></td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
			<div class="panel-footer" style="padding: 0px;"><jsp:include
					page="/blog/common/pagination.jsp" /></div>
		</div>
	</form>
</body>
</html>
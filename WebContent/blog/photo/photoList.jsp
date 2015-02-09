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
	
	function mod(_id) {
		$("#modalLabel").text("修改图片");
		$("#modalFrame").attr("src", "modPhoto.htm?_id="+_id);
		$("#modalDialog").modal("show");
	}
	/** 批量删除 */
	function batchDel() {
		var _ids = "";
		$("input[name=chkbox]").each(function() {
			if ($(this).prop("checked")) {
				_ids += $(this).val() + ",";
			}
		});
		if (_ids == "") {
			alert("请选择需要删除的项!");
		} else {
			_ids = _ids.substring(0, _ids.length - 1);
			del(_ids);
		}
	}

	/** 删除 */
	function del(_ids) {
		if (confirm("确认删除?")) {
			$.post('deletePhoto.do', {
				_ids : _ids
			}, function(data) {
				if (data.success) {
					alert(data.message);
					doQuery();
				} else {
					alert(data.message);
				}
			}, 'json');
		}
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
			$("#classifyId").val('${classifyId}');
		}
	}, "json");
	
	function selectChange(){
		var classifyId = $("#classifyId").val();
		window.location.href = 'photoList.htm?classifyId='+classifyId;
	}
</script>
<body>
	<form id="form1" name="form1" action="photoList.htm" method="post">
		<input type="hidden" name="returnUrl" value="photoList.htm" />
		<div class="panel panel-primary" style="margin: 0px;">
			<div class="panel-heading" style="padding: 5px;">图库</div>
			<div class="panel-body" style="padding: 5px;">
				<div
					style="padding: 5px; margin-bottom: 5px; background: #F4F4F4; border: 1px solid; border-color: #428bca;">
					<table>
						<tr>
							<td>分类：</td>
							<td><select name="classifyId" id="classifyId"
								class="form-control input-sm" style="width: 160px;" onchange="selectChange()"></select></td>
							<td>
						</tr>
					</table>
				</div>
				<table style="margin-bottom: 5px;">
					<tr>
						<td>
							<button type="button" class="btn btn-primary btn-sm"
								onclick="javascript:add();">
								<span class="glyphicon glyphicon-plus"></span>&nbsp;上传图片
							</button>
							<button type="button" class="btn btn-danger btn-sm"
								onclick="javascript:batchDel();">
								<span class="glyphicon glyphicon-trash"></span>&nbsp;删除
							</button>
						</td>
					</tr>
				</table>
				<div style="height: 33px; overflow-y: scroll">
					<table class="table table-condensed table-hover table-bordered"
						style="margin: 0px;">
						<tr style="background: #f5f5f5">
							<th style="text-align: center; width: 5%"><input
								type="checkbox" onclick="javascript:checkAll(this);" /></th>
							<th style="width: 15%">缩略图</th>
							<th style="width: 30%">分类</th>
							<th style="width: 20%">创建时间</th>
							<th style="text-align: center; width: 25%">操作</th>
						</tr>
					</table>
				</div>
				<div id="listDiv" style="overflow-y: scroll">
					<table class="table table-condensed table-hover table-bordered"
						style="margin: 0px;">
						<c:if test="${photoList != null && fn:length(photoList) > 0}">
							<c:forEach items="${photoList}" var="photo">
									<td align="center" style="width: 5%"><input
										type="checkbox" name="chkbox" value="${photo._id}" /></td>
									<td style="width: 15%"><img src="download.do?fileId=${photo.fileId}"></td>
									<td style="width: 30%">${classifyMap[photo.classifyId]}</td>
									<td style="width: 20%"><fmt:formatDate
											value="${photo.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td style="width: 25%" align="center">
										<button type="button" class="btn btn-link btn-xs"
											onclick="javascript:mod('${photo._id}');">
											<span class="glyphicon glyphicon-edit"></span>&nbsp;修改
										</button>
										<button type="button" class="btn btn-link btn-xs"
											onclick="javascript:del('${photo._id}');">
											<span class="glyphicon glyphicon-trash"></span>&nbsp;删除
										</button>
									</td>
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
	<jsp:include page="/blog/common/modalDialog.jsp" />
</body>
</html>
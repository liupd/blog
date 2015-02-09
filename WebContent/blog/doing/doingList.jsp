<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/blog/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客分类管理</title>
<%@ include file="/blog/common/cssjs.jsp" %>
</head>
<script type="text/javascript">
function add() {
	$("#modalLabel").text("新增心情");
	$("#modalFrame").attr("src", "addDoing.htm");
	$("#modalDialog").modal("show");
}
function mod(_id) {
	$("#modalLabel").text("修改心情");
	$("#modalFrame").attr("src", "modDoing.htm?_id="+_id);
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
    if (confirm("确认删除所选心情?")) {
        $.post('deleteDoing.do', {_ids: _ids}, function(data) {
        	if (data.success) {
        		alert(data.message);
				doQuery();
            } else {
            	alert(data.message);
            }
        }, 'json');
    }
}
</script>
<body>

<form id="form1" name="form1" action="doingList.htm" method="post">
			<input type="hidden" name="returnUrl" value="doingList.htm"/>
			<div class="panel panel-primary" style="margin:0px;">
				<div class="panel-heading" style="padding:5px;">心情列表</div>
				<div class="panel-body" style="padding:5px;">
					<div style="padding:5px; margin-bottom:5px; background:#F4F4F4;border:1px solid;border-color:#428bca;">
						<table>
							<tr>
								<td>
									<div class="input-group input-group-sm" style="width:200px">
										<input type="text" class="form-control" placeholder="请输入内容..." id="content" name="content" value="${doing.content}"/>
										<span class="input-group-btn">
									        <button type="button" class="btn btn-primary btn-sm" onclick="javascript:doQuery();"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
									    </span>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<table style="margin-bottom:5px;">
						<tr>
							<td>
								<button type="button" class="btn btn-primary btn-sm" onclick="javascript:add();"><span class="glyphicon glyphicon-plus"></span>&nbsp;写心情</button>
								<button type="button" class="btn btn-danger btn-sm" onclick="javascript:batchDel();"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</button>
							</td>
						</tr>
					</table>
					<div style="height:33px;overflow-y:scroll">
						<table class="table table-condensed table-hover table-bordered" style="margin:0px;">
							<tr style="background:#f5f5f5">
								<th style="text-align:center;width:5%"><input type="checkbox" onclick="javascript:checkAll(this);" /></th>
								<th style="width:20%">编码</th>
								<th style="width:30%">内容</th>
								<th style="width:20%">创建时间</th>
								<th style="text-align:center;width:25%">操作</th>
							</tr>
						</table>
					</div>
					<div id="listDiv" style="overflow-y:scroll">
						<table class="table table-condensed table-hover table-bordered" style="margin:0px;">
							<c:if test="${doingList != null && fn:length(doingList) > 0}">
								<c:forEach items="${doingList}" var="doing">
									<tr>
										<td align="center" style="width:5%">
											<input type="checkbox" name="chkbox" value="${doing._id}"/>
										</td>
										<td style="width:20%">${doing._id}</td>
										<td style="width:30%">${doing.content}</td>
										<td style="width:20%"><fmt:formatDate value="${doing.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td style="width:25%" align="center">
											<button type="button" class="btn btn-link btn-xs" onclick="javascript:mod('${doing._id}');"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
											<button type="button" class="btn btn-link btn-xs" onclick="javascript:del('${doing._id}');"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${doingList == null || fn:length(doingList) == 0}">
								<tr>
									<td colspan="6" align="center"><font color="red"><b>对不起，没有可展示的数据...</b></font></td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
				<div class="panel-footer" style="padding:0px;"><jsp:include page="/blog/common/pagination.jsp"/></div>
			</div>
		</form>
		<jsp:include page="/blog/common/modalDialog.jsp"/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="import.jsp" %>
<input type="hidden" id="curPage" name="curPage" value="${pagination.curPage}" />
<input type="hidden" id="totalPage" name="totalPage" value="${pagination.totalPage}" />
<ul class="pagination" style="margin:5px;float:right;">
	<li><span>第${pagination.firstRow} - ${pagination.lastRow}条记录 / 共${pagination.totalCount}条</span></li>
</ul>
<ul class="pagination" style="margin:5px;">
	<li>
		<span style="padding-top:5px;padding-bottom:4px;">
			每页&nbsp;
			<select style="width:60px;height:20px;margin:0px;text-align:center;" name="numPerPage">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="15">15</option>
				<option value="20">20</option>
			</select>
			&nbsp;条
		</span>
	</li>
	<!-- 分页首页按钮 -->
	<c:choose>
		<c:when test="${pagination.curPage <= 1}">
			<li class="disabled"><span>首页</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:skipToFirstPage();">首页</a></li>
		</c:otherwise>
	</c:choose>
	<!-- 前一页按钮 -->
	<c:choose>
		<c:when test="${pagination.curPage <= 1}">
			<li class="disabled"><span>前一页</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:skipToPrePage();">前一页</a></li>
		</c:otherwise>
	</c:choose>
	<!-- 跳转 -->
	<li>
		<span style="padding-top:5px;padding-bottom:4px;">第&nbsp;<input id="page" type="text" style="margin:0px;width:25px;height:20px;padding:0px;text-align:center;" id="current_page" value="${pagination.curPage}"/>&nbsp;页</span>
	</li>
	<li><a id="skip" href="javascript:skipTo();">跳转</a></li>
	<li><span>共&nbsp;${pagination.totalPage}&nbsp;页</span></li>
	<!-- 下一页按钮 -->
	<c:choose>
		<c:when test="${pagination.curPage >= pagination.totalPage}">
			<li class="disabled"><span>下一页</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:skipToNextPage();">下一页</a></li>
		</c:otherwise>
	</c:choose>
	<!-- 分页尾页按钮 -->
	<c:choose>
		<c:when test="${pagination.curPage >= pagination.totalPage}">
			<li class="disabled"><span>尾页</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:skipToLastPage();">尾页</a></li>
		</c:otherwise>
	</c:choose>
</ul>
<script>
	$("select[name=numPerPage]").val("<c:out value='${pagination.numPerPage}'/>");
	$("select[name=numPerPage]").change(function() {
		skipTo();
	});
	function skipToFirstPage() {
		skipPage(1);
	}
	function skipToPrePage() {
		var curPage = document.getElementById("curPage").value;
		var page = Number(curPage) - 1;
		skipPage(page);
	}
	function skipTo() {
		var page = document.getElementById("page").value;
		skipPage(page);
	}
	function skipToNextPage() {
		var curPage = document.getElementById("curPage").value;
		var page = Number(curPage) + 1;
		skipPage(page);
	}
	function skipToLastPage() {
		var page = document.getElementById("totalPage").value;
		skipPage(page);
	}
	function skipPage(page) {
		var curPage = Number(page);
		if (curPage > 2147483647) {
			curPage = 2147483647;
		} else if (curPage < 0) {
			curPage = 1;
		}
		document.getElementById("curPage").value = curPage;
		document.form1.submit();
	}
</script>
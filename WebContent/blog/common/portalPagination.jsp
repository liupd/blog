<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="import.jsp"%>
<input type="hidden" id="curPage" name="curPage"
	value="${pagination.curPage}" />
<input type="hidden" id="totalPage" name="totalPage"
	value="${pagination.totalPage}" />


<div class="page">
		<!-- 分页首页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage <= 1}">
				<b class="disabled">&lt;&lt;</b>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToFirstPage();"><b>&lt;&lt;</b></a>
			</c:otherwise>
		</c:choose>
		<!-- 前一页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage <= 1}">
				<b class="disabled">&lt;</b>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToPrePage();"><b>&lt;</b></a>
			</c:otherwise>
		</c:choose>
		<b>${pagination.curPage}</b>
		共<b class="disabled">${pagination.totalPage}</b>页
		<!-- 下一页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage >= pagination.totalPage}">
				<b class="disabled">&gt;</b>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToNextPage();"><b>&gt;</b></a>
			</c:otherwise>
		</c:choose>
		<!-- 分页尾页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage >= pagination.totalPage}">
				<b class="disabled">&gt;&gt;</b>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToLastPage();"><b>&gt;&gt;</b></a>
			</c:otherwise>
		</c:choose>
</div>

<script>
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
		document.search.submit();
	}
</script>
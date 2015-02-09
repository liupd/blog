<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="import.jsp"%>
<input type="hidden" id="curPage" name="curPage"
	value="${pagination.curPage}" />
<input type="hidden" id="totalPage" name="totalPage"
	value="${pagination.totalPage}" />

<div class="page">
	<div class="con">
		<!-- 分页首页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage <= 1}">
				<span class="disabled">首页</span>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToFirstPage();">首页</a>
			</c:otherwise>
		</c:choose>
		<!-- 前一页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage <= 1}">
				<span class="disabled">上一页</span>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToPrePage();">上一页</a>
			</c:otherwise>
		</c:choose>
		第&nbsp;${pagination.curPage}&nbsp;页
		共&nbsp;${pagination.totalPage}&nbsp;页
		<!-- 下一页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage >= pagination.totalPage}">
				<span class="disabled">下一页</span>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToNextPage();">下一页</a>
			</c:otherwise>
		</c:choose>
		<!-- 分页尾页按钮 -->
		<c:choose>
			<c:when test="${pagination.curPage >= pagination.totalPage}">
				<span class="disabled">尾页</span>
			</c:when>
			<c:otherwise>
				<a href="javascript:skipToLastPage();">尾页</a>
			</c:otherwise>
		</c:choose>
	</div>
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
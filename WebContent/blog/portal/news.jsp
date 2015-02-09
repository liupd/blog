<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/blog/common/import.jsp"%>
<h3>
	<p>
		最新<span>文章</span>
	</p>
</h3>
<ul class="rank">
	<c:if test="${newsblogList != null && fn:length(newsblogList) > 0}">
		<c:forEach items="${newsblogList}" var="blog">
			<li><a href="show.htm?_id=${blog._id}" target="_blank">${blog.title}</a></li>
		</c:forEach>
	</c:if>
</ul>
<h3 class="ph">
	<p>
		点击<span>排行</span>
	</p>
</h3>
<ul class="paih">
	<c:if test="${hotblogList != null && fn:length(hotblogList) > 0}">
		<c:forEach items="${hotblogList}" var="blog">
			<li><a href="show.htm?_id=${blog._id}" target="_blank">${blog.title}</a></li>
		</c:forEach>
	</c:if>
</ul>
<h3 class="links">
	<p>
		友情<span>链接</span>
	</p>
</h3>
<ul class="website">
	<li><a>暂时还没有</a></li>
</ul>

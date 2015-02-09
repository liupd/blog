<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>nick'blog</title>
<meta name="keywords" content="nick的博客" />
<meta name="description" content="开源 共享" />
<link href="css/mood.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/blog/common/head.jsp"%>
	<div class="moodlist">
		<h1 class="t_nav">
			<span>删删写写，回回忆忆，虽无法行云流水，却也可碎言碎语。</span><a href="index.htm" class="n1">网站首页</a><a
				href="doingListForPortal.htm" class="n2">碎言碎语</a>
		</h1>
		<div class="bloglist">
			<c:if test="${doingList != null && fn:length(doingList) > 0}">
				<c:forEach items="${doingList}" var="doing">
					<ul class="arrow_box">
						<div class="sy">
							<c:if test="${doing.fileId != null && doing.fileId !=''}">
								<img src="download.do?fileId=${doing.fileId}">
							</c:if>
							<p>${doing.content}</p>
						</div>
						<span class="dateview">${doing.createTimeStr}</span>
					</ul>
				</c:forEach>
			</c:if>
		</div>
		<form name="search" id="search" action="doingListForPortal.htm"
			method="get">
			<jsp:include page="/blog/common/portalPagination.jsp" />
		</form>
	</div>
	<%@ include file="/blog/common/foot.jsp"%>
</body>
</html>
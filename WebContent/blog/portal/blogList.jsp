<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>nick'blog</title>
<meta name="keywords" content="nick的博客" />
<meta name="description" content="开源 共享" />
<link href="css/learn.css" rel="stylesheet" />
</head>
<body>
	<%@ include file="/blog/common/head.jsp"%>
	<article class="blogs">
		<h1 class="t_nav">
			<span>我们长路漫漫，只因学无止境。 </span><a href="index.htm" class="n1">网站首页</a>
			<c:if test="${blog.classifyId == null }">
				<a href="blogListForPortal.htm" class="n2">学无止境</a>
			</c:if>
			<c:if test="${blog.classifyId != null }">
				<a href="blogListForPortal.htm?classifyId=${blog.classifyId}"
					class="n2">${classifyMap[blog.classifyId]}</a>
			</c:if>
		</h1>
		<div class="newblog left">
			<c:if test="${blogList != null && fn:length(blogList) > 0}">
				<c:forEach items="${blogList}" var="blog">
					<h2>${blog.title}</h2>
					<p class="dateview">
						<span>发布时间：${fn:substring(blog.createTimeStr,0,10)}</span><span>
							作者：Nick.</span><span>分类：[<a
							href="blogListForPortal.htm?classifyId=${blog.classifyId}">${classifyMap[blog.classifyId]}</a>]
						</span>
					</p>
					<figure>
						<img src="download.do?fileId=${blog.fileId}">
					</figure>
					<ul class="nlist">
						<p>${blog.introduce}</p>
						<a href="show.htm?_id=${blog._id}" target="_blank"
							class="readmore">详细信息>></a>
					</ul>
					<div class="line"></div>
				</c:forEach>
			</c:if>
			<div class="blank"></div>
			<form name="search" id="search" action="blogListForPortal.htm"
				method="get">
				<input type="hidden" name="classifyId" value="${blog.classifyId}">
				<jsp:include page="/blog/common/portalPagination.jsp" />
			</form>
		</div>
		<aside class="right">
			<div class="rnav">
				<h2>栏目导航</h2>
				<ul>
					<c:if test="${classifyList != null && fn:length(classifyList) > 0}">
						<c:forEach items="${classifyList}" var="classify" varStatus="status">
							<li><a
								href="blogListForPortal.htm?classifyId=${classify._id}" class="rnav${status.index + 1}">${classify.classifyName}
									(${classify.blogCount})</a></li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<div class="news" id="news"></div>
		</aside>
	</article>
	<%@ include file="/blog/common/foot.jsp"%>
</body>
</body>
<script type="text/javascript">
	$("#news").load("news.htm");
</script>
</html>
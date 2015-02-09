<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>nick'blog</title>
<meta name="keywords" content="nick的博客" />
<meta name="description" content="开源 共享" />
<link href="css/index.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/blog/common/head.jsp"%>
	<div class="banner">
		<section class="box">
			<ul class="texts">
				<p>打了死结的青春，捆死一颗苍白绝望的灵魂。</p>
				<p>为自己掘一个坟墓来葬心，红尘一梦，不再追寻。</p>
				<p>加了锁的青春，不会再因谁而推开心门。</p>
			</ul>
			<div class="avatar">
				<a href="#"><span>杨帆</span></a>
			</div>
		</section>
	</div>
	<div class="template">
		<div class="box">
			<h3>
				<p>
					<span>个人博客</span>文章推荐
				</p>
			</h3>
			<ul>
				<c:if test="${blogList != null && fn:length(blogList) > 0}">
					<c:forEach items="${blogList}" var="blog">
						<li><a href="show.htm?_id=${blog._id}" target="_blank"><img
								src="download.do?fileId=${blog.fileId}"></a><span>${blog.title}</span>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
	<article>
		<h2 class="title_tj">
			<p>
				文章<span>推荐</span>
			</p>
		</h2>
		<div class="bloglist left">

			<c:if test="${blogList != null && fn:length(blogList) > 0}">
				<c:forEach items="${blogList}" var="blog">
					<h3>${blog.title}</h3>
					<figure>
						<img src="download.do?fileId=${blog.fileId}">
					</figure>
					<ul>
						<p>${blog.introduce}</p>
						<!-- ${fn:substring(blog.content,0,10)} -->
						<a href="show.htm?_id=${blog._id}" target="_blank" class="readmore">阅读全文>></a>
					</ul>
					<p class="dateview">
						<span>${fn:substring(blog.createTimeStr,0,10)}</span><span>作者：Nick.</span><span>分类：[<a
							href="blogListForPortal.htm?classifyId=${blog.classifyId}">${classifyMap[blog.classifyId]}</a>]
						</span>
					</p>
				</c:forEach>
			</c:if>
		</div>
		<aside class="right">
			<div class="weather">
				<iframe width="250" scrolling="no" height="60" frameborder="0"
					allowtransparency="true"
					src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1"></iframe>
			</div>
			<div class="news" id="news"></div>
		</aside>
	</article>
	<%@ include file="/blog/common/foot.jsp"%>
</body>
<script type="text/javascript">
	$("#news").load("news.htm");
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>nick'blog</title>
<meta name="keywords" content="nick的博客" />
<meta name="description" content="开源 共享" />
<link href="css/new.css" rel="stylesheet">
<link href="js/third-party/SyntaxHighlighter/shCoreDefault.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="js/third-party/SyntaxHighlighter/shCore.js"></script>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/ueditor.parse.js"></script>
<script type="text/javascript">
	SyntaxHighlighter.all();
</script>
</head>
<body>
	<%@ include file="/blog/common/head.jsp"%>
	<article class="blogs">
		<h1 class="t_nav">
			<span>您当前的位置：<a href="/index.htm">首页</a>&nbsp;&gt;&nbsp; <a
				href="blogListForPortal.htm">学无止境</a>&nbsp;&gt;&nbsp;<a
				href="blogListForPortal.htm?classifyId=${blog.classifyId}">${classifyMap[blog.classifyId]}</a>
			</span><a href="index.htm" class="n1">网站首页</a><a
				href="blogListForPortal.htm?classifyId=${blog.classifyId}"
				class="n2">${classifyMap[blog.classifyId]}</a>
		</h1>
		<div class="index_about">
			<h2 class="c_titile">${blog.title}</h2>
			<p class="box_c">
				<span class="d_time">发布时间：${fn:substring(blog.createTimeStr,0,10)}</span><span>作者：Nick.</span>
				<span>浏览量：${blog.viewCount}</span>
			</p>
			<ul class="infos">${blog.content}
			</ul>
			<div class="keybq">
				<p>
					<span>关键字词</span>：${blog.keywords}
				</p>

			</div>
			<div class="ad"></div>
			<div class="nextinfo">
				<p>
					
					上一篇：
					<c:if test="${nextBlog.title != null}">
						<a href="show.htm?_id=${nextBlog._id}">${nextBlog.title}</a>
					</c:if>
					<c:if test="${nextBlog.title == null}">
						已经是最后一篇了
					</c:if>
				</p>
				<p>
					下一篇：
					<c:if test="${previous.title != null}">
						<a href="show.htm?_id=${previous._id}">${previous.title}</a>
					</c:if>
					<c:if test="${previous.title == null}">
						已经是第一篇了
					</c:if>
				</p>
			</div>
			<div class="otherlink">
				<h2>相关文章</h2>
				<ul>
				<c:if test="${relateBlog != null && fn:length(relateBlog) > 0}">
					<c:forEach items="${relateBlog}" var="relate">
						<c:if test="${relate._id != blog._id}">
							<li><a href="show.htm?_id=${relate._id}" target="_blank">${relate.title}</a>
						</li>
						</c:if>
					</c:forEach>
				</c:if>
				</ul>
			</div>
		</div>
		<%@ include file="/blog/portal/share.jsp"%>
		<aside class="right">
			<div class="news" id="news"></div>
		</aside>
	</article>
	<%@ include file="/blog/common/foot.jsp"%>
</body>
<script type="text/javascript">
	$("#news").load("news.htm");
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/blog/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Nick'Blog</title>
<meta content="" name="keywords">
<meta content="" name="description">
<link href="css/global.css" rel="stylesheet" type="text/css">
<link href="js/third-party/SyntaxHighlighter/shCoreDefault.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="js/third-party/SyntaxHighlighter/shCore.js"></script>
<script type="text/javascript" src="js/ga.js"></script>
<script src="js/jquery-1.11.0.min.js"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="js/bootstrap3/js/bootstrap.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="js/jquery.form.min.js"></script>
<script type="text/javascript">
	SyntaxHighlighter.all();
</script>
<script type="text/javascript">
	function commentAdd() {
		$("#addForm").ajaxSubmit({
			beforeSubmit : function() {
				var periodName = $("#userName").val();
				if (periodName == '') {
					alert("请输入name");
					$("#userName").focus();
					return false;
				}
				var commentContent = $("#commentContent").val();
				if (commentContent == '') {
					alert("请输入content");
					$("#commentContent").focus();
					return false;
				}
				return true;
			},
			success : function(data, statusText) {
				if (data.success) {
					alert(data.message);
					window.location.href = $("#returnUrl").val();
				} else {
					alert(data.message);
				}
			},
			dataType : 'json'
		});
	}
</script>
</head>
<body>
	<div class="index">
		<div class="wrap">
			<div class="header">
				<div class="headerChild"></div>
				<div class="logo">
					<a href="">Nick</a>
				</div>
				<div class="headerSearch">
					<form name="search" action="index.htm" method="get">
						<input type="text" name="keywords" class="text"
							value="${queryBean.keywords}">
						<div class="searchBtn">
							<button type="submit" class="gradual" style="opacity: 0;"></button>
						</div>
						<input type="hidden" name="classifyId"
							value="${queryBean.classifyId}">
				</div>
				<div class="nav">
					<div class="left"></div>
					<ul>
						<li class="normal"><a href="index.htm?pageType=1"><b></b><em>首页</em><span></span></a></li>
						<li class="sel"><a href="index.htm"><b></b><em>博文</em><span></span></a></li>
						<li class="normal"><a href=""><b></b><em>关于我</em><span></span></a></li>
					</ul>
					<div class="right"></div>
				</div>
			</div>
			<div class="content">
				<div class="left">
					<div class="leftTop">
						<div class="aboutMe">
							<h1>Hello, I'm Nick</h1>
							<p>
								(function(){<br> &nbsp;&nbsp;&nbsp;&nbsp;var me =
								{name:Nick, work:java开发, like:JavaScript &amp;&amp; html5};<br>
								&nbsp;&nbsp;&nbsp;&nbsp;me.extend({ motto : "I want do best
								code!" });<br> })();
							</p>
						</div>
					</div>
					<div class="leftTopBot"></div>
					<div class="listWrap">
						<c:if test="${blogList != null && fn:length(blogList) > 0}">
							<c:forEach items="${blogList}" var="blog">
								<input type="hidden" value="index.htm?_id=${blog._id}"
									id="returnUrl">
								<form id="backForm" action="index.htm?_id=${blog._id}"
									method="get"></form>
								<div class="list">
									<div class="articleDate">${fn:substring(blog.createTimeStr,0,10)}</div>
									<div class="articleTitle">
										<a href="index.htm?_id=${blog._id}">${blog.title}</a>
									</div>
									<div class="articleCon">${blog.content}</div>
									<div class="bot">
										<div class="ps">
											类别：${classifyMap[blog.classifyId]}
											&nbsp;&nbsp;&nbsp;&nbsp;浏览（<span>${blog.viewCount}</span>）评论（<span>${blog.commentCount}</span>）
										</div>
									</div>
								</div>
								<div class="comment" id="CommentList">
									<h3>
										<span>共有${fn:length(commentList)}条评论</span><a href="#reply"><img
											src="http://www.rockydo.com/images/leave_comment2.png"></a>
									</h3>
									<ul class="commentList">
										<c:if
											test="${commentList != null && fn:length(commentList) > 0}">
											<c:forEach items="${commentList}" var="comment">
												<li>
													<div class="commentTop"></div>
													<div class="commentCon">
														<div class="info">
															<p class="name">${comment.userName}</p>
															<p class="time">${comment.createTimeStr}</p>
														</div>
														<div class="words">${comment.commentContent}</div>
													</div>
													<div class="commentBot"></div>
												</li>
											</c:forEach>
										</c:if>
									</ul>
									<div class="commentPage"></div>
								</div>
								<div class="comment" id="CommentList"></div>
								<div class="comment">
									<h3>
										<span>发表评论</span><a href="" name="reply"></a>
									</h3>
									<div class="reply">
										<form id="addForm" action="addComment.do" method="post">
											<ul class="info">
												<li><input type="hidden" id="blogId" name="blogId"
													value="${blog._id}" /><input type="text" id="userName"
													name="userName" class="text" /></li>
												<li><input type="text" id="email" class="text" /></li>
												<li><input type="text" id="website" class="text"
													value="http://" /></li>
											</ul>
											<div class="words">
												<textarea class="textarea" id="commentContent"
													name="commentContent"></textarea>
												<div class="submit">
													<input type="button" name="submit" onclick="commentAdd()"
														class="submitBtn" value="" />
												</div>
											</div>
										</form>
									</div>
								</div>
							</c:forEach>
						</c:if>


					</div>
					<div class="leftBot"></div>
				</div>
				</form>
				<div class="right">
					<div class="rightCon">
						<div class="normal">
							<div class="twist"></div>
							<div class="title">文章分类</div>
							<div class="con">
								<ul>
									<c:if
										test="${classifyList != null && fn:length(classifyList) > 0}">
										<c:forEach items="${classifyList}" var="classify">
											<li>·<a href="index.htm?classifyId=${classify._id}">${classify.classifyName}
													(${classify.blogCount})</a></li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="bot"></div>
					</div>
				</div>
			</div>
			﻿
		</div>
</body>
</html>
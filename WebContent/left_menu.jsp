<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>nick'blog</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="description" content="菜单" />
    <link href="js/dtree/dtree.css" type="text/css" rel="StyleSheet" />
    <script src="js/dtree/dtree.js"	type="text/javascript"></script>
</head>
<style type="text/css">
<!--
.leftMenu {
	border: 1px solid #a5d3e7;
	padding-top: 5px;
	padding-right: 5px;
	padding-bottom: 10px;
	padding-left: 10px;
	visibility: visible;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	background-image: url(images/fsdgfj.gif);
	background-repeat: no-repeat;

}
-->
</style>
<body style="margin:0px;">
<div class="leftMenu">
	<script type="text/javascript">
	
	var imgPath = "js/dtree/";
	d = new dTree('d');
	d.add('0','-1','nick\'blog','','教育合作伙伴服务平台');
	
	d.add('11','0','blog管理','','blog管理','main');
	d.add('111','11','博客分类','classifList.htm','博客分类','main');
	d.add('112','11','博客内容','blogList.htm','博客内容','main');
	d.add('113','11','博客回复','commentList.htm','博客回复','main');
	d.add('114','11','碎言碎语','doingList.htm','碎言碎语','main');
	d.add('115','11','图库管理','photoList.htm','碎言碎语','main');
	document.write(d);
	</script>
</div>
</body>
</html>

<%@ page language="java" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>nick'blog</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="description" content="主页" />
		<script type="text/javascript">
			if(parent && parent.frames.length>0){
				top.location.href = "index.jsp";
			}
		</script>
	</head>
		<frameset cols="200,*" frameborder="no" border="0" framespacing="0" id="frame2">
			<frame id="leftmenu" name="leftmenu"  src="left_menu.jsp"  scrolling="auto" noresize frameborder="0" />
			<frame id="main" name="main" src="addBlog.htm" scrolling="auto" frameborder="0" />
		</frameset>
</html>

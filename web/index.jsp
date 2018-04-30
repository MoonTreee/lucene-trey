<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>index</title>
<body>
<td align="center"><a href="${pageContext.request.contextPath}/lucene_search.action">添加</a></td>
<form action="lucene_search.action" method="post" target="top">
	<div class="loginBox">
		<ul class="login">
			<li class="l_tit">检索关键词</li>
			<li class="mb_10"><input type="text" name="keyword" class="login_input user_icon"></li>
			<li><input type="submit" value="检索" class="login_btn"></li>
		</ul>
	</div>
</form>
</body>
</html>

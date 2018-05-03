<%--
  Created by IntelliJ IDEA.
  User: Trey
  Date: 2018/5/3
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns= "http://www.w3.org/1999/xhtml" >
<head>
    <meta http-equiv= "Content-Type" content ="text/html; charset=gb2312" />
    <title>list</title>
    <style type= "text/css">
        /*id为customers 的标签的样式*/
        #customers
        {
            font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
            width:100%;
            border-collapse:collapse;
        }
        /*id为customers 的标签里面的td标签和th标签的样式*/
        #customers td, #customers th
        {
            font-size:1em;
            border:1px solid #98BF21;
            padding:3px 7px 2px 7px;
        }
        /*id为customers 的标签里面的th标签的样式*/
        #customers th
        {
            font-size:1.1em;
            text-align:left;
            padding-top:5px;
            padding-bottom:4px;
            background-color:#A7C942;
            color:#FFF;
        }
        /*id为customers的标签里面的class名为alt的tr标签里面的td标签的样式*/
        #customers tr.alt td
        {
            color:#000;
            background-color:#EAF2D3;
        }

    </style>
</head>

<body>
<table id= "customers">
    <tr>
        <th>ID</th>
        <th>标题</th>
        <th>关键词</th>
        <th>单位</th>
        <th>资助金额</th>
        <th>年份</th>

    </tr>
    <c:forEach items="${luceneList}" var="m">
        <tr>
            <td>${m.id}</td>
            <td>${m.title}</td>
            <td>${m.keyWord}</td>
            <td>${m.organization}</td>
            <td>${m.funds}</td>
            <td>${m.year}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
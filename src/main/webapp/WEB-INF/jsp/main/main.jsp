<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/21
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common.jsp"%>
<html>
<head>
    <title>主页面</title>
</head>
<body>
    <div id="header">
        <tiles:insertAttribute name="header"/>
    </div>
    <div id="content">
        <tiles:insertAttribute name="body"/>
    </div>
    <div id="footer">
        <tiles:insertAttribute name="footer"/>
    </div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/21
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>主页面</title>
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
</head>
<body>
   <h1>Main Page</h1>
<a href="${basePath}/registry">用户注册</a><br/>
<a href="${basePath}/param?name=zhangsan">通过@RequestParam传递参数</a>
<a href="${basePath}/param/zhangsan">通过@PathVariable传递参数</a>
<a onclick="jsonParam()" href="#">通过Json传递参数</a><br/>
<a href="${basePath}/user/export" >导出用户数据</a>
</body>
<script type="text/javascript">
    function jsonParam(){
        var user={username:'zs',password:'123456'};
        var url="${basePath}/param/json";
       $.post(url,user,function(result){
           alert(result);
       });
    }
</script>
</html>

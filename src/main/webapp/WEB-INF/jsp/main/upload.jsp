<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/31
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>上传文件</title>
</head>
<body>
    <form action="${basePath}/upload" method="post" enctype="multipart/form-data">
        <h4>上传文件</h4>
        <table id="uploadTable">
            <tr>
                <td> <input type="file" name="files" value="请选择文件" size="200">&nbsp;</td>
            </tr>
        </table>
       <button onclick="addLine()" type="button">点击添加更多附件</button><hr>
        <input type="submit" value="提交">
    </form>
</body>
<script type="text/javascript">
    function addLine(){
        var newLine=document.getElementById("uploadTable").insertRow();
        newLine.insertCell().innerHTML="<input type='file' name='files' value='请选择文件' size='200'>&nbsp;"+
        "<button onclick='javascript:removeLine(this)' type='button'>移除</button>";
    }
    function removeLine(obj){
        var objSourceRow=obj.parentNode.parentNode;
        //obj.parentNode是<td>、obj.parentNode.parentNode是<tr>、
        // obj.parentNode.parentNode.parentNode是<tbody>、obj.parentNode.parentNode.parentNode.parentNode是<table>
        var objTable=obj.parentNode.parentNode.parentNode.parentNode;
        objTable.lastChild.removeChild(objSourceRow);
    }
</script>
</html>

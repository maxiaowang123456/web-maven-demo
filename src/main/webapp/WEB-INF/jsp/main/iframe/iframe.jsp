<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/26
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/iframe/iframe.css">
</head>
<body>
  <div id="header">
      <h1>IfamePage</h1>
      <div id="ad">
          <iframe width="728px" height="90px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" src=""
          style="left:0px;top:0px;position:absolute;" id="aswift_0" name="aswift_0" src="advert.jsp"></iframe>
      </div>
  </div>
  <form action="${basePath}/iframe/submit" method="post" name="tryitform" id="tryitform" onsubmit="validateForm()" target="i">
      <div id="butt">
          <input type="button" value="提交代码" onclick="submitTryit()"/>
      </div>
      <div id="codeArea">
          <h2>编辑您的代码</h2>
          <textarea id="testCode" wrap="soft">
<html>
<body>
<h1>My First Heading</h1>

<p>My first paragraph.</p>
</body>
</html>
          </textarea>
      </div>
      <input type="hidden" name="code" id="code"/>
  </form>
  <div id="result">
      <h2>查看结果</h2>
      <iframe name="i" frameborder="0" src="${basePath}/iframe/loadtext"></iframe>
  </div>
  <div id="footer">
      <p>请在上面的文本框中编辑您的代码，然后单击提交按钮测试结果。
        <a href="http://http://www.w3school.com.cn/index.html" title="W3School.com">w3school.com.cn</a>
      </p>
  </div>
</body>
<script type="text/javascript">
    function validateForm(){
        var code=document.getElementById("code").value;
        if(code.length>5000){
            document.getElementById("code").value="<h1>Error</h1>"
        }
    }
    function submitTryit(){
      var t=document.getElementById("testCode").value;
      t=t.replace(/script/gi,"demoscpt");
      document.getElementById("code").value=t;
        validateForm();
        document.getElementById("tryitform").submit();
    }

</script>
</html>

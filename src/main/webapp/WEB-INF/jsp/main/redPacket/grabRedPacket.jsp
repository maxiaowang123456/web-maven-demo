<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/5
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>抢红包</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            console.log("begin grab redpacket");
            var max=150;
            for(var i=1;i<=max;i++){
                $.post("${basePath}/userRedPacket/grabRedPacketByRedis?redPacketId=1&userId="+i,
                    function(result){
                        console.log(result);
                });
            }
        });
    </script>
</head>
<body>
</body>
</html>

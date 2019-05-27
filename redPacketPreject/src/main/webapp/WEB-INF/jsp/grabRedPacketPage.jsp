<%--
  Created by IntelliJ IDEA.
  User: shnstt
  Date: 2019/4/21
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>抢红包</title>
    <script type="text/javascript" src="<%=basePath%>script/jquery-3.4.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var max = 30000;
            for (var i=1;i<=max;i++){
                $.post({
                    url:"http://localhost:8080/shns/userRedPacket/grabRedPacket.do?redPacketId=1&userId="+i
                    // ,success:function (result) {
                    //     if (!result.success()) {
                    //         console.log(result.message);
                    //     }
                    // }
                });
            }
        });
    </script>
</head>

<body>
    <h1>抢红包</h1>
</body>
</html>

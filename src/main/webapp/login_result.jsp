<%@ page import="javax.swing.*" %><%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2024/4/7
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>

    <%
        String mess=(String)request.getAttribute("message");
        if(mess=="错误")
        {
    %>
    <script type="text/javascript" >
        alert("密码或用户名<%=mess%>,请重新输入！");
        window.document.location.href="Login.html";
    </script>
    <%
        }
    %>

    <%
        if(mess=="登陆成功"){
    %>
    <script type="text/javascript">
        alert("<%=mess%>");
        window.document.location.href="ContactList.jsp";
    </script>
    <%
        }
    %>
    <%
    if(mess=="空"){
    %>
    <script type="text/javascript">
        alert("用户名或密码不为空，请输入！");
        window.document.location.href="Login.html";
    </script>
    <%
        }
    %>
</div>
<script type="text/javascript">


</script>
</body>
</html>

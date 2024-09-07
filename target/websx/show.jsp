<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2024/6/11
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--show.jsp-->
<html>
<head>
    <title>显示用户查询</title>
    <style>
        table{
            margin: 100px auto;
        }
        caption{
            font-size: 25px;
            font-weight: bold;
        }
        th, td{
            padding: 5px 10px;
            font-size: 20px;
            border: 1px solid #000;
        }
    </style>
</head>
<body>
<table cellpadding="0px" cellspacing="0px">
    <caption>第<%=request.getAttribute("num")%>页数据</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Date</th>
    </tr>
    </thead>
    <% for (int i = 1; i <= 3; i++) {
    %>
    <tr>
        <td><%=request.getAttribute("id" + i)%></td>
        <td><%=request.getAttribute("name" + i)%></td>
        <td><%=request.getAttribute("pass" + i)%></td>
        <td><%=request.getAttribute("date" + i)%></td>
    </tr>
    <% } %>
</table>
</body>
</html>

<%@ page import="edu.ncst.websx.entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page   contentType="text/html;charset=utf-8"   %>

<%@ page   pageEncoding="utf-8"%>

<%request.setCharacterEncoding("utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加联系人事项</title>
    <link rel="stylesheet" href="css/AddMatter.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div class="container">
    <div class="title">添加联系人事项</div>

    <form action="addmatter"  method="post">
        <div class="form-group">
            <label for="contact">姓名：</label>
            <input type="text" id="contact" name="ct_name">
        </div>
        <div class="form-group">
            <label for="birthday">日期：</label>
            <input type="date" id="birthday" name="matter_time">
        </div>
        <div class="form-group">
            <label for="mat">事项：</label>
            <input type="text" id="mat" name="matter">
        </div>
        <button type="submit" name="submit">添加</button>

    </form>
</div>
</body>

</html>

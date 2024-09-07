<%@ page import="edu.ncst.websx.entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.ncst.websx.servlet.DBUtil" %>
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
    <title>详情</title>
    <link rel="stylesheet" href="css/DetailCt.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div class="container clearfix">
    <div class="title">详细信息</div>
    <div class="img">
        <img id="showImg" src="">
    </div>
    <form  method="post"  >
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="ct_name">
        </div>
        <div class="form-group">
            <label for="address">地址：</label>
            <input type="text" id="address" name="ct_ad" >
        </div>
        <div class="form-group">
            <label for="email">邮箱：</label>
            <input type="text" id="email" name="ct_em">
        </div>
        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" id="qq" name="ct_qq" >
        </div>
        <div class="form-group">
            <label for="wechat">微信：</label>
            <input type="text" id="wechat" name="ct_wx" >
        </div>
        <div class="form-group">
            <label for="postal_code">邮编：</label>
            <input type="text" id="postal_code" name="ct_yb">
        </div>
        <div class="form-group">
            <label for="gender">性别：</label>
            <select id="gender" >
                <option selected="selected" name="ct_mf">女</option>
                <option  name="ct_mf">男</option>
            </select>
        </div>
        <div class="form-group">
            <label for="birthday">生日：</label>
            <input type="text" id="birthday" name="ct_birth" >
        </div>
        <div class="form-group">
            <label for="tel">电话：</label>
            <input type="text" id="tel" name="ct_phone" >
        </div>
        <div class="btn left">
            <form>
            <button id="update" >更新</button>
            </form>
        </div>
        <input type="hidden" id="id" name="id" >
    </form>
    <form action="getcm" method="post">
        <div class="btn right">
            <button id="matterr">事项</button>
        </div>
    </form>
    <%
    // 假设ct_id已经被设置为request属性
        String ct_id = request.getParameter("ct_id");
        Person person = DBUtil.getOneContact(ct_id, 0);
        String fileName=DBUtil.getContactPic(ct_id);
        if (person != null) {
            // 处理ct_id
            // 例如，使用ct_id查询数据库获取联系人详细信息
            String name = person.getCt_name();
            String address = person.getCt_ad();
            String email = person.getCt_em();
            String qq = person.getCt_qq();
            String wechat = person.getCt_wx();
            String postal_code = person.getCt_yb();
            String gender = person.getCt_mf();
            String birthday = person.getCt_birth();
            String tel = person.getCt_phone();

    %>
    <script type="text/javascript">
        document.getElementById("name").value = '<%= name %>';
        document.getElementById("address").value = '<%= address %>';
        document.getElementById("email").value = '<%= email %>';
        document.getElementById("qq").value = '<%= qq %>';
        document.getElementById("wechat").value = '<%= wechat %>';
        document.getElementById("postal_code").value = '<%= postal_code %>';
        document.getElementById("gender").value = '<%= gender %>';
        document.getElementById("birthday").value = '<%= birthday %>';
        document.getElementById("tel").value = '<%= tel %>';
        document.getElementById("id").value = '<%= ct_id %>'; // 添加这一行
        $(document).ready(function() {
            $("#update").click(function() {
                var ct_id =  $("#id").val();
                var ct_name = $("#name").val();
                var ct_ad = $("#address").val();
                var ct_yb = $("#postal_code").val();
                var ct_qq = $("#qq").val();
                var ct_wx = $("#wechat").val();
                var ct_em = $("#email").val();
                var ct_mf = $("#gender").val();
                var ct_birth = $("#birthday").val();
                var ct_phone = $("#tel").val();
                // 调用服务器端的方法
                $.ajax({
                    url: 'change', // Servlet的URL
                    type: 'post', // 请求类型
                    data: {
                        ct_id: ct_id,
                        ct_name: ct_name,
                        ct_ad: ct_ad,
                        ct_yb: ct_yb,
                        ct_qq: ct_qq,
                        ct_wx: ct_wx,
                        ct_em: ct_em,
                        ct_mf: ct_mf,
                        ct_birth: ct_birth,
                        ct_phone: ct_phone
                    },
                    success: function() {
                        // 更新成功的处理逻辑
                        alert("更新成功");
                        window.location.href="ContactList.jsp";
                    },
                    error: function(xhr, status, error) {
                        // 更新失败的处理逻辑
                        alert("更新失败：" + xhr.status + " - " + xhr.statusText);
                    }
                });

            });
            var FileName = '<%= fileName %>'; // 这也是一个示例文件名
            // 动态设置图片元素的src属性
            $("#showImg").attr("src", "imgs/" + FileName);

        });
    </script>
   <%
       }
    else{
   %>

    <%
        }
    %>
</div>

</body>

</html>


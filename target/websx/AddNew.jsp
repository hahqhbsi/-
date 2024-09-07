<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=utf-8"   %>

<%@ page   pageEncoding="utf-8"%>

<%request.setCharacterEncoding("utf-8");%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加联系人</title>
    <link rel="stylesheet" href="css/AddNew.css">
</head>
<body>
<div class="container">
    <div class="title">联系人列表</div>

    <form action="test" method="post" enctype="multipart/form-data">
        头像：<input type="file" name="head">
        <button>提交数据</button>
    </form>
    <form action="addnew"  method="post" accept-charset="UTF-8" enctype="multipart/form-data">

        <img id="showImg" src="" >
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="ct_name">
        </div>
        <div class="form-group">
            <label for="address">地址：</label>
            <input type="text" id="address" name="ct_ad">
        </div>
        <div class="form-group">
            <label for="email">邮箱：</label>
            <input type="text" id="email" name="ct_em">
        </div>
        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" id="qq" name="ct_qq">
        </div>
        <div class="form-group">
            <label for="wechat">微信：</label>
            <input type="text" id="wechat" name="ct_wx">
        </div>
        <div class="form-group">
            <label for="postal_code">邮编：</label>
            <input type="text" id="postal_code" name="ct_yb" oninput="validatePostalCode()">
            <span id="postal_code_warning" style="color: red; display: none;">邮编是6位数字</span>
        </div>
        <div class="form-group">
            <label for="gender">性别：</label>
            <select id="gender" >
                <option selected="selected" name="ct_mf" value="0">女</option>
                <option value="1" name="ct_mf">男</option>
            </select>
        </div>
        <div class="form-group">
            <label for="birthday">生日：</label>
            <input type="date" id="birthday" name="ct_birth">
        </div>
        <div class="form-group">
            <label for="tel">电话：</label>
            <input type="text" id="tel" name="ct_phone" oninput="validatePhone()">
            <span id="tel_warning" style="color: red; display: none;">电话是11位数</span>
        </div>
        <button type="submit" name="submit">添加</button>
    </form>
</div>



</body>
<script type="text/javascript">
    function validatePostalCode() {//检查邮编格式
        var postalCodeInput = document.getElementById('postal_code');//获得组件中的数
        var warningSpan = document.getElementById('postal_code_warning');//获得隐藏组件的文字
        if (postalCodeInput.value.length != 6) {
            warningSpan.style.display = 'inline';//改变样式
        } else {
            warningSpan.style.display = 'none';
        }
    }
    function validatePhone() {//检查电话格式
        var phoneInput = document.getElementById('tel');
        var warningSpan = document.getElementById('tel_warning');
        if (postalCodeInput.value.length != 11) {
            warningSpan.style.display = 'inline';
        } else {
            warningSpan.style.display = 'none';
        }
    }
</script>
</html>

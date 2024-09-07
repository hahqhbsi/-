<%@ page import="edu.ncst.websx.entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page   contentType="text/html;charset=utf-8"   %>

<%@ page   pageEncoding="utf-8"%>

<%request.setCharacterEncoding("utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>黑名单列表</title>
    <link rel="stylesheet" href="css/BlackList.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div class="list clearfix">
    <div class="title">黑名单列表</div>
    <div class="intro left">
        <div class="city left">
            <img src="imgs/1.jpg" class="left">
            <span class="personCity left">北京
            <p><a class="changeCity">[更换城市]</a></p>
            </span>

        </div>
        <div class="weatherSet right ">
            <div class="weather right">
                <p class="iconfont icon-qingtian"></p>
                <p>今天6月15日</p>
                <p>晴</p>
                <p>24℃~30℃</p>
            </div>
            <div class="weather right">
                <p class="iconfont icon-duoyun"></p>
                <p>明天6月16日</p>
                <p>晴转多云</p>
                <p>25℃~31℃</p>
            </div>
            <div class="weather right">
                <p class="iconfont icon-duoyun"></p>
                <p>后天6月17日</p>
                <p>多云转晴</p>
                <p>28℃~40℃</p>
            </div>
        </div>
    </div>

    <!--以上是头像加天气-->
    <div class="bottom">
        <div class="operation">
            <input type="text" class="search" placeholder="搜索">
            <button class="sousuo iconfont icon-sousuo"></button>

            <form  method="post" action="blacklist" >
                <select name="status" class="status">
                    <option value="0">全部</option>
                    <option value="1">女</option>
                    <option value="2">男</option>
                </select>
                <button>刷新</button>
            </form>
        </div>
        <table>
            <tr>
                <th>姓名 <button class="iconfont icon-paixupaihang order"></button></th>
                <th>性别</th>
                <th>电话</th>
                <th colspan="2">操作</th>
            </tr>

            <c:forEach var="p" items="${persons}" varStatus="status">
                <tr>
                    <td>${p.ct_name}</td>
                    <td>${p.ct_mf}</td>
                    <td>${p.ct_phone}</td>
                    <td class="opera"><button data-ct_id="${p.ct_id}">详情</button></td>
                    <td class="opera2"><button data-ct_id="${p.ct_id}">还原</button></td>
                </tr>
            </c:forEach>
        </table>
        <%-- 在JSP页面中 --%>
        <%--  <c:out value="${empty persons}"></c:out> <!-- 如果为true，则表示persons列表为空 -->--%>

        <div class="map">
            <select name="radio" class="radio radiomap">
                <option>第1页</option>
                <option value="1">计算机</option>
                <option value="2">人工智能</option>
            </select>   
            <button class="next">
                下一页>>
            </button>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" >
    <%--增加--%>

    $(document).ready(function() {
        //还原
        $('.opera2 button[data-ct_id]').click(function() {
            var ct_id = $(this).data('ct_id');
            $.ajax({
                url: 'cancledelete', // 服务器端处理请求的URL
                data: { ct_id: ct_id }, // 发送的数据
                type: 'post', // 请求类型
                success: function(response) {
                    // 请求成功后的处理逻辑
                    console.log('Success:', response);
                },
                error: function(xhr, status, error) {
                    // 请求失败后的处理逻辑
                    console.error('Error:', error);
                }
            });
        });
        /*详情*/
        $('.opera button[data-ct_id]').click(function() {
            var ct_id = $(this).data('ct_id');
            // 创建一个隐藏的表单
            var form = document.createElement('form');
            form.setAttribute('method', 'post');
            form.setAttribute('action', 'detail2.jsp');
            // 创建一个隐藏的输入字段，用于存储ct_id值
            var input = document.createElement('input');
            input.setAttribute('type', 'hidden');
            input.setAttribute('name', 'ct_id');
            input.setAttribute('value', ct_id);
            // 将输入字段添加到表单中
            form.appendChild(input);
            // 将表单添加到DOM中，这样就可以提交了
            document.body.appendChild(form);
            // 提交表单
            form.submit();
            out.print(ct_id);
        });


        $('.sousuo').click(function() {
            var searchTerm = $('.search').val(); // 获取搜索框中的文本
            if (searchTerm.trim() !== '') {
                submitSearchForm(searchTerm); // 提交搜索表单
            } else {
                alert("请输入姓名");
            }
        });
        $('.order').click(function() {
            sortTableByFirstName();
        });
        // 提交表单时，确保隐藏字段的值是最新的
        $('.shuxin').click(function() {
            $('[name="selectedStatus"]').val($('.status').val());
        });
    });
    // 提交搜索表单的函数
    function submitSearchForm(searchTerm) {
        // 使用encodeURIComponent处理中文搜索词
        var encodedSearchTerm = encodeURIComponent(searchTerm);
        // 创建一个隐藏的表单
        var form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', 'searchblack');
        // 创建一个隐藏的输入字段，用于存储ct_name值
        var input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', 'ct_name');
        input.setAttribute('value', encodedSearchTerm);
        // 将输入字段添加到表单中
        form.appendChild(input);
        // 将表单添加到DOM中，这样就可以提交了
        document.body.appendChild(form);
        // 提交表单
        form.submit();
    }
    function sortTableByFirstName() {//排序
        var table, rows, sortedRows, i, x, y;
        table = document.querySelector("table");
        rows = table.rows;
        sortedRows = Array.from(rows).slice(1);

        sortedRows.sort(function(a, b) {
            var aText = a.cells[0].textContent || a.cells[0].innerText;
            var bText = b.cells[0].textContent || b.cells[0].innerText;
            return aText.localeCompare(bText);
        });

        for (i = rows.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }

        for (i = 0; i < sortedRows.length; i++) {
            table.appendChild(sortedRows[i]);
        }
    }

    document.querySelector('.order').addEventListener('click', sortTableByFirstName);



</script>

</html>

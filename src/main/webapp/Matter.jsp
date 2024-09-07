<%@ page import="edu.ncst.websx.entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page   contentType="text/html;charset=utf-8"   %>

<%@ page   pageEncoding="utf-8"%>

<%request.setCharacterEncoding("utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>联系人事项提醒</title>
    <link rel="stylesheet" href="css/Matter.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div class="list clearfix">
    <div class="title">联系人事项列表</div>
    <div class="intro left">
    </div>
    <!--以上是头像加天气-->
    <div class="bottom">
        <div class="operation">
            <input type="text" class="search" placeholder="搜索">
            <button class="sousuo iconfont icon-sousuo"></button>

            <form method="post" action="getmatter" class="search2">
                <input type="hidden" name="selectedStatus" value=""> <!-- 隐藏的表单字段 -->
                <button class="shuxin">刷新</button>
                <select name="status" class="status">
                    <option value="0">待完成</option>
                    <option value="1">取消</option>
                    <option value="2">已完成</option>
                </select>
            </form>
            <button class="common iconfont icon-tianjiayonghu" id="btn_add" ></button>
        </div>
        <table>
            <tr>
                <th>姓名</th>
                <th>时间 <button class="iconfont icon-paixupaihang order"></button></th>
                <th>事件</th>
                <th colspan="2">操作</th>
            </tr>

            <c:forEach var="p" items="${matters}" varStatus="status">
                <tr>
                    <td>${p.ct_name}</td>
                    <td>${p.matter_time}</td>
                    <td>${p.matter}</td>
                    <td class="opera"><button data-matter_id="${p.matter_id}">取消</button></td>
                    <td class="opera2"><button data-matter_id="${p.matter_id}">完成</button></td>
                </tr>
            </c:forEach>
        </table>
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

</html>
<script type="text/javascript" >
    $(document).ready(function() {
        $('.status').change(function() {
            $('[name="selectedStatus"]').val($(this).val());
        });
        $("#btn_add").click(function() {//添加事件
            window.location.href = "AddMatter.jsp";
        });
        // 提交表单时，确保隐藏字段的值是最新的
        $('.shuxin').click(function() {
                $('[name="selectedStatus"]').val($('.status').val());
                $.ajax({
                    url: 'getmatter', // Servlet的URL
                    type: 'post', // 请求类型
                    data: { ct_id: ct_id }, // 要发送的数据
                    success: function(response) {
                        alert(ct_id);
                        if (response.redirect) {
                            window.location.href = response.redirectUrl;
                        }
                    },
                    error: function(xhr, status, error) {
                        alert("请求失败：" + xhr.status + " - " + xhr.statusText);
                    }
                });

        });
        //完成事件
        $('.opera2 button[data-matter_id]').click(function() {
            var matter_id = $(this).data('matter_id');
            $.ajax({
                url: 'finishmatter', // 服务器端处理请求的URL
                data: { matter_id: matter_id }, // 发送的数据
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
        $('.opera button[data-matter_id]').click(function() {//取消事件
            var matter_id= $(this).data('matter_id');
            $.ajax({
                url: 'canclematter', // 服务器端处理请求的URL
                data: { matter_id: matter_id }, // 发送的数据
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
    });
    // 提交搜索表单的函数
    function submitSearchForm(searchTerm) {
        var encodedSearchTerm = encodeURIComponent(searchTerm);
        // 创建一个隐藏的表单
        var form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', 'searchmatter');
        // 创建一个隐藏的输入字段，用于存储ct_name值
        var input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', 'matter');
        input.setAttribute('value', encodedSearchTerm);
        // 将输入字段添加到表单中
        form.appendChild(input);
        // 将表单添加到DOM中，这样就可以提交了
        document.body.appendChild(form);
        // 提交表单
        form.submit();
    }
    function sortTableByTime() {
        var table, rows, sortedRows, i;
        table = document.querySelector("table");
        rows = table.rows;
        sortedRows = Array.from(rows).slice(1); // 假设第一行是表头，不包括在排序中
        // 使用Date.parse将时间字符串转换为时间戳进行比较
        sortedRows.sort(function(a, b) {
            var aText = a.cells[1].textContent || a.cells[1].innerText; // 假设时间在第二个单元格
            var bText = b.cells[1].textContent || b.cells[1].innerText;
            var aTime = Date.parse(aText);
            var bTime = Date.parse(bText);
            // 如果时间无法解析，可能会返回NaN，需要处理这种情况
            if (isNaN(aTime)) {
                console.warn('无法解析时间：', aText);
                return 1; // 根据需要调整排序
            }
            if (isNaN(bTime)) {
                console.warn('无法解析时间：', bText);
                return -1; // 根据需要调整排序
            }
            return aTime - bTime; // 升序排序
        });
        // 删除原有行
        for (i = rows.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
        // 添加排序后的行
        for (i = 0; i < sortedRows.length; i++) {
            table.appendChild(sortedRows[i]);
        }
    }
    // 添加事件监听器
    document.querySelector('.order').addEventListener('click', sortTableByTime);

</script>

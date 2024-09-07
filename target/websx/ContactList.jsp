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
    <link rel="stylesheet" href="css/ContactList.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>

<div class="list clearfix">
    <div class="title">联系人列表</div>
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
    <div class="bottom clearfix">
        <div class="operation">
            <div class="ss">
                <input type="text" class="search" placeholder="搜索">
                <button class="sousuo iconfont icon-sousuo"></button>
                <form  method="post" action="getContact" class="new">
                    <select name="status" class="status">
                        <option value="0">全部</option>
                        <option value="1">女</option>
                        <option value="2">男</option>
                    </select>
                    <button>刷新</button>
                </form>
            </div>
            <div class="ico">
                <button class="common iconfont icon-tianjiayonghu" id="btn_add" ></button>
                <button class="common iconfont icon-deleteuser" id="btn_delete" ></button>
                <button class="common iconfont icon-kehuguanli" id="btn_control"></button>
            </div>
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
                    <td class="opera"><button id="more" data-ct_id="${p.ct_id}" >详情</button></td>
                    <td class="opera2"><button id="delete" data-ct_id="${p.ct_id}">屏蔽</button></td>
                </tr>

            </c:forEach>
        </table>
        <!-- 分页导航 -->
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="yourServlet?page=${currentPage - 1}">上一页</a>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" var="page">
                <c:if test="${page eq currentPage}">
                    <span>${page}</span>
                </c:if>
                <c:if test="${page ne currentPage}">
                    <a href="yourServlet?page=${page}">${page}</a>
                </c:if>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <a href="yourServlet?page=${currentPage + 1}">下一页</a>
            </c:if>
        </div>
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
<%
    String mess=(String)request.getAttribute("message");
    if(mess=="成功")
    {
%>
<script type="text/javascript" >
    alert("添加成功！");
</script>
<%
    }
%>

<!-- 隐藏的form表单，用于在跳转时传递ct_id -->

</body>

<script type="text/javascript" >

        $('.status').change(function() {
            $('[name="selectedStatus"]').val($(this).val());
        });

        // 提交表单时，确保隐藏字段的值是最新的
        $('.shuxin').click(function() {
            $('[name="selectedStatus"]').val($('.status').val());
        });

        $("#btn_add").click(function() {
            window.location.href = "AddNew.jsp";
        });
        //看黑名单....
        $("#btn_delete").click(function() {
            window.location.href = "BlackList.jsp";
        });
        $("#btn_control").click(function() {
            window.location.href = "Matter.jsp";
        });
        //拉黑
        $('.opera2 button[data-ct_id]').click(function() {
            var ct_id = $(this).data('ct_id');
            $.ajax({
                url: 'getdelete', // 服务器端处理请求的URL
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
            form.setAttribute('action', 'DetailCt.jsp');

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
// 提交搜索表单的函数
function submitSearchForm(searchTerm) {
    var encodedSearchTerm = encodeURIComponent(searchTerm);
    // 创建一个隐藏的表单
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', 'searchcontact');
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
    table = document.querySelector("table");// 选择页面中的表格元素
    rows = table.rows;
    sortedRows = Array.from(rows).slice(1);// 将行转换为数组，并去除第一行（标题）

    sortedRows.sort(function(a, b) {
        var aText = a.cells[0].textContent || a.cells[0].innerText;// 获取第一列的文本内容
        var bText = b.cells[0].textContent || b.cells[0].innerText;
        return aText.localeCompare(bText); // 使用本地字符串比较来比较两个字符串
    });
    for (i = rows.length - 1; i > 0; i--) {// 从后往前遍历表格的行
        table.deleteRow(i);// 删除每一行，除了标题行
    }
    for (i = 0; i < sortedRows.length; i++) {// 遍历排序后的行数组
        table.appendChild(sortedRows[i]); // 将行添加回表格中，此时已是排序过的
    }
}
        // 当点击类名为'order'的元素时，执行 sortTableByFirstName 函数
document.querySelector('.order').addEventListener('click', sortTableByFirstName);


</script>


</html>
<%--
  Created by IntelliJ IDEA.
  User: lizhenyuan
  Date: 2021/10/11
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页操作--%>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1" >首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
        <a href="#">${requestScope.page.pageNo - 1}</a>
    </c:if>
    【${requestScope.page.pageNo}】
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="#">${requestScope.page.pageNo + 1}</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>

        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="serachPage">
</div>
<script type="text/javascript">
    $(function () {
        $("#serachPage").click(function () {
            var pageNo = $("#pn_input").val();
            // javaScript语言中提供了一个location地址栏对象
            // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
            // href属性可读，可写
            location.href = ("${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo);
        })
    })
</script>
<%--分页操作--%>

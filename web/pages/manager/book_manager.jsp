<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			// 给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				// 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
				/**
				 * confirm是确认提示框函数
				 * 参数是它的提示内容
				 * 它有两个按钮，一个确认，一个是取消。
				 * 返回true表示点击了，确认，返回false表示点击取消。
				 */
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
				// return false// 阻止元素的默认行为===不提交请求
			});
		});
	</script>
</head>
<body>

	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
	<%@include file="/pages/common/manager.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${param.pageNo}">修改</a></td>
				<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${param.pageNo}">删除</a></td>
			</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?action=add&pageTotal=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
		<br/>
		<%--分页操作--%>
<%--		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="manager/bookServlet?action=pageQuery&pageNo=1" >首页</a>
				<a href="manager/bookServlet?action=pageQuery&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
				<a href="#">${requestScope.page.pageNo - 1}</a>
			</c:if>
			【${requestScope.page.pageNo}】
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="#">${requestScope.page.pageNo + 1}</a>
				<a href="manager/bookServlet?action=pageQuery&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
				<a href="manager/bookServlet?action=pageQuery&pageNo=${requestScope.page.pageTotal}">末页</a>
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
					location.href = ("${pageScope.basePath}manager/bookServlet?action=pageQuery&pageNo=" + pageNo);
				})
			})
		</script>--%>
		<%@include file="/pages/common/page_query.jsp"%>
		<%--分页操作--%>
	</div>
	
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<%@include file="/pages/common/manager.jsp" %>
		
		<div id="main">
			<form action="manager/bookServlet" method="get">
				<input type="hidden" name="action" value="${param.action}">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<input type="hidden" name="pageTotal" value="${param.pageTotal}">

				<input type="hidden" name="id" value="${param.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>
					<tr>
						<td><input name="name" type="text"   value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text"  value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text"  value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text"  value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>
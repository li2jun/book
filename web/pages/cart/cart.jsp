<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_ment.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.count}</td>
					<td>${item.price}</td>
					<td>${item.totalPrice}</td>
					<td><a href="cartServlet?action=deleteItem&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>

			
		</table>
		
		<div class="cart_info">
			<span class="cart_span">一共<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总计<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a href="cartServlet?action=empty">清空购物车</a></span>
			<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
		</div>
	
	</div>
	
 <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
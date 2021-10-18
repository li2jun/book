<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@include file="/pages/common/head.jsp"%>
</head>
<script type="text/javascript">
	$(function () {
		$("button.addToCart").click(function () {
			//获取属性值
			var bookid = $(this).attr("bookid");
			//设置属性值
			//$(this).attr("bookid",1);
			$.getJSON("http://localhost:8080/book/cartServlet","action=ajaxAddToCart&id="+bookid,function (data) {
				$("#cartTotalCt").html("您的购物车中有件"+data.totalCount+"商品");
			});
		})
	})
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="priceQuery">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max > 9999999?null:param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<c:if test="${empty sessionScope.cart.items}">
			<div style="text-align: center" >
				<span id="cartTotalCt">当前购物车为空</span>
			</div>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<div style="text-align: center" >
					<%--输出时是该属性的get方法,cart.totalCount -> cart.getTotalCount--%>
					<span id="cartTotalCt">您的购物车中有件${sessionScope.cart.totalCount}商品</span>
				</div>
			</c:if>
			<%--商品信息--%>
			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="static/img/default.jpg" />
				</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<%--可以自创一个键值对，来保存信息--%>
							<button bookId="${book.id}" class="addToCart">加入购物车</button>
							<%--<a href="cartServlet?action=ajaxAddToCart&id=${book.id}" STYLE="color: brown">加入购物车</a>--%>
						</div>
					</div>
			</div>
			</c:forEach>
			<%--商品信息--%>
		</div>
		<%--分页操作--%>
		<%@include file="/pages/common/page_query.jsp"%>
	
	</div>
	<%--页脚信息--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
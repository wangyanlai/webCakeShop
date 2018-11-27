<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<title>订单列表</title>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container-fluid">
	<jsp:include page="/admin/header.jsp"></jsp:include>	
	<br>	
	<ul role="tablist" class="nav nav-tabs">
        <li class="<c:if test="${status == 0 }">active</c:if>" role="presentation"><a href="${pageContext.request.contextPath }/admin/order_list">全部订单</a></li>
        <li class="<c:if test="${status == 1 }">active</c:if>" role="presentation"><a href="${pageContext.request.contextPath }/admin/order_list?status=1">未付款</a></li>
        <li class="<c:if test="${status == 2 }">active</c:if>" role="presentation"><a href="${pageContext.request.contextPath }/admin/order_list?status=2">已付款</a></li>
        <li class="<c:if test="${status == 3 }">active</c:if>" role="presentation"><a href="${pageContext.request.contextPath }/admin/order_list?status=3">配送中</a></li>
        <li class="<c:if test="${status == 4 }">active</c:if>" role="presentation"><a href="${pageContext.request.contextPath }/admin/order_list?status=4">已完成</a></li>
    </ul>   
    <br>	
	<table class="table table-bordered table-hover">
	<tr>
		<th width="5%">ID</th>
		<th width="5%">总价</th>
		<th width="15%">商品详情</th>
		<th width="20%">收货信息</th>
		<th width="10%">订单状态</th>
		<th width="10%">支付方式</th>
		<th width="10%">下单用户</th>
		<th width="10%">下单时间</th>
		<th width="10%">操作</th>
	</tr>
	
  	<c:forEach items="${p.list }" var = "order">          	
         <tr>
         	<td><p>${order.id }</p></td>
         	<td><p>${order.total }</p></td>
         	<td>   
         		<c:forEach items="${order.itemList }" var="item">      	
		         	<p>${item.goodsName }(${item.price }) x ${item.amount }</p>
		        </c:forEach>       	
         	</td>
         	<td>
         		<p>${order.name }</p>
         		<p>${order.phone }</p>
         		<p>${order.address }</p>
         	</td>
			<td>
				<p><c:if test="${order.status == 2 }"><span style="color:red;">已付款</span></c:if></p>
				<p><c:if test="${order.status == 3 }"><span style="color:green;">已发货</span></c:if></p>
				<p><c:if test="${order.status == 4 }"><span style="color:black;">已完成</span></c:if></p>
			</td>
			<td>
			<c:if test="${order.paytype == 1 }"><span style="color:black;">微信</span></c:if>
			<c:if test="${order.paytype == 2 }"><span style="color:black;">支付宝</span></c:if>
			<c:if test="${order.paytype == 3 }"><span style="color:black;">货到付款</span></c:if>
			</td>
			<td><p>${order.user.username }</p></td>
			<td><p>${order.datetime }</p></td>
			<td>
				<c:if test="${order.status == 2 }"><a class="btn btn-success" href="${pageContext.request.contextPath }/admin/order_status?id=${order.id }&status=3">发货</a></c:if>
				<c:if test="${order.status == 3 }"><a class="btn btn-success" href="${pageContext.request.contextPath }/admin/order_status?id=${order.id }&status=4">完成</a></c:if>
				<a class="btn btn-danger" href="${pageContext.request.contextPath }/admin/order_delete?id=${order.id }&pageNo=${p.pageNo }&status=${status }">删除</a>
			</td>
       	</tr>	
     </c:forEach>
</table>

<br>
<div style='text-align:center;'>
<a class='btn btn-info' <c:choose><c:when test="${p.pageNo == 1 }">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }/admin/order_list?pageNo=1&status=${status }"</c:otherwise></c:choose>>首页</a>
<a class='btn btn-info' <c:choose><c:when test="${p.pageNo == 1 }">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }/admin/order_list?pageNo=${p.pageNo-1 }&status=${status }"</c:otherwise></c:choose>>上一页</a>
<h3 style='display:inline;'>[${p.pageNo }/${p.pageCount }]</h3>
<h3 style='display:inline;'>[${p.totalCount }]</h3>
<a class='btn btn-info' <c:choose><c:when test="${p.pageCount == 0 || p.pageNo == p.pageCount }">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }/admin/order_list?pageNo=${p.pageNo+1 }&status=${status }"</c:otherwise></c:choose>>下一页</a>
<a class='btn btn-info' <c:choose><c:when test="${p.pageCount == 0 || p.pageNo == p.pageCount }">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }/admin/order_list?pageNo=${p.pageCount }&status=${status }"</c:otherwise></c:choose>>尾页</a>
<input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }/admin/order_list?pageNo="+(this.previousSibling.value)+"&status=${status }"'>GO</a>
</div>
<%-- <jsp:include page="/page.jsp"> --%>
<%-- 	<jsp:param value="/admin/order_list" name="url"/> --%>
<%-- 	<jsp:param value="&status=${status }" name="param"/> --%>
<%-- </jsp:include> --%>
<br>
</div>
</body>
</html>
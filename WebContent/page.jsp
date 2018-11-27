<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style='text-align:center;'>
<a class='btn btn-info' <c:choose><c:when test="${p.pageNo == 1 || p.pageNo > p.pageCount}">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }${param.url }?pageNo = 1${param.param }"</c:otherwise></c:choose> >首页</a>
<a class='btn btn-info' <c:choose><c:when test="${p.pageNo == 1 || p.pageNo > p.pageCount}">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }${param.url }?pageNo=${p.pageNo-1 }${param.param }"</c:otherwise></c:choose> >上一页</a>
<h3 style='display:inline;'>[${p.pageNo }/${p.pageCount }]</h3>
<h3 style='display:inline;'>[${p.totalCount }]</h3>
<a class='btn btn-info' <c:choose><c:when test="${p.pageCount == 0 || p.pageNo >= p.pageCount }">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }${param.url }?pageNo=${p.pageNo+1 }${param.param }"</c:otherwise></c:choose> >下一页</a>
<a class='btn btn-info' <c:choose><c:when test="${p.pageCount == 0 || p.pageNo >= p.pageCount }">disabled</c:when><c:otherwise>href="${pageContext.request.contextPath }${param.url }?pageNo=${p.pageCount }${param.param }"</c:otherwise></c:choose> >尾页</a>
<input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }${param.url }?pageNo="+(this.previousSibling.value)+"${param.param }"'>GO</a>
</div>
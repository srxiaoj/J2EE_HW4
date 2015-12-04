<%-- Haorui Wu
	 andrewid: haoruiw --%>

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.FavoriteBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>
	<table>
	<c:forEach var="favoriteBean" items="${favoriteList}">
		<tr>
			<td></td>
			<td><a href="?favoriteId=${favoriteBean.favoriteId}">
					${favoriteBean.url} </a></td>
		</tr>
		<tr>
			<td></td>
			<td>${favoriteBean.comment}</td>
		</tr>
		<tr>
			<td></td>
			<td>${favoriteBean.clickCount} Clicks</td>
		</tr>
	</c:forEach>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />

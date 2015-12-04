<%@page import="java.util.List"%>
<%@page import="databeans.FavoriteBean"%>
<%@page import="databeans.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<form action="manage.do" method="POST">
	<table>
		<tr>
			<td colspan="3"><hr /></td>
		</tr>
		<tr>
			<td style="font-size: large">URL:</td>
			<td colspan="2"><input type="text" size="40" name="url" /></td>
		</tr>
		<tr>
			<td style="font-size: large">Comment:</td>
			<td colspan="2"><input type="text" size="40" name="comment" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="action" value="Add Favorite" /></td>
			<td><input type="submit" name="action" value="Log out" /></td>
		</tr>
		<tr>
			<td colspan="3"><hr /></td>
		</tr>
	</table>
</form>

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
		<tr>
			<td></td>
			<td>
				<form action="remove.do" method="POST">
					<input type="hidden" name="id" value="${favoriteBean.favoriteId}" />
					<input type="submit" value="Delete" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>


<jsp:include page="template-bottom.jsp" />


<jsp:include page="template-top.jsp" />

<%@ page import="databeans.FavoriteBean" %>
<p>
	<table>
<%
    for (FavoriteBean favorite : (FavoriteBean[])request.getAttribute("favoriteList")) {
%>
		<tr>
			<td><a href="view.do?id=<%=favorite.getUrl()%>"><%=favorite.getComment()%></a></td>
		</tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />

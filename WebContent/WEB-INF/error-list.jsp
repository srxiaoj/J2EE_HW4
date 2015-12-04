<%-- Haorui Wu
	 andrewid: haoruiw --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${errors != null && fn:length(errors) > 0}">
		<p style="font-size: medium; color: red">
			<c:forEach var="error" items="${errors}">
			${error}<br />
			</c:forEach>
		</p>
	</c:when>
</c:choose>

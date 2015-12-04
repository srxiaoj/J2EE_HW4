<%
    java.util.List<String> errors = (java.util.List) request.getAttribute("errors");
	if (errors != null && errors.size() > 0) {
		out.println("<p style=\"font-size:medium; color:red\">");
		for (String error : errors) {
			out.println(error+"<br/>");
		}
		out.println("</p>");
	}
%>

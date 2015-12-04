<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>Favorite Websites</title>
	<style>
		.menu-head {font-size: 10pt; font-weight: bold; color: black; }
		.menu-item {font-size: 10pt;  color: black }
    </style>
</head>

<body>
<%@ page import="databeans.UserBean" %>

<table cellpadding="4" cellspacing="0">
    <tr>
	    <!-- Banner row across the top -->
        <td width="130" bgcolor="#99FF99">
            <img border="0" src="login2.jpg" height="75">
            <img border="0" src="login3.jpg" height="75"> </td>
        <td bgcolor="#99FF99">&nbsp;  </td>
        <td width="500" bgcolor="#99FF99">
            <p align="center">
<%
	if (request.getAttribute("title") == null) {
%>
		        <font size="7">Favorite List</font>
<%
    } else {
%>
		        <font size="5"><%=request.getAttribute("title")%></font>
<%
    }
%>
			</p>
		</td>
    </tr>
	
	<!-- Spacer row -->
	<tr>
		<td bgcolor="#99FF99" style="font-size:5px">&nbsp;</td>
		<td colspan="2" style="font-size:5px">&nbsp;</td>
	</tr>
	
	<tr>
		<td bgcolor="#99FF99" valign="top" height="500">
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">
<%
    UserBean user = (UserBean) session.getAttribute("user");
	if (user == null) {
%>
				<span class="menu-item"><a href="login.do">Login</a></span><br/>
				<span class="menu-item"><a href="register.do">Register</a></span><br/>
<%
    } else {
%>
				<span class="menu-head"><%=user.getFirstName()%> <%=user.getLastName()%></span><br/>
				<span class="menu-item"><a href="manage.do">Manage Your Favorite Websites</a></span><br/>
				<span class="menu-item"><a href="change-pwd.do">Change Password</a></span><br/>
				<span class="menu-item"><a href="logout.do">Logout</a></span><br/>
				<span class="menu-item">&nbsp;</span><br/>
				<span class="menu-head">Photos From:</span><br/>
<%
        for (UserBean u : (UserBean[])request.getAttribute("userList")) {
%>
			    <span class="menu-item">
					<a href="list.do?Email<%=u.getEmail()%>">
						<%=u.getFirstName()%> <%=u.getLastName()%>
					</a>
				</span>
				<br/>
<%
		}
    }
%>
			</p>
		</td>
		
		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">

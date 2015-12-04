<jsp:include page="template-top.jsp" />

<p style="font-size:medium">
	Enter your new password
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="POST" action="change-pwd.do">
		<table>
			<tr>
				<td> New Password: </td>
				<td><input type="password" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td> Confirm New Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Change Password"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />

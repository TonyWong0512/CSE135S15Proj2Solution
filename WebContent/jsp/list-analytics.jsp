<%@page import="java.util.List" import="helpers.*" import="models.*"%>
<%
    List<User> users = AnalyticsHelper.listUsersInAlphabeticalOrder(out);
    List<Product> products = AnalyticsHelper.listProductsInAlphabeticalOrder(out);
    int size = 10 - users.size();
%>

<table class="table table-bordered" align="center">
	<thead>
		<tr align="center">
			<th class="col-md-1"><B></B></th>
			<%
			    for (User user : users) {
			%>
			<th class="col-md-1"><B> <%=user.getUserName()%>
			</B></th>
			<%
			    }
			%>
			<%
			    for (int i = 0; i < size; i++) {
			%>
			<th class="col-md-1"><B></B></th>
			<%
			    }
			%>
		</tr>
	</thead>
	<tbody>
		<%
		    for (Product product : products) {
		%>
		<tr align="center">
			<td class="col-md-1"><B><%=product.getName()%></B></td>
			<%
			    for (User user : users) {
			%>
			<td class="col-md-1"></td>
			<%
			    }
			%>
			<%
			    for (int i = 0; i < size; i++) {
			%>
			<td class="col-md-1"><B></B></td>
			<%
			    }
			%>
		</tr>
		<%
		    }
		%>
	</tbody>
</table>


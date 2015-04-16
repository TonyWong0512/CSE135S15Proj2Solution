<!-- You will see on this form a CSS construct called a modal. Don't let
this scare you, this is just some pretty presentation. They allow you to create
boxes that appear when clicking on a button. You do not have to use them if you
don't want to. -->
<%@page import="java.util.List" import="helpers.*"%>
<%=ProductsHelper.modifyProducts(request)%>
<%
	List<ProductWithCategoryName> products = ProductsHelper.listProducts(request);
%>
<table class="table table-striped" align="center">
    <thead>
        <tr align="center">
            <th width="20%"><B>Product Name</B></th>
            <th width="20%"><B>SKU</B></th>
            <th width="20%"><B>Category Name</B></th>
            <th width="20%"><B>Price</B></th>
            <th width="20%" colspan="2"><B>Operations</B></th>
        </tr>
    </thead>
    <tbody>
    <%
    	for (ProductWithCategoryName p : products) {
    %>
            <tr>
            <td><%= p.getName() %></td>
            <td><%= p.getSKU() %></td>
            <td><%= p.getCategoryName() %></td>
            <td><%= p.getPrice() %></td>
            <td>
                <%-- <form action="categories" method="post">
                    <input type="text" name="action" id="action" value="delete" style="display: none"> <input type="text"
                        name="id" id="id" value="<%=p.getId()%>" style="display: none"> <input type="submit"
                        value="Delete">
                </form>
                <button data-toggle="modal" data-target="#product-<%=p.getId()%>">Update</button> --%>
            </td>
            </tr>
    <% } %>
    </tbody>
</table>
<%@page import="java.util.List" import="helpers.*"%>
<%
	List<CategoryWithCount> categories = CategoriesHelper
			.listCategories();
%>

<div class="panel panel-default">
    <div class="panel-body">
        Filter By Category :
        <div class="bottom-nav">
            <ul class="nav nav-list">
                <%
                	for (CategoryWithCount cwc : categories) {
                %>
                <li>
                    <form action="products" method="post">
                    <input type="text" name="search" id="search" value="<%=request.getParameter("search")%>" style="display: none">
                        <input type="text" name="category" id="category" value="<%=cwc.getId()%>" style="display: none"> <input
                            type="submit" value="<%=cwc.getName()%>">
                    </form>
                </li>
                <%
                	}
                %>
            </ul>
        </div>
    </div>
</div>
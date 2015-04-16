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
                    <form action="categories" method="post">
                        <input type="text" name="name" id="name" value="<%=cwc.getName()%>" style="display: none"> <input
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
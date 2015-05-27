<%@page import="java.util.List" import="helpers.*" import="models.*"%>
<%
    List<CategoryWithCount> categories = CategoriesHelper.listCategoriesNoCount();
%>
<div class="panel panel-default">
	<div class="panel-body">
		<div class="bottom-nav">
			<form action="analytics" method="POST" >
				<ul class="nav nav-list">
					<li><select name="rowType" id="search_key">
							<option value="Customers" selected="selected">Customers</option>
							<option value="States">States</option>
					</select></li>
                    <li><select name="order" id="search_key_0">
                            <option value="Alphabetical" selected="selected">Alphabetical</option>
                            <option value="TopK">TopK</option>
                    </select>
					<li><select name="category" id="search_key_2">
							<option value="0" selected="selected">All Categories</option>
							<%
							    for (CategoryWithCount c : categories) {
							        out.println("<option value=\"" + c.getId() + "\">" + c.getName() + "</option>");
							    }
							%>
					</select></li>
				</ul>
				<input type="hidden" name="row_offset" value="0">
				<input type="hidden" name="col_offset" value="0"> 
				<input type="hidden" name="action" value="search">
				<input style="margin : 10px 15px;" type="submit" value="Search">
			</form>
			</form>
		</div>
	</div>
</div>
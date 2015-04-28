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
                    </select><div class="alert alert-warning">Order not yet supported</div></li>
					<li><select name="state" id="search_key_1">
							<option value="All" selected="selected">All States</option>
							<option value="Alabama">Alabama</option>
							<option value="Alaska">Alaska</option>
							<option value="Arizona">Arizona</option>
							<option value="Arkansas">Arkansas</option>
							<option value="California">California</option>
							<option value="Colorado">Colorado</option>
							<option value="Connecticut">Connecticut</option>
							<option value="Delaware">Delaware</option>
							<option value="Florida">Florida</option>
							<option value="Georgia">Georgia</option>
							<option value="Hawaii">Hawaii</option>
							<option value="Idaho">Idaho</option>
							<option value="Illinois">Illinois</option>
							<option value="Indiana">Indiana</option>
							<option value="Iowa">Iowa</option>
							<option value="Kansas">Kansas</option>
							<option value="Kentucky">Kentucky</option>
							<option value="Louisiana">Louisiana</option>
							<option value="Maine">Maine</option>
							<option value="Maryland">Maryland</option>
							<option value="Massachusetts">Massachusetts</option>
							<option value="Michigan">Michigan</option>
							<option value="Minnesota">Minnesota</option>
							<option value="Mississippi">Mississippi</option>
							<option value="Missouri">Missouri</option>
							<option value="Montana">Montana</option>
							<option value="Nebraska">Nebraska</option>
							<option value="Nevada">Nevada</option>
							<option value="New Hampshire">New Hampshire</option>
							<option value="New Jersey">New Jersey</option>
							<option value="New Mexico">New Mexico</option>
							<option value="New York">New York</option>
							<option value="North Carolina">North Carolina</option>
							<option value="North Dakota">North Dakota</option>
							<option value="Ohio">Ohio</option>
							<option value="Oklahoma">Oklahoma</option>
							<option value="Oregon">Oregon</option>
							<option value="Pennsylvania">Pennsylvania</option>
							<option value="Rhode Island">Rhode Island</option>
							<option value="South Carolina">South Carolina</option>
							<option value="South Dakota">South Dakota</option>
							<option value="Tennessee">Tennessee</option>
							<option value="Texas">Texas</option>
							<option value="Utah">Utah</option>
							<option value="Vermont">Vermont</option>
							<option value="Virginia">Virginia</option>
							<option value="Washington">Washington</option>
							<option value="West Virginia">West Virginia</option>
							<option value="Wisconsin">Wisconsin</option>
							<option value="Wyoming">Wyoming</option>
					</select></li>
					<li><select name="category" id="search_key_2">
							<option value="0" selected="selected">All Categories</option>
							<%
							    for (CategoryWithCount c : categories) {
							        out.println("<option value=\"" + c.getId() + "\">" + c.getName() + "</option>");
							    }
							%>
					</select></li>
					<li><select name="age" id="search_key_3">
							<option value="0" selected="selected">All Ages</option>
							<option value="12_18">12-18</option>
							<option value="18_45">18-45</option>
							<option value="45_65">45-65</option>
							<option value="65_100">65-</option>
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
package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ProductsHelper {

	public static List<ProductWithCategoryName> listProducts(HttpServletRequest request) {
		List<ProductWithCategoryName> productWithCategoryNames = new ArrayList<ProductWithCategoryName>();
		String categoryFilter = "", searchFilter = ""; 
		try {
			Integer cid = Integer.parseInt(request.getParameter("category"));
			if (cid != null)
				categoryFilter =  "cid = " + cid; 
		} catch (Exception e) {
		}
		try {
			String search = request.getParameter("search");
			if (search != null)
				searchFilter = "name ~ '" + search + "'";
		} catch (Exception e) {
		}
		String filter = null;
		if (categoryFilter.isEmpty()) {
			if (searchFilter.isEmpty()) {
				filter = "";
			} else {
				filter = " WHERE " + searchFilter;
			}
		} else {
			if (searchFilter.isEmpty()) {
				filter = " WHERE " + categoryFilter;
			} else {
				filter = " WHERE " + categoryFilter + " AND " + searchFilter;
			}
		}
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (Exception e) {
				System.err
						.println("Internal Server Error. This shouldn't happen.");
				return new ArrayList<ProductWithCategoryName>();
			}
			String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
			String user = "postgres";
			String password = "postgres";
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String query = "WITH selected AS (SELECT * FROM products" + filter + ") SELECT s.id, c.name, s.name, s.SKU, s.price FROM selected s JOIN categories c ON s.cid = c.id";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String cname = rs.getString(2);
				String name = rs.getString(3);
				String SKU = rs.getString(4);
				Integer price = rs.getInt(5);
				productWithCategoryNames.add(new ProductWithCategoryName(id, cname, name, SKU, price));
			}
			return productWithCategoryNames;
		} catch (Exception e) {
			System.err.println("Some error happened!<br/>"
					+ e.getLocalizedMessage());
			return new ArrayList<ProductWithCategoryName>();
		}
	}
	
	public static String modifyProducts(HttpServletRequest request) {
		return "";
	}
	
}
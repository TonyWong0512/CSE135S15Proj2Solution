package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class PurchaseHelper {
	
	public static String purchaseCart(ShoppingCart cart, Integer uid) {
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (Exception e) {
				return HelperUtils.printError("Internal Server Error. This shouldn't happen.");
			}
			String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
			String user = "postgres";
			String password = "postgres";
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			for (int i = 0 ; i < cart.getProducts().size() ; i++) {
				ProductWithCategoryName p = cart.getProducts().get(i);
				int quantity = cart.getQuantities().get(i);
				String SQL_1 = "INSERT INTO sales (uid, pid, quantity, price) VALUES("
						+ uid
						+ ",'"
						+ p.getId()
						+ "','"
						+ quantity
						+ "', "
						+ p.getPrice()
						+ ");";
				try {
					stmt.execute(SQL_1);
					conn.commit();
				} catch (Exception e) {
					return HelperUtils
							.printError("Insert failed! Please <a href=\"products\" target=\"_self\">insert it</a> again.<br/>"
									+ e.getLocalizedMessage());
				}

			}
			cart.empty();
			return HelperUtils.printSuccess("Purchase successful!");
		} catch (Exception e) {
			return HelperUtils.printError(e.getLocalizedMessage());
		}
	}
	
	public static ShoppingCart obtainCartFromSession(HttpSession session) {
		ShoppingCart cart;
	    try { 
		     cart = (ShoppingCart) session.getAttribute("cart");
		    if (cart == null) {
		        cart = new ShoppingCart();
		    }
	    } catch (Exception e) {
	        cart = new ShoppingCart();	    	
	    }
	    session.setAttribute("cart",cart);
	    return cart;
	}
	

}

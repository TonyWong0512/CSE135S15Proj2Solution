package helpers;

import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * @author Jules Testard
 * 
 */
public class IndexHelper {

	public static String login(String name, HttpSession session) {
		Connection conn = null;
		Statement stmt;
		try {

			try {
				Class.forName("org.postgresql.Driver");
			} catch (Exception e) {
				System.out.println("Driver error");
			}
			String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
			String user = "postgres";
			String password = "postgres";
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery("SELECT * FROM  users where name='" + name
					+ "';");

			String role = null;
			int userID = 0;
			int t = 0;
			if (rs.next()) {
				userID = rs.getInt(1);
				role = rs.getString(3);
				session.setAttribute("name", name);
				session.setAttribute("role", role);
				t++;
			}
			if (t == 0) {
				String data = "No user found with this name!";
				return HelperUtils.printError(data);
			}
			conn.close();
			String data = "You have successfully logged in!";
			return HelperUtils.printSuccess(data);
		} catch (Exception e) {
			String data = "Error, can not access the database, please check the database connection...";
			return HelperUtils.printError(data);
		}
	}
}
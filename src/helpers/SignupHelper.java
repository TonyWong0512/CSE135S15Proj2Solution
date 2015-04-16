package helpers;

import java.sql.*;

public class SignupHelper {

	public static String signup(String name, Integer age, String role,
			String state) {

		Connection conn = null;
		Statement stmt;

		try {
			// Should use prepared statements.
			String SQL = "INSERT INTO users (name, role, age, state) VALUES('"
					+ name + "','" + role + "'," + age + ",'" + state + "');";
			try {
				Class.forName("org.postgresql.Driver");
			} catch (Exception e) {
				System.out
						.println("Could not register PostgreSQL JDBC driver with the DriverManager");
			}
			String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
			String user = "postgres";
			String password = "postgres";
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			try {
				conn.setAutoCommit(false);
				stmt.execute(SQL);
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				return HelperUtils
						.printError("Fail, can not access the database, please check the database status first! Please <a href='signup.jsp' target='_self'>register</a> again.");
			}
			conn.close();
		} catch (Exception e) {
			String output = "Error.<br><a href=\"login.jsp\" target=\"_self\"><i>Go Back to Home Page.</i></a>";
			return HelperUtils.printError(output);
		}
		String output = "<h4>Registered successfully!</h4> <br>";
		output += "<table><tr><td>Name:</td><td>" + name
				+ "</td></tr><tr><td>Role:</td><td>" + role
				+ "</td></tr><tr><td>Age:</td><td>" + age
				+ "</td></tr><tr><td>State:</td><td>" + state
				+ "</td></tr></table>";
		return HelperUtils.printSuccess(output);
	}
}

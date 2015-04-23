package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignupHelper {

    public static String signup(String name, Integer age, String role, String state) {

        Connection conn = null;
        Statement stmt;

        try {
            int stateId = getStateId(state);
            String SQL = "INSERT INTO users (name, role, age, state) VALUES('" + name + "','" + role + "'," + age
                    + ",'" + stateId + "');";
            try {
                Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
                System.out.println("Could not register PostgreSQL JDBC driver with the DriverManager");
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
                return HelperUtils.printError(e.getLocalizedMessage());
            }
            conn.close();
        } catch (Exception e) {
            String output = "A problem happened while interacting with the database : \n" + e.getLocalizedMessage();
            return HelperUtils.printError(output);
        }
        String output = "<h4>Registered successfully!</h4> <br>";
        output += "<table><tr><td>Name:</td><td>" + name + "</td></tr><tr><td>Role:</td><td>" + role
                + "</td></tr><tr><td>Age:</td><td>" + age + "</td></tr><tr><td>State:</td><td>" + state
                + "</td></tr></table>";
        return HelperUtils.printSuccess(output);
    }

    public static int getStateId(String stateName) throws SQLException {
        // Look up the state id.
        String query = "SELECT id FROM states WHERE name=\'" + stateName + "\'";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println("Could not register PostgreSQL JDBC driver with the DriverManager");
        }
        String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
        String user = "postgres";
        String password = "postgres";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("There is no state with this name : " + stateName);
        }
    }
}

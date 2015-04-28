package models;

import helpers.AnalyticsHelper.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class State {

    private int id;

    private String name;

    /**
     * @param id
     * @param name
     */
    public State(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<State> getStatesByOrder(Order o) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Internal Server Error. This shouldn't happen.");
            return new ArrayList<State>();
        }
        ArrayList<State> states = new ArrayList<State>();
        String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
        String user = "postgres";
        String password = "postgres";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM users ORDBER BY name LIMIT 20";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Integer id = rs.getInt(1);
            String name = rs.getString(2);
            states.add(new State(id, name));
        }
        return states;
    }

}

/**
 * 
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author julestestard
 */
public class SaleWithPositions {

    private int id;

    private int uid;

    private int pid;

    private int quantity;

    private int price;

    private int x;

    private int y;

    /**
     * @param id
     * @param uid
     * @param pid
     * @param quantity
     * @param price
     */
    public SaleWithPositions(int id, int uid, int pid, int quantity, int price, int x, int y) {
        super();
        this.id = id;
        this.uid = uid;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
        this.x = x;
        this.y = y;
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
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     *            the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     *            the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    public static ArrayList<User> getSalesWithPositionsForUser(List<User> users, List<Product> products)
            throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Internal Server Error. This shouldn't happen.");
            return new ArrayList<User>();
        }
        ArrayList<SaleWithPositions> salesWithPositions = new ArrayList<SaleWithPositions>();
        String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
        String user = "postgres";
        String password = "postgres";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM users ORDER BY name LIMIT 20";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Integer id = rs.getInt(1);
            String name = rs.getString(2);
            String role = rs.getString(3);
            Integer age = rs.getInt(4);
            Integer state = rs.getInt(5);
            users.add(new User(id, name, role, age, state));
        }
        return users;
    }

}

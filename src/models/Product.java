/**
 * 
 */
package models;

import helpers.AnalyticsHelper.Order;
import helpers.HelperUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Jules Testard
 */
public class Product {

    private int id;

    private int cid;

    private String name;

    private String SKU;

    private int price;

    /**
     * @param id
     * @param cid
     * @param name
     * @param sKU
     * @param price
     */
    public Product(int id, int cid, String name, String sKU, int price) {
        this.id = id;
        this.cid = cid;
        this.name = name;
        SKU = sKU;
        this.price = price;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sKU
     */
    public String getSKU() {
        return SKU;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return the cid
     */
    public int getCid() {
        return cid;
    }

    public static ArrayList<Product> getProductsByOrder(Order o) throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();
        Connection conn = null;
        try {
            conn = HelperUtils.connect();
        } catch (Exception e) {
            System.err.println("Internal Server Error. This shouldn't happen.");
            return new ArrayList<Product>();
        }
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM products ORDER BY name LIMIT 10";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Integer id = rs.getInt(1);
            Integer cid = rs.getInt(2);
            String name = rs.getString(3);
            String SKU = rs.getString(4);
            Integer price = rs.getInt(5);
            products.add(new Product(id, cid, name, SKU, price));
        }
        return products;
    }
}

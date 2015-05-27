package helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.jsp.JspWriter;

import models.Analytics;
import models.Product;
import models.User;

public class AnalyticsHelper {

    static final int show_num_row = 20, show_num_col = 10;

    public static Analytics AnalysisForUsers(String order, String category, int rowOffset, int colOffset, JspWriter out) {
        Connection conn = null;
        Statement stmt, stmt2;
        ResultSet rs, rs2;
        // Statements that vary among user configurations
        String tmpTable1 = null, tmpTable2 = null;
        String userQuery = null, userQuery2 = null, userQuery3 = null;
        String productQuery = null, productQuery2 = null, productQuery3 = null;
        String colCount = null;
        int maxUser, maxProduct;

        //Statement that do not change
        String cellsQuery = "select s.uid, s.pid, sum(s.quantity*s.price) from u_t u,p_t p, sales s where s.uid=u.id and s.pid=p.id group by s.uid, s.pid;";
        String rowCount = "select count(*) from users";

        try {
            conn = HelperUtils.connect();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            if ("Alphabetical".equals(order)) {
                tmpTable1 = "CREATE TEMP TABLE p_t (id int, name text) ON COMMIT DELETE ROWS;";
                tmpTable2 = "CREATE TEMP TABLE u_t (id int, name text) ON COMMIT DELETE ROWS;";
                if ("0".equals(category)) {
                    userQuery = "select id,name from users order by name asc offset " + rowOffset + " limit 20";
                    userQuery2 = "insert into u_t (id, name) " + userQuery;
                    userQuery3 = "select u.name, s.uid, sum(s.quantity*s.price) from  u_t u, sales s where s.uid=u.id group by s.uid, u.name order by u.name desc;";
                    productQuery = "select id,name from products order by name asc offset " + colOffset + " limit "
                            + show_num_col;
                    productQuery2 = "insert into p_t (id, name) " + productQuery;
                    productQuery3 = "select p.name, s.pid, sum(s.quantity*s.price) from p_t p, sales s where s.pid=p.id  group by s.pid, p.name order by p.name desc;";
                    colCount = "select count(*) from products";
                } else {
                    userQuery = "select id,name from users order by name asc offset " + rowOffset + " limit 20";
                    userQuery2 = "insert into u_t (id, name) " + userQuery;
                    userQuery3 = "select u.name, s.uid, sum(s.quantity*s.price) from  u_t u LEFT OUTER JOIN (select s2.uid as uid, s2.pid as pid, s2.price as price, s2.quantity as quantity  from sales s2, products p where s2.pid = p.id and p.cid = "
                            + category + ") s ON s.uid=u.id group by s.uid, u.name;";
                    productQuery = "select id,name from products where cid=" + category + " order by name asc offset "
                            + colOffset + " limit " + show_num_col;
                    productQuery2 = "insert into p_t (id, name) " + productQuery;
                    productQuery3 = "select p.name, s.pid, sum(s.quantity*s.price) from p_t p, sales s where s.pid=p.id  group by s.pid, p.name order by p.name desc;";
                    colCount = "select count(*) from products where cid =" + category;
                }
            } else {
                tmpTable1 = "CREATE TEMP TABLE p_t (name text, id int, price int) ON COMMIT DELETE ROWS;";
                tmpTable2 = "CREATE TEMP TABLE u_t (name text, id int, price int) ON COMMIT DELETE ROWS;";
                if ("0".equals(category)) {
                    userQuery = "select u.name, s.uid, sum(s.quantity*s.price) as price from  users u left outer join sales s on s.uid=u.id group by s.uid, u.name order by price desc offset "
                            + rowOffset + " limit 20;";
                    userQuery2 = "insert into u_t (name, id, price) " + userQuery;
                    userQuery3 = "select * from u_t;";
                    productQuery = "select p.name, s.pid, sum(s.quantity*s.price) as price from products p left outer join sales s on s.pid=p.id  group by s.pid, p.name order by price desc offset "
                            + colOffset + " limit 10;";
                    productQuery2 = "insert into p_t (name, id, price) " + productQuery;
                    productQuery3 = "select * from p_t;";
                    colCount = "select count(*) from products";
                } else {
                    userQuery = "select s.uid, u.name, sum(s.quantity*s.price) as price from  users u LEFT OUTER JOIN (select s2.uid as uid, s2.pid as pid, s2.price as price, s2.quantity as quantity from sales s2, products p where s2.pid = p.id and p.cid = "
                            + category
                            + ") s ON s.uid=u.id group by s.uid, u.name order by price offset "
                            + rowOffset
                            + " limit 20;";
                    userQuery2 = "insert into u_t (name, id, price) " + userQuery;
                    userQuery3 = "select * from u_t;";
                    productQuery = "select s.pid, p.name, sum(s.quantity*s.price) as price from products p LEFT OUTER JOIN sales s ON s.pid=p.id WHERE p.cid="
                            + category + " group by s.pid, p.name order by price offset " + colOffset + " limit 10;";
                    productQuery2 = "insert into p_t (name, id, price) " + productQuery;
                    productQuery3 = "select * from p_t;";
                    colCount = "select count(*) from products where cid =" + category;
                }

            }
            // Dump on console query results;
            System.out.println("Temporary table 1 : " + tmpTable1);
            System.out.println("Temporary table 2 : " + tmpTable2);
            System.out.println("User Query 1 : " + userQuery);
            System.out.println("User Query 2 : " + userQuery2);
            System.out.println("User Query 3 : " + userQuery3);
            System.out.println("Product Query 1 : " + productQuery);
            System.out.println("Product Query 2 : " + productQuery2);
            System.out.println("Product Query 3 : " + productQuery3);
            System.out.println("Cells Query : " + cellsQuery);
            System.out.println("Row Count Query: " + rowCount);
            System.out.println("Column Count Query: " + colCount);

            long start = System.currentTimeMillis();
            conn.setAutoCommit(false);
            // Create and fill temp tables
            stmt2.execute(tmpTable1);
            stmt2.execute(tmpTable2);
            stmt2.execute(userQuery2);
            stmt2.execute(productQuery2);

            // Get counts
            maxUser = getRowOrColumnCount(stmt, rowCount);
            maxProduct = getRowOrColumnCount(stmt, colCount);

            HashMap<Integer, User> users = new HashMap<Integer, User>();
            ArrayList<Integer> userPosition = new ArrayList<Integer>();
            HashMap<Integer, Integer> userPrices = new HashMap<Integer, Integer>();
            HashMap<Integer, Product> products = new HashMap<Integer, Product>();
            ArrayList<Integer> productPosition = new ArrayList<Integer>();
            HashMap<Integer, Integer> productPrices = new HashMap<Integer, Integer>();

            rs = stmt.executeQuery(userQuery3);
            while (rs.next()) {
                String name = rs.getString(1);
                int uid = rs.getInt(2);
                int amount = rs.getInt(3);
                userPrices.put(uid, amount);
                users.put(uid, new User(uid, name, null, 0, 0));
                userPosition.add(uid);
            }

            rs = stmt.executeQuery(productQuery3);
            while (rs.next()) {
                String name = rs.getString(1);
                int pid = rs.getInt(2);
                int amount = rs.getInt(3);
                productPrices.put(pid, amount);
                products.put(pid, new Product(pid, 0, name, null, 0));
                productPosition.add(pid);
            }
            int[][] prices = new int[show_num_row][show_num_col];
            rs = stmt.executeQuery(cellsQuery);
            while (rs.next()) {
                int uid = rs.getInt(1);
                int pid = rs.getInt(2);
                int amount = rs.getInt(3);
                prices[userPosition.indexOf(uid)][productPosition.indexOf(pid)] = amount;
            }
            long end = System.currentTimeMillis();
            long time_taken = end - start;
            Analytics a = new Analytics(maxUser, maxProduct, 0, productPrices, userPrices, null, products, users, null,
                    productPosition, userPosition, null, prices, time_taken);
            return a;
        } catch (Exception e) {
            printError(out, e);
            return new Analytics();
        }
    }

    public static Analytics AnalysisForStates(String order, String category, int rowOffset, int colOffset, JspWriter out) {
        try {
            if ("Alphabetical".equals(order)) {
                if ("0".equals(category)) {

                } else {

                }

            } else {
                if ("0".equals(category)) {

                } else {

                }

            }
            return new Analytics();
        } catch (Exception e) {
            printError(out, e);
            return new Analytics();
        }
    }

    private static void printError(JspWriter out, Exception e) {
        try {
            out.println(HelperUtils.printError("Internal Server Error. This shouldn't happen : " + e.getMessage()));
            e.printStackTrace();
        } catch (Exception e2) {
            System.out.println(e2.getLocalizedMessage());
        }
    }

    private static int getRowOrColumnCount(Statement stmt, String query) throws SQLException {
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException("There was an error processing query " + query);
    }
}

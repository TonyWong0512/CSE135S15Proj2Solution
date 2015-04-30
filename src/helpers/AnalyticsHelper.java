package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.jsp.JspWriter;

import models.Analytics;
import models.Product;
import models.State;
import models.User;

public class AnalyticsHelper {

    static Connection conn = null;
    static Statement stmt, stmt2;
    static ResultSet rs = null;
    static String SQL_1 = null, SQL_2 = null, SQL_ut = null, SQL_pt = null, SQL_row = null, SQL_col = null;
    static String SQL_amount_row = null, SQL_amount_col = null, SQL_amount_cell = null, SQL_state = null;
    static int p_id = 0, u_id = 0;
    static String p_name = null, u_name = null;
    static int p_amount_price = 0, u_amount_price = 0;
    static int show_num_row = 20, show_num_col = 10;

    public enum Order {
        ALPHABETICAL,
        TOPK
    }

    public static Analytics AnalysisForUsers(String state, String category, String age, int rowOffset, int colOffset,
            JspWriter out) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                printError(out, e);
                return new Analytics();
            }
            String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
            String user = "postgres";
            String password = "postgres";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            // Convert state string to state id.
            if (!state.equals("All")) {
                SQL_state = "SELECT id FROM states WHERE name='" + state + "'";
                rs = stmt.executeQuery(SQL_state);
                if (rs.next()) {
                    state = rs.getString(1);
                }
            }

            if (("All").equals(state) && ("0").equals(category) && ("0").equals(age))//0,0,0
            {
                SQL_1 = "select id,name from users order by name asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users";
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s  where s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s where s.pid=p.id  group by s.pid;";
            }

            if (("All").equals(state) && !("0").equals(category) && ("0").equals(age))//0,1,0
            {
                SQL_1 = "select id,name from users order by name asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users";
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s where s.pid=p.id  group by s.pid;";
            }
            if (("All").equals(state) && ("0").equals(category) && !("0").equals(age))//0,0,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select id,name from users where age>" + minAge + " and age<=" + maxAge
                        + " order by name asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users where age>" + minAge + " and age<=" + maxAge + " ";
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s  where s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.age>"
                        + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            if (!("All").equals(state) && ("0").equals(category) && ("0").equals(age))//1,0,0
            {
                SQL_1 = "select id,name from users where state='" + state + "' order by name asc offset " + rowOffset
                        + " limit " + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users where state='" + state + "' ";
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s  where s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "'  group by s.pid;";
            }
            if (("All").equals(state) && !("0").equals(category) && !("0").equals(age))//0,1,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select id,name from users where age>" + minAge + " and age<=" + maxAge
                        + " order by name asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users where age>" + minAge + " and age<=" + maxAge + " ";
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.age>"
                        + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            if (!("All").equals(state) && !("0").equals(category) && ("0").equals(age))//1,1,0
            {
                SQL_1 = "select id,name from users where state='" + state + "' order by name asc offset " + rowOffset
                        + " limit " + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users where state='" + state + "' ";
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "'  group by s.pid;";

            }
            if (!("All").equals(state) && ("0").equals(category) && !("0").equals(age))//1,0,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select id,name from users where state='" + state + "' and age>" + minAge + " and age<="
                        + maxAge + " order by name asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users where state='" + state + "' and age>" + minAge + " and age<="
                        + maxAge + " ";
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s  where s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "' and u.age>" + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            if (!("All").equals(state) && !("0").equals(category) && !("0").equals(age))//1,1,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select id,name from users where state='" + state + "' and age>" + minAge + " and age<="
                        + maxAge + " order by name asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into u_t (id, name) " + SQL_1;
                SQL_pt = "insert into p_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from users where state='" + state + "' and age>" + minAge + " and age<="
                        + maxAge + " ";
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select s.uid, sum(s.quantity*s.price) from  u_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by s.uid;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from p_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "' and u.age>" + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            long start=System.currentTimeMillis();
            // Get customer
            HashMap<Integer, User> users = new HashMap<Integer, User>();
            HashMap<Integer, Integer> userPosition = new HashMap<Integer, Integer>();
            int k = 0;
            rs = stmt.executeQuery(SQL_1);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                users.put(id, new User(id, name, null, 0, 0));
                userPosition.put(id, k);
                k++;
            }

            // Get products
            HashMap<Integer, Product> products = new HashMap<Integer, Product>();
            HashMap<Integer, Integer> productPosition = new HashMap<Integer, Integer>();
            rs = stmt.executeQuery(SQL_2);
            k = 0;
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                products.put(id, new Product(id, 0, name, null, 0));
                productPosition.put(id, k);
                k++;
            }

            //temporary table
            conn.setAutoCommit(false);
            stmt2.execute("CREATE TEMP TABLE p_t (id int, name text)ON COMMIT DELETE ROWS;");
            stmt2.execute("CREATE TEMP TABLE u_t (id int, name text)ON COMMIT DELETE ROWS;");
            //customer tempory table
            stmt2.execute(SQL_ut);
            //product tempory table
            stmt2.execute(SQL_pt);

            //count the total tuples in  usres and products after filterings
            int maxUser = 0;
            rs = stmt.executeQuery(SQL_row);//if only customer can buy products, then limit to only customers
            if (rs.next()) {
                maxUser = rs.getInt(1);
            }
            int maxProduct = 0;
            rs = stmt.executeQuery(SQL_col);//if only customer can buy products, then limit to only customers
            if (rs.next()) {
                maxProduct = rs.getInt(1);
            }

            HashMap<Integer, Integer> userPrices = new HashMap<Integer, Integer>();
            rs = stmt.executeQuery(SQL_amount_row);
            while (rs.next()) {
                int uid = rs.getInt(1);
                int amount = rs.getInt(2);
                userPrices.put(uid, amount);
            }
            HashMap<Integer, Integer> productPrices = new HashMap<Integer, Integer>();
            rs = stmt.executeQuery(SQL_amount_col);
            while (rs.next()) {
                int pid = rs.getInt(1);
                int amount = rs.getInt(2);
                productPrices.put(pid, amount);
            }
            int[][] prices = new int[show_num_row][show_num_col];
            SQL_amount_cell = "select s.uid, s.pid, sum(s.quantity*s.price) from u_t u,p_t p, sales s where s.uid=u.id and s.pid=p.id group by s.uid, s.pid;";
            rs = stmt.executeQuery(SQL_amount_cell);
            while (rs.next()) {
                int uid = rs.getInt(1);
                int pid = rs.getInt(2);
                int amount = rs.getInt(3);
                prices[userPosition.get(uid)][productPosition.get(pid)] = amount;
            }
            long end=System.currentTimeMillis();
            long time_taken = end-start;
            return new Analytics(maxUser, maxProduct, 0, productPrices, userPrices, null, products, users, null, prices, time_taken);
        } catch (SQLException e) {
            printError(out, e);
            return new Analytics();
        } finally {
            try {
                stmt.close();
                stmt2.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Analytics AnalysisForStates(String state, String category, String age, int rowOffset, int colOffset,
            JspWriter out) {
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
            stmt2 = conn.createStatement();

            // Convert state string to state id.
            if (!state.equals("All")) {
                SQL_state = "SELECT id FROM states WHERE name='" + state + "'";
                rs = stmt.executeQuery(SQL_state);
                if (rs.next()) {
                    state = rs.getString(1);
                }
            }

            if (("All").equals(state) && ("0").equals(category) && ("0").equals(age))//0,0,0
            {
//                SQL_1 = "select id, name from states order by name asc offset " + rowOffset + " limit " + show_num_row;
//                SQL_ut = "insert into us_t (id, state) select u2.id, u.name from (" + SQL_1
//                        + ") as st left outer join users u on st.id = u.state order by st.name";

                SQL_1 = "select state from users group by state order by state asc offset " + rowOffset + " limit "
                        + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(*) from states";
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s  where s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s where s.pid=p.id  group by s.pid;";
            }

            if (("All").equals(state) && !("0").equals(category) && ("0").equals(age))//0,1,0
            {
                SQL_1 = "select state from users  group by state order by state asc offset " + rowOffset + " limit "
                        + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(distinct state) from users";
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s  where s.pid=p.id group by s.pid;";
            }
            if (("All").equals(state) && ("0").equals(category) && !("0").equals(age))//0,0,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select state from users where age>" + minAge + " and age<=" + maxAge
                        + "  group by state order by state asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(distinct state) from users where  age>" + minAge + " and age<=" + maxAge;
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s  where s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and  u.age>"
                        + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            if (!("All").equals(state) && ("0").equals(category) && ("0").equals(age))//1,0,0
            {
                SQL_1 = "select state from users where state='" + state
                        + "'  group by state order by state asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(distinct state) from users where state='" + state + "'";
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s  where s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "'  group by s.pid;";
            }
            if (("All").equals(state) && !("0").equals(category) && !("0").equals(age))//0,1,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select state from users where age>" + minAge + " and age<=" + maxAge
                        + "  group by state order by state asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(distinct state) from users where age>" + minAge + " and age<=" + maxAge;
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.age>"
                        + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            if (!("All").equals(state) && !("0").equals(category) && ("0").equals(age))//1,1,0
            {
                SQL_1 = "select state from users where state='" + state
                        + "'  group by state order by state asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(distinct state) from users where state='" + state + "'";
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "' group by s.pid;";

            }
            if (!("All").equals(state) && ("0").equals(category) && !("0").equals(age))//1,0,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select state from users where state='" + state + "' and age>" + minAge + " and age<=" + maxAge
                        + "  group by state order by state asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products order by name asc offset " + colOffset + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(distinct state) from users where state='" + state + "' and age>" + minAge
                        + " and age<=" + maxAge;
                SQL_col = "select count(*) from products";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s  where s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "' and u.age>" + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            if (!("All").equals(state) && !("0").equals(category) && !("0").equals(age))//1,1,1
            {
                String minAge = age.split("_")[0];
                String maxAge = age.split("_")[1];
                SQL_1 = "select state from users where state='" + state + "' and age>" + minAge + " and age<=" + maxAge
                        + "  group by state order by state asc offset " + rowOffset + " limit " + show_num_row;
                SQL_2 = "select id,name from products where cid=" + category + " order by name asc offset " + colOffset
                        + " limit " + show_num_col;
                SQL_ut = "insert into us_t (id, state) select u2.id, u.state from (" + SQL_1
                        + ") as u left outer join users u2 on u2.state=u.state order by u.state";
                SQL_pt = "insert into ps_t (id, name) " + SQL_2;
                SQL_row = "select count(distinct state) from users where state='" + state + "' and age>" + minAge
                        + " and age<=" + maxAge;
                SQL_col = "select count(*) from products where cid=" + category + "";
                SQL_amount_row = "select u.state, sum(s.quantity*s.price) from  us_t u, sales s, products p  where s.pid=p.id and p.cid="
                        + category + " and s.uid=u.id group by u.state;";
                SQL_amount_col = "select s.pid, sum(s.quantity*s.price) from ps_t p, sales s, users u where s.pid=p.id  and s.uid=u.id and u.state='"
                        + state + "' and u.age>" + minAge + " and u.age<=" + maxAge + "  group by s.pid;";
            }
            long start = System.currentTimeMillis();
            // Get customer
            HashMap<Integer, State> states = new HashMap<Integer, State>();
            HashMap<Integer, Integer> statePosition = new HashMap<Integer, Integer>();
            int k = 0;
            rs = stmt.executeQuery(SQL_1);
            while (rs.next()) {
                int id = rs.getInt(1);
                statePosition.put(id, k);
                k++;
            }

            for (int id : statePosition.keySet()) {
                SQL_state = "SELECT name FROM states WHERE id='" + id + "'";
                rs = stmt.executeQuery(SQL_state);
                String name = "";
                if (rs.next()) {
                    name = rs.getString(1);
                    states.put(id, new State(id, name));
                }
            }

            // Get products
            HashMap<Integer, Product> products = new HashMap<Integer, Product>();
            HashMap<Integer, Integer> productPosition = new HashMap<Integer, Integer>();
            rs = stmt.executeQuery(SQL_2);
            k = 0;
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                products.put(id, new Product(id, 0, name, null, 0));
                productPosition.put(id, k);
                k++;
            }

            //temporary table
            conn.setAutoCommit(false);
            stmt2.execute("CREATE TEMP TABLE us_t (id int, state text) ON COMMIT DELETE ROWS;");
            stmt2.execute("CREATE TEMP TABLE ps_t (id int, name text) ON COMMIT DELETE ROWS;");
            //customer tempory table
            stmt2.execute(SQL_ut);
            //product tempory table
            stmt2.execute(SQL_pt);

            //count the total tuples in  usres and products after filterings
            int maxState = 0;
            rs = stmt.executeQuery(SQL_row);//if only customer can buy products, then limit to only customers
            if (rs.next()) {
                maxState = rs.getInt(1);
            }
            int maxProduct = 0;
            rs = stmt.executeQuery(SQL_col);//if only customer can buy products, then limit to only customers
            if (rs.next()) {
                maxProduct = rs.getInt(1);
            }

            HashMap<Integer, Integer> statePrices = new HashMap<Integer, Integer>();
            rs = stmt.executeQuery(SQL_amount_row);
            while (rs.next()) {
                int sid = rs.getInt(1);
                int amount = rs.getInt(2);
                statePrices.put(sid, amount);
            }
            HashMap<Integer, Integer> productPrices = new HashMap<Integer, Integer>();
            rs = stmt.executeQuery(SQL_amount_col);
            while (rs.next()) {
                int pid = rs.getInt(1);
                int amount = rs.getInt(2);
                productPrices.put(pid, amount);
            }
            int[][] prices = new int[show_num_row][show_num_col];
            SQL_amount_cell = "select u.state, s.pid, sum(s.quantity*s.price) from us_t u,ps_t p, sales s where s.uid=u.id and s.pid=p.id group by u.state, s.pid;";
            rs = stmt.executeQuery(SQL_amount_cell);
            while (rs.next()) {
                int sid = rs.getInt(1);
                int pid = rs.getInt(2);
                int amount = rs.getInt(3);
                prices[statePosition.get(sid)][productPosition.get(pid)] = amount;
            }
            long end = System.currentTimeMillis();
            long time_taken = end-start;
            return new Analytics(0, maxProduct, maxState, productPrices, null, statePrices, products, null, states,
                    prices, time_taken);
        } catch (SQLException e) {
            printError(out, e);
            return new Analytics();
        } finally {
            try {
                stmt.close();
                stmt2.close();
                conn.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private static void printError(JspWriter out, Exception e) {
        try {
            out.println(HelperUtils.printError("Internal Server Error. This shouldn't happen : "
                    + e.getLocalizedMessage()));
        } catch (Exception e2) {
            System.out.println(e2.getLocalizedMessage());
        }
    }
}

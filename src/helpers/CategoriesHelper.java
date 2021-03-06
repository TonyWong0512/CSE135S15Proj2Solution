package helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import models.CategoryWithCount;

public class CategoriesHelper {

    public static List<CategoryWithCount> listCategories() {
        try {
            return CategoryWithCount.getCategoriesWithCount();
        } catch (Exception e) {
            System.err.println("Some error happened!<br/>" + e.getLocalizedMessage());
            return new ArrayList<CategoryWithCount>();
        }
    }

    public static List<CategoryWithCount> listCategoriesNoCount() {
        try {
            return CategoryWithCount.getCategories();
        } catch (Exception e) {
            System.err.println("Some error happened!<br/>" + e.getLocalizedMessage());
            return new ArrayList<CategoryWithCount>();
        }
    }

    public static String modifyCategories(HttpServletRequest request) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = HelperUtils.connect();
            } catch (Exception e) {
                return HelperUtils.printError("Internal Server Error. This shouldn't happen.");
            }
            stmt = conn.createStatement();
            rs = null;
            String action = null, id_str = null;
            String name = "", description = "";
            try {
                action = request.getParameter("action");
            } catch (Exception e) {
                // No action means no modification thus no alert
                return "";
            }
            try {
                id_str = request.getParameter("id");
            } catch (Exception e) {
                id_str = null;
            }
            try {
                name = request.getParameter("name");
                description = request.getParameter("description");
            } catch (Exception e) {
                name = null;
                description = null;
            }
            if (("insert").equals(action)) {
                String SQL_1 = "INSERT INTO categories (name, description) VALUES('" + name + "','" + description
                        + "');";
                try {
                    conn.setAutoCommit(false);
                    stmt.execute(SQL_1);
                    conn.commit();
                    conn.setAutoCommit(true);
                    stmt.close();
                    conn.close();
                    return HelperUtils.printSuccess("Insertion successful");
                } catch (Exception e) {
                    return HelperUtils
                            .printError("Insert failed! Please <a href=\"categories\" target=\"_self\">insert it</a> again.<br/>"
                                    + e.getLocalizedMessage());
                }
            } else if (("update").equals(action)) {
                String SQL_2 = "update categories set name='" + name + "' , description='" + description
                        + "' where id=" + id_str + ";";
                try {
                    conn.setAutoCommit(false);
                    stmt.execute(SQL_2);
                    conn.commit();
                    conn.setAutoCommit(true);
                    return HelperUtils.printSuccess("Update successful.");
                } catch (Exception e) {
                    return HelperUtils
                            .printError("Updated Failed! Please <a href=\"categories\" target=\"_self\">Update it</a> again.<br/>"
                                    + e.getLocalizedMessage());
                }
            } else if (("delete").equals(action)) {
                String SQL_1 = "select count(*) from products where cid=" + id_str + ";";
                String SQL_2 = "delete from categories where id=" + id_str + ";";
                try {
                    // Check if number of products is 0 before trying to delete.
                    conn.setAutoCommit(false);
                    rs = stmt.executeQuery(SQL_1);
                    int count = -1;
                    while (rs.next()) {
                        count = rs.getInt(1);
                    }

                    if (count <= 0) {
                        stmt.execute(SQL_2);
                        conn.commit();
                        conn.setAutoCommit(true);
                        return HelperUtils.printSuccess("Deletion successful.");
                    } else {
                        return HelperUtils
                                .printError("You can't delete this category, there are still products in it. Delete them first before trying again.");
                    }
                } catch (Exception e) {
                    return HelperUtils
                            .printError("Deletion Failed! Please try again in the <a href=\"categories.jsp\" target=\"_self\">categories page</a>.<br><br>");
                }
            } else {
                // Wrong action means no modification thus no alert
                return "";
            }
        } catch (Exception e) {
            return HelperUtils.printError("Some error happened!<br/>" + e.getLocalizedMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

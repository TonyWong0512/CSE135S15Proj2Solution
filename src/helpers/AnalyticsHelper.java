package helpers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

import models.Product;
import models.State;
import models.User;

public class AnalyticsHelper {

    public enum Order {
        ALPHABETICAL,
        TOPK
    }

    public static ArrayList<User> listUsersInAlphabeticalOrder(JspWriter out) {
        try {
            return User.getUsersByOrder(Order.ALPHABETICAL);
        } catch (SQLException e) {
            printError(out, e);
            return new ArrayList<User>();
        }
    }

    public static ArrayList<State> listStatesInAlphabeticalOrder(JspWriter out) {
        try {
            return State.getStatesByOrder(Order.ALPHABETICAL);
        } catch (SQLException e) {
            printError(out, e);
            return new ArrayList<State>();
        }
    }

    public static ArrayList<User> listUsersInTopKOrder(JspWriter out) {
        try {
            return User.getUsersByOrder(Order.TOPK);
        } catch (SQLException e) {
            printError(out, e);
            return new ArrayList<User>();
        }
    }

    public static ArrayList<State> listStatesInTopKOrder(JspWriter out) {
        try {
            return State.getStatesByOrder(Order.TOPK);
        } catch (SQLException e) {
            printError(out, e);
            return new ArrayList<State>();
        }
    }

    public static ArrayList<Product> listProductsInTopKOrder(JspWriter out) {
        try {
            return Product.getProductsByOrder(Order.TOPK);
        } catch (SQLException e) {
            printError(out, e);
            return new ArrayList<Product>();
        }
    }

    public static ArrayList<Product> listProductsInAlphabeticalOrder(JspWriter out) {
        try {
            return Product.getProductsByOrder(Order.ALPHABETICAL);
        } catch (SQLException e) {
            printError(out, e);
            return new ArrayList<Product>();
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

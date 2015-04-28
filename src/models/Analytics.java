package models;

import java.util.HashMap;

public class Analytics {

    private int totalUserCount;

    private int totalProductCount;

    private int totalStateCount;

    private HashMap<Integer, Integer> productPrices = null;

    private HashMap<Integer, Integer> userPrices = null;

    private HashMap<Integer, Integer> statePrices = null;

    private HashMap<Integer, User> users = null;

    private HashMap<Integer, State> states = null;

    private HashMap<Integer, Product> products;

    private int prices[][];

    public Analytics() {

        this.userPrices = new HashMap<Integer, Integer>();
        this.statePrices = new HashMap<Integer, Integer>();
        this.productPrices = new HashMap<Integer, Integer>();
        this.products = new HashMap<Integer, Product>();
        this.states = new HashMap<Integer, State>();
        this.users = new HashMap<Integer, User>();
        this.prices = new int[20][10];
    }

    /**
     * @param totalUserCount
     * @param totalProductCount
     * @param totalStateCount
     * @param productPrices
     * @param userPrices
     * @param statePrices
     * @param users
     * @param states
     * @param products
     * @param prices
     */
    public Analytics(int totalUserCount, int totalProductCount, int totalStateCount,
            HashMap<Integer, Integer> productPrices, HashMap<Integer, Integer> userPrices,
            HashMap<Integer, Integer> statePrices, HashMap<Integer, Product> products, HashMap<Integer, User> users,
            HashMap<Integer, State> states, int[][] prices) {
        super();
        this.totalUserCount = totalUserCount;
        this.totalProductCount = totalProductCount;
        this.totalStateCount = totalStateCount;
        this.productPrices = productPrices;
        this.userPrices = userPrices;
        this.statePrices = statePrices;
        this.users = users;
        this.states = states;
        this.products = products;
        this.prices = prices;
    }

    /**
     * @return the totalUserCount
     */
    public int getTotalUserCount() {
        return totalUserCount;
    }

    /**
     * @return the totalProductCount
     */
    public int getTotalProductCount() {
        return totalProductCount;
    }

    /**
     * @return the totalStateCount
     */
    public int getTotalStateCount() {
        return totalStateCount;
    }

    /**
     * @return the productPrices
     */
    public HashMap<Integer, Integer> getProductPrices() {
        return productPrices;
    }

    /**
     * @return the userPrices
     */
    public HashMap<Integer, Integer> getUserPrices() {
        return userPrices;
    }

    /**
     * @return the statePrices
     */
    public HashMap<Integer, Integer> getStatePrices() {
        return statePrices;
    }

    /**
     * @return the users
     */
    public HashMap<Integer, User> getUsers() {
        return users;
    }

    /**
     * @return the states
     */
    public HashMap<Integer, State> getStates() {
        return states;
    }

    /**
     * @return the products
     */
    public HashMap<Integer, Product> getProducts() {
        return products;
    }

    /**
     * @return the prices
     */
    public int[][] getPrices() {
        return prices;
    }

}

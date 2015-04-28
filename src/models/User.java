/**
 * 
 */
package models;


/**
 * @author julestestard
 */
public class User {

    private int id;

    private String userName;

    private String role;

    private int age;

    private int stateId;

    /**
     * @param userName
     * @param role
     * @param age
     * @param stateId
     */
    public User(int id, String userName, String role, int age, int stateId) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.age = age;
        this.stateId = stateId;
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the stateId
     */
    public int getStateId() {
        return stateId;
    }

    /**
     * @param stateId
     *            the stateId to set
     */
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    //    public static ArrayList<User> getUsersByOrder(Order o) throws SQLException {
    //        try {
    //            Class.forName("org.postgresql.Driver");
    //        } catch (Exception e) {
    //            System.err.println("Internal Server Error. This shouldn't happen.");
    //            return new ArrayList<User>();
    //        }
    //        ArrayList<User> users = new ArrayList<User>();
    //        String url = "jdbc:postgresql://127.0.0.1:5432/cse135";
    //        String user = "postgres";
    //        String password = "postgres";
    //        Connection conn = DriverManager.getConnection(url, user, password);
    //        Statement stmt = conn.createStatement();
    //        String query = "SELECT * FROM users ORDER BY name LIMIT 20";
    //        ResultSet rs = stmt.executeQuery(query);
    //        while (rs.next()) {
    //            Integer id = rs.getInt(1);
    //            String name = rs.getString(2);
    //            String role = rs.getString(3);
    //            Integer age = rs.getInt(4);
    //            Integer state = rs.getInt(5);
    //            users.add(new User(id, name, role, age, state));
    //        }
    //        return users;
    //    }

}

package pl.pjatk.Management;

import pl.pjatk.Menu.Food;

import java.sql.*;

public class DataSource {
    public DataSource() {
        init();
    }

    public void init(){
        createTableOfFood();
    }

    public Connection open() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/kubat/OneDrive/Desktop/PJATK/GUI/ćwiczenia/Restaurant/restaurantdb.db");

            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return null;
        }
    }

    // CREATE TABLES

    public void createTableOfFood(){
        try (Connection conn = this.open();
             Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Food " +
                    " (idFood INTEGER PRIMARY KEY, Name TEXT, Description TEXT, Price REAL, isAvailable INTEGER)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD FOOD

    // CREATE

    public void insertFood(int idFood, String name, String description, double price, boolean isAvailable) {
        String sql = "INSERT INTO Food(idFood,Name,Description,Price,isAvailable) VALUES(?,?,?,?,?)";

        try (Connection conn = this.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFood);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.setDouble(4, price);
            pstmt.setBoolean(5, isAvailable);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // READ

    public void selectFood(){
        String sql = "SELECT idFood, Name, Description, Price FROM Food";

        try (Connection conn = this.open();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("idFood") + ". " +
                        rs.getString("Name") +  ", " +
                        ((getAvailablity(rs.getInt("idFood"))) ? rs.getDouble("Price") + "$" : "UNAVAILABLE") + "\n" +
                        rs.getString("Description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Food selectFoodById(int id){
        if(id!=0){
            String sql = "SELECT * FROM Food WHERE idFood = " + id;

            try (Connection conn = this.open();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql);) {

                String name = "";
                String description = "";
                double price = 0;

                while (rs.next()) {
                    name = rs.getString("Name");
                    description = rs.getString("Description");
                    price = rs.getDouble("Price");
                }

                return new Food(name, description, price);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Podałeś zły numer");
        }
        return null;
    }

    public int getNumberOfFood(){
        String sql = "SELECT MAX(idFood) FROM Food";

        try (Connection conn = this.open();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public boolean getAvailablity(int id){
        String sql = "SELECT isAvailable FROM Food WHERE idFood = ?";

        try (Connection conn = this.open();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){


            pstmt.setDouble(1,id);

            ResultSet rs  = pstmt.executeQuery();

            return rs.getBoolean("isAvailable");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    // UPDATE

    public void updateAvailability(int id){
        String sql;
        if(getAvailablity(id)){
            sql = "UPDATE Food SET isAvailable = 0 WHERE idFood = ?";
        } else {
            sql = "UPDATE Food SET isAvailable = 1 WHERE idFood = ?";
        }


        try (Connection conn = this.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // DELETE

    public void deleteFood(int id) {
        String sql = "DELETE FROM Food WHERE idFood = ?";

        try (Connection conn = this.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

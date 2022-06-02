package pl.pjatk.Menu;

import java.sql.*;

public class FoodDataSource {
    public Connection open() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/kubat/OneDrive/Desktop/PJATK/GUI/ćwiczenia/Restaurant/restaurantdb.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Food " +
                    " (idFood INTEGER, Name TEXT, Description TEXT, Price REAL, isAvailable INTEGER)");
            statement.close();

            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return null;
        }
    }

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
}

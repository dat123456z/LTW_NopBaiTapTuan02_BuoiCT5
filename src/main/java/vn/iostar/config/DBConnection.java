package vn.iostar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private final String host = "DAVIDDET";   // KHÔNG kèm \SQLEXPRESS
    private final int port = 1433;            // Đặt đúng cổng thực tế
    private final String dbName = "SV";
    private final String userID = "sa";
    private final String password = "123456";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + host + ":" + port
                   + ";databaseName=" + dbName
                   + ";encrypt=true;trustServerCertificate=true"; 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    public static void main(String[] args) {
        try (Connection conn = new DBConnection().getConnection()) {
        	// crate statement
        	Statement stmt = conn.createStatement();
      
        	stmt.executeUpdate("INSERT INTO SV(id, username, email) VALUES (1, 'Dat', 'dat@gmail.com')");
        
        	ResultSet rs = stmt.executeQuery("SELECT * FROM SV");
        	// show data
        	while (rs.next()) {
        		 System.out.println(
        	                rs.getInt("id") + " " +
        	                rs.getString("username") + " " +
        	                rs.getString("email")
        	            );
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

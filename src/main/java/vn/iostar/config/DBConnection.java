package vn.iostar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private final String host = "DAVIDDET";   
    private final int port = 1433;           
    private final String dbName = "LTW";
    private final String userID = "sa";
    private final String password = "123456";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + host + ":" + port
                   + ";databaseName=" + dbName
                   + ";encrypt=true;trustServerCertificate=true"; 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

  
}

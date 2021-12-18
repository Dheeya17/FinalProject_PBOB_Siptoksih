package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseModel {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    public Connection conn;


    public BaseModel(){
        dbUrl = "jdbc:postgresql://localhost/Siptoksih";
        dbUser = "Siptoksih";
        dbPassword = "Siptoksih";


        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());
        }
    }
}

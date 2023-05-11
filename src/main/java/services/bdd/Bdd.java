package services.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
    public static Connection getBdd() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/toutdouxlist","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

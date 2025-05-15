import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String DB_URL = "jdbc:mysql://localhost:3306/leave_management";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("DB connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}

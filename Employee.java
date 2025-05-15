import java.sql.*;
import java.util.Scanner;

public class Employee {
    private Scanner sc = new Scanner(System.in);

    public void register() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "INSERT INTO employees (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();

            System.out.println("✅ Registration successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int login() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "SELECT id FROM employees WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Login successful.");
                return rs.getInt("id");
            } else {
                System.out.println("❌ Invalid credentials.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void applyLeave(int empId) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("From Date (YYYY-MM-DD): ");
            String from = sc.nextLine();
            System.out.print("To Date (YYYY-MM-DD): ");
            String to = sc.nextLine();
            System.out.print("Reason: ");
            String reason = sc.nextLine();

            String sql = "INSERT INTO leave_requests (employee_id, from_date, to_date, reason) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empId);
            stmt.setString(2, from);
            stmt.setString(3, to);
            stmt.setString(4, reason);
            stmt.executeUpdate();

            System.out.println("✅ Leave request submitted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewLeaves(int empId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM leave_requests WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Leave ID: " + rs.getInt("id"));
                System.out.println("From: " + rs.getDate("from_date"));
                System.out.println("To: " + rs.getDate("to_date"));
                System.out.println("Reason: " + rs.getString("reason"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

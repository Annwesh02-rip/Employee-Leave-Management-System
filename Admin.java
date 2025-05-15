import java.sql.*;
import java.util.Scanner;

public class Admin {
    private Scanner sc = new Scanner(System.in);

    public void viewAllRequests() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT l.id, e.name, l.from_date, l.to_date, l.reason, l.status FROM leave_requests l JOIN employees e ON l.employee_id = e.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Leave ID: " + rs.getInt("id"));
                System.out.println("Employee: " + rs.getString("name"));
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

    public void updateStatus() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Leave Request ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Approve or Reject? (A/R): ");
            String status = sc.nextLine().equalsIgnoreCase("A") ? "Approved" : "Rejected";

            String sql = "UPDATE leave_requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("✅ Leave status updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

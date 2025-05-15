import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee emp = new Employee();
        Admin admin = new Admin();

        while (true) {
            System.out.println("\n=== Employee Leave Management ===");
            System.out.println("1. Register as Employee");
            System.out.println("2. Employee Login");
            System.out.println("3. Admin Panel");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    emp.register();
                    break;
                case 2:
                    int empId = emp.login();
                    if (empId != -1) {
                        while (true) {
                            System.out.println("1. Apply for Leave");
                            System.out.println("2. View My Leave Requests");
                            System.out.println("3. Logout");
                            System.out.print("Choose: ");
                            int ch = Integer.parseInt(sc.nextLine());
                            if (ch == 1) emp.applyLeave(empId);
                            else if (ch == 2) emp.viewLeaves(empId);
                            else break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Admin Access:");
                    admin.viewAllRequests();
                    admin.updateStatus();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}

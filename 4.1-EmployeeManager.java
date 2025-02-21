import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class EmployeeManager {
    private ArrayList<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee Added Successfully.");
    }

    public void updateEmployee() {
        System.out.print("Enter Employee ID to Update: ");
        int id = scanner.nextInt();
        boolean found = false;

        for (Employee emp : employees) {
            if (emp.id == id) {
                found = true;
                scanner.nextLine();
                System.out.print("Enter New Name: ");
                emp.name = scanner.nextLine();
                System.out.print("Enter New Salary: ");
                emp.salary = scanner.nextDouble();
                System.out.println("Employee Updated Successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Employee Not Found.");
        }
    }

    public void removeEmployee() {
        System.out.print("Enter Employee ID to Remove: ");
        int id = scanner.nextInt();
        boolean removed = employees.removeIf(emp -> emp.id == id);
        if (removed) {
            System.out.println("Employee Removed Successfully.");
        } else {
            System.out.println("Employee Not Found.");
        }
    }

    public void searchEmployee() {
        System.out.print("Enter Employee ID to Search: ");
        int id = scanner.nextInt();
        boolean found = false;

        for (Employee emp : employees) {
            if (emp.id == id) {
                found = true;
                System.out.println("Employee Found: " + emp);
                break;
            }
        }

        if (!found) {
            System.out.println("Employee Not Found.");
        }
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No Employees to Display.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.addEmployee();
                    break;
                case 2:
                    manager.updateEmployee();
                    break;
                case 3:
                    manager.removeEmployee();
                    break;
                case 4:
                    manager.searchEmployee();
                    break;
                case 5:
                    manager.displayEmployees();
                    break;
                case 6:
                    System.out.println("Exiting Program...");
                    return;
                default:
                    System.out.println("Invalid Choice. Please Try Again.");
            }
        }
    }
}

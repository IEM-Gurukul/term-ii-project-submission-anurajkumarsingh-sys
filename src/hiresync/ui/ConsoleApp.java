package hiresync.ui;

import hiresync.models.Company;
import hiresync.models.JobProfile;
import hiresync.models.Student;
import hiresync.services.PlacementCoordinator;
import java.util.List;
import java.util.Scanner;

/**
 * Main console application for HireSync: University Placement Management System.
 */
public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PlacementCoordinator coordinator = PlacementCoordinator.getInstance();

    public static void main(String[] args) {
        System.out.println("=== Welcome to HireSync: University Placement Management System ===");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> registerCompany();
                case 3 -> postJobProfile();
                case 4 -> viewEligibleStudents();
                case 5 -> viewAllStudents();
                case 6 -> viewAllCompanies();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting HireSync. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Register Student");
        System.out.println("2. Register Company");
        System.out.println("3. Post Job Profile (Existing Company)");
        System.out.println("4. View Eligible Students for a Job");
        System.out.println("5. View All Students");
        System.out.println("6. View All Companies");
        System.out.println("0. Exit");
    }

    private static void registerStudent() {
        System.out.println("\n--- Register Student ---");
        String id = getStringInput("Enter Student ID: ");
        String name = getStringInput("Enter Name: ");
        String email = getStringInput("Enter Email: ");
        String password = getStringInput("Enter Password: ");
        double cgpa = getDoubleInput("Enter CGPA: ");
        int backlogs = getIntInput("Enter Number of Backlogs: ");
        String dept = getStringInput("Enter Department: ");

        Student student = new Student(id, name, email, password, cgpa, backlogs, dept);
        coordinator.registerStudent(student);
        System.out.println("Student registered successfully!");
    }

    private static void registerCompany() {
        System.out.println("\n--- Register Company ---");
        String id = getStringInput("Enter Company ID: ");
        String name = getStringInput("Enter Company Name: ");
        String email = getStringInput("Enter Email: ");
        String password = getStringInput("Enter Password: ");
        String industry = getStringInput("Enter Industry: ");

        Company company = new Company(id, name, email, password, industry);
        coordinator.registerCompany(company);
        System.out.println("Company registered successfully!");
    }

    private static void postJobProfile() {
        System.out.println("\n--- Post Job Profile ---");
        List<Company> companies = coordinator.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies registered yet.");
            return;
        }

        for (int i = 0; i < companies.size(); i++) {
            System.out.println((i + 1) + ". " + companies.get(i).getName());
        }
        int compIndex = getIntInput("Select Company: ") - 1;

        if (compIndex >= 0 && compIndex < companies.size()) {
            Company company = companies.get(compIndex);
            String jobId = getStringInput("Enter Job ID: ");
            String title = getStringInput("Enter Job Title: ");
            String desc = getStringInput("Enter Description: ");
            double minCgpa = getDoubleInput("Enter Minimum CGPA: ");
            int maxBacklogs = getIntInput("Enter Maximum Backlogs: ");
            double salary = getDoubleInput("Enter Salary (LPA): ");

            JobProfile profile = new JobProfile(jobId, title, desc, minCgpa, maxBacklogs, salary);
            company.addJobProfile(profile);
            System.out.println("Job Profile posted successfully for " + company.getName());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void viewEligibleStudents() {
        System.out.println("\n--- View Eligible Students ---");
        List<Company> companies = coordinator.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies registered.");
            return;
        }

        for (int i = 0; i < companies.size(); i++) {
            System.out.println((i + 1) + ". " + companies.get(i).getName());
        }
        int compIndex = getIntInput("Select Company: ") - 1;

        if (compIndex >= 0 && compIndex < companies.size()) {
            Company company = companies.get(compIndex);
            List<JobProfile> profiles = company.getJobProfiles();
            if (profiles.isEmpty()) {
                System.out.println("No job profiles for this company.");
                return;
            }

            for (int i = 0; i < profiles.size(); i++) {
                System.out.println((i + 1) + ". " + profiles.get(i).getTitle());
            }
            int profileIndex = getIntInput("Select Job Profile: ") - 1;

            if (profileIndex >= 0 && profileIndex < profiles.size()) {
                JobProfile profile = profiles.get(profileIndex);
                List<Student> eligible = coordinator.getEligibleStudents(profile);
                if (eligible.isEmpty()) {
                    System.out.println("No eligible students found for this profile.");
                } else {
                    System.out.println("Eligible Students:");
                    eligible.forEach(System.out::println);
                }
            }
        }
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Registered Students ---");
        List<Student> students = coordinator.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void viewAllCompanies() {
        System.out.println("\n--- All Registered Companies ---");
        List<Company> companies = coordinator.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies registered.");
        } else {
            companies.forEach(System.out::println);
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a decimal number.");
            scanner.next();
            System.out.print(prompt);
        }
        double val = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return val;
    }
}

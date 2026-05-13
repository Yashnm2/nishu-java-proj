package view;

import backend.Student;
import controller.AuthController;
import java.util.Scanner;

public class LoginView extends BaseView {
    private final AuthController authController;

    public LoginView(AuthController authController, Scanner scanner) {
        super(scanner);
        this.authController = authController;
    }

    public Student promptLogin() {
        System.out.println("=== Student Login ===");
        while (true) {
            String id       = readLine("Student ID : ");
            String password = readLine("Password   : ");

            Student student = authController.login(id, password);
            if (student != null) {
                System.out.println("Welcome, " + student.getName() + "!\n");
                return student;
            }
            System.out.println("Invalid credentials. Please try again.\n");
        }
    }
}

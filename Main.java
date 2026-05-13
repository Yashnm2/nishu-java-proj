import backend.Student;
import controller.AuthController;
import controller.CCA_EventController;
import view.CCA_EventView;
import view.LoginView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthController authController = new AuthController();
        CCA_EventController eventController = new CCA_EventController();
        LoginView loginView = new LoginView(authController, scanner);
        CCA_EventView eventView = new CCA_EventView(eventController, authController, scanner);

        System.out.println("=== Student CCA Management System ===\n");
        System.out.println("Demo accounts:  S001 / pass123   |   S002 / pass456\n");

        while (true) {
            Student student = loginView.promptLogin();
            eventView.show(student);
        }
    }
}

package view;

import backend.CCA_Event;
import backend.Student;
import controller.AuthController;
import controller.CCA_EventController;
import java.util.List;
import java.util.Scanner;

public class CCA_EventView extends BaseView {
    private final CCA_EventController eventController;
    private final AuthController authController;

    public CCA_EventView(CCA_EventController eventController, AuthController authController, Scanner scanner) {
        super(scanner);
        this.eventController = eventController;
        this.authController = authController;
    }

    public void show(Student student) {
        while (true) {
            System.out.println("=== CCA Event Manager (" + student.getName() + ") ===");
            System.out.println("1. View all CCA events");
            System.out.println("2. Add CCA event");
            System.out.println("3. Edit CCA event");
            System.out.println("4. Delete CCA event");
            System.out.println("5. Logout");

            switch (readLine("Choice: ")) {
                case "1": viewAll(student);  break;
                case "2": add(student);      break;
                case "3": edit(student);     break;
                case "4": delete(student);   break;
                case "5":
                    authController.logout();
                    System.out.println("Logged out.\n");
                    return;
                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }

    private void viewAll(Student student) {
        List<CCA_Event> events = eventController.getAll(student);
        System.out.println();
        if (events.isEmpty()) {
            System.out.println("No CCA events found.");
        } else {
            events.forEach(System.out::println);
        }
        System.out.println();
    }

    private void add(Student student) {
        System.out.println("\n-- Add CCA Event --");
        String name        = readLine("Event name       : ");
        String date        = readLine("Date (DD/MM/YYYY): ");
        String role        = readLine("Role             : ");
        String description = readLine("Description      : ");
        int hours          = parseInt(readLine("Hours            : "), 0);
        int sealPoints     = parseInt(readLine("SEAL Points      : "), 0);

        CCA_Event event = eventController.add(student, name, date, role, description, hours, sealPoints);
        System.out.println("Added: " + event + "\n");
    }

    private void edit(Student student) {
        viewAll(student);
        int id = parseInt(readLine("Enter event ID to edit: "), -1);

        CCA_Event existing = student.findCCAEventById(id);
        if (existing == null) {
            System.out.println("Event not found.\n");
            return;
        }

        System.out.println("(Leave blank to keep the current value)");
        String name       = readLine("Event name [" + existing.getName() + "]: ");
        String date       = readLine("Date       [" + existing.getDate() + "]: ");
        String role       = readLine("Role       [" + existing.getRole() + "]: ");
        String hoursInput = readLine("Hours      [" + existing.getHours() + "]: ");

        boolean updated = eventController.edit(
            student, id,
            name.isEmpty()       ? existing.getName()  : name,
            date.isEmpty()       ? existing.getDate()  : date,
            role.isEmpty()       ? existing.getRole()  : role,
            hoursInput.isEmpty() ? existing.getHours() : parseInt(hoursInput, existing.getHours())
        );
        System.out.println(updated ? "Event updated.\n" : "Update failed.\n");
    }

    private void delete(Student student) {
        viewAll(student);
        int id = parseInt(readLine("Enter event ID to delete: "), -1);
        System.out.println(eventController.delete(student, id) ? "Event deleted.\n" : "Event not found.\n");
    }
}

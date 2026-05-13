package controller;

import backend.CCA_Event;
import backend.Student;
import java.util.HashMap;
import java.util.Map;

public class AuthController {
    private Map<String, Student> studentStore = new HashMap<>();
    private Student currentStudent = null;

    public AuthController() {
        // Seed two demo accounts
        Student alice = new Student("Alice", "S001", "pass123");
        alice.addCCAEvent(new CCA_Event("Robotics Club Meet", "10/01/2025", "Member", "Ongoing", 2, 50));
        studentStore.put("S001", alice);
        studentStore.put("S002", new Student("Bob", "S002", "pass456"));
    }

    public Student login(String studentId, String password) {
        Student student = studentStore.get(studentId);
        if (student != null && student.getPassword().equals(password)) {
            currentStudent = student;
            return student;
        }
        return null;
    }

    public void logout() {
        currentStudent = null;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public boolean isLoggedIn() {
        return currentStudent != null;
    }
}

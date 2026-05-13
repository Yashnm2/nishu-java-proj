package backend;

import java.util.ArrayList;
import java.util.List;

public class Student implements Authenticatable {
    private String name;
    private String studentId;
    private String password;
    private List<CCA_Event> ccaEvents;

    public Student(String name, String studentId, String password) {
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.ccaEvents = new ArrayList<>();
    }

    public String getName() { return name; }
    @Override public String getId() { return studentId; }
    @Override public String getPassword() { return password; }

    public void setName(String name) { this.name = name; }
    public void setId(String studentId) { this.studentId = studentId; }
    public void setPassword(String password) { this.password = password; }

    public List<CCA_Event> getAllCCAEvents() { return ccaEvents; }

    public void addCCAEvent(CCA_Event event) {
        ccaEvents.add(event);
    }

    public boolean removeCCAEvent(int id) {
        for (int i = 0; i < ccaEvents.size(); i++) {
            if (ccaEvents.get(i).getId() == id) {
                ccaEvents.remove(i);
                return true;
            }
        }
        return false;
    }

    public CCA_Event findCCAEventById(int id) {
        return ccaEvents.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

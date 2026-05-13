package controller;

import backend.CCA_Event;
import backend.Student;
import java.util.List;

public class CCA_EventController implements CRUDController<CCA_Event> {

    @Override
    public List<CCA_Event> getAll(Student student) {
        return student.getAllCCAEvents();
    }

    public CCA_Event add(Student student, String name, String date, String role,
                         String description, int hours, int sealPoints) {
        CCA_Event event = new CCA_Event(name, date, role, description, hours, sealPoints);
        student.addCCAEvent(event);
        return event;
    }

    public boolean edit(Student student, int id, String name, String date, String role, int hours) {
        CCA_Event event = student.findCCAEventById(id);
        if (event == null) return false;
        event.setName(name);
        event.setDate(date);
        event.setRole(role);
        event.setHours(hours);
        return true;
    }

    @Override
    public boolean delete(Student student, int id) {
        return student.removeCCAEvent(id);
    }
}

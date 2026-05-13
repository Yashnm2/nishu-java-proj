package controller;

import backend.Student;
import java.util.List;

public interface CRUDController<T> {
    List<T> getAll(Student student);
    boolean delete(Student student, int id);
}

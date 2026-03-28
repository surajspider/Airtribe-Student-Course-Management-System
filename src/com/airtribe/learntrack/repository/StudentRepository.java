package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public void add(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }
    
    // Deactivate a student instead of deleting
    public boolean deactivateStudent(int id) {
        Optional<Student> studentOpt = findById(id);
        if (studentOpt.isPresent()) {
            studentOpt.get().setActive(false);
            return true;
        }
        return false;
    }
}

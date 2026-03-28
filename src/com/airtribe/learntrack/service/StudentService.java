package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        Student newStudent = new Student(IdGenerator.getNextStudentId(), firstName, lastName, email, batch);
        studentRepository.add(newStudent);
        return newStudent;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws EntityNotFoundException {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isEmpty()) {
            throw new EntityNotFoundException("Student with ID " + id + " not found.");
        }
        return studentOpt.get();
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        boolean success = studentRepository.deactivateStudent(id);
        if (!success) {
            throw new EntityNotFoundException("Student with ID " + id + " not found. Cannot deactivate.");
        }
    }
}

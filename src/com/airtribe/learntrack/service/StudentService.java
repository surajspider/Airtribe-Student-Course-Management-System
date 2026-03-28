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

    // Method Overloading Example 1: Update only the batch
    public Student updateStudent(int id, String newBatch) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setBatch(newBatch);
        return student;
    }

    // Method Overloading Example 2: Update multiple fields
    public Student updateStudent(int id, String firstName, String lastName, String batch) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setBatch(batch);
        return student;
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        boolean success = studentRepository.deactivateStudent(id);
        if (!success) {
            throw new EntityNotFoundException("Student with ID " + id + " not found. Cannot deactivate.");
        }
    }
}

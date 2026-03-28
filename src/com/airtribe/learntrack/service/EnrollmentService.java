package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;
import java.util.Optional;

public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentService studentService, CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) throws EntityNotFoundException, InvalidInputException {
        // Validate student
        Student student = studentService.getStudentById(studentId);
        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student.");
        }

        // Validate course
        Course course = courseService.getCourseById(courseId);
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course.");
        }

        Enrollment enrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId);
        enrollmentRepository.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) throws EntityNotFoundException {
        // Validate student exists
        studentService.getStudentById(studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }
    
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus newStatus) throws EntityNotFoundException {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findById(enrollmentId);
        if (enrollmentOpt.isEmpty()) {
            throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
        }
        enrollmentOpt.get().setStatus(newStatus);
    }
}

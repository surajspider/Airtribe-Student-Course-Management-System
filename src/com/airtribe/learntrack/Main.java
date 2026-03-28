package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.*;
import com.airtribe.learntrack.service.*;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;
    private static Scanner scanner;

    public static void main(String[] args) {
        // TODO: Replace these in-memory repositories with an actual Database (like MySQL) in later modules
        // Initialize dependencies
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(enrollmentRepository, studentService, courseService);

        scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to LearnTrack - Student & Course Management System");

        while (!exit) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            int choice = -1;
            
            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {
                    case MenuOptions.ADD_STUDENT:
                        handleAddStudent();
                        break;
                    case MenuOptions.VIEW_STUDENTS:
                        handleViewStudents();
                        break;
                    case MenuOptions.SEARCH_STUDENT:
                        handleSearchStudent();
                        break;
                    case MenuOptions.DEACTIVATE_STUDENT:
                        handleDeactivateStudent();
                        break;
                    case MenuOptions.ADD_COURSE:
                        handleAddCourse();
                        break;
                    case MenuOptions.VIEW_COURSES:
                        handleViewCourses();
                        break;
                    case MenuOptions.TOGGLE_COURSE_STATUS:
                        handleToggleCourseStatus();
                        break;
                    case MenuOptions.ENROLL_STUDENT:
                        handleEnrollStudent();
                        break;
                    case MenuOptions.VIEW_ENROLLMENTS:
                        handleViewEnrollments();
                        break;
                    case MenuOptions.UPDATE_ENROLLMENT_STATUS:
                        handleUpdateEnrollmentStatus();
                        break;
                    case MenuOptions.EXIT:
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid menu option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println(MenuOptions.ADD_STUDENT + ". Add new student");
        System.out.println(MenuOptions.VIEW_STUDENTS + ". View all students");
        System.out.println(MenuOptions.SEARCH_STUDENT + ". Search student by ID");
        System.out.println(MenuOptions.DEACTIVATE_STUDENT + ". Deactivate a student");
        System.out.println(MenuOptions.ADD_COURSE + ". Add new course");
        System.out.println(MenuOptions.VIEW_COURSES + ". View all courses");
        System.out.println(MenuOptions.TOGGLE_COURSE_STATUS + ". Activate/Deactivate a course");
        System.out.println(MenuOptions.ENROLL_STUDENT + ". Enroll a student in a course");
        System.out.println(MenuOptions.VIEW_ENROLLMENTS + ". View enrollments for a student");
        System.out.println(MenuOptions.UPDATE_ENROLLMENT_STATUS + ". Mark enrollment as completed/cancelled");
        System.out.println(MenuOptions.EXIT + ". Exit");
    }

    private static void handleAddStudent() throws InvalidInputException {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        InputValidator.validateStringNotEmpty(firstName, "First Name");
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        InputValidator.validateStringNotEmpty(lastName, "Last Name");

        System.out.print("Enter Email (Optional, press Enter to skip): ");
        String email = scanner.nextLine();

        System.out.print("Enter Batch: ");
        String batch = scanner.nextLine();
        InputValidator.validateStringNotEmpty(batch, "Batch");

        Student student;
        if (email.trim().isEmpty()) {
            student = studentService.addStudent(firstName, lastName, "N/A", batch);
        } else {
            student = studentService.addStudent(firstName, lastName, email, batch);
        }
        System.out.println("Student added successfully: " + student);
    }

    private static void handleViewStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void handleSearchStudent() throws EntityNotFoundException {
        System.out.print("Enter Student ID to search: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Student student = studentService.getStudentById(id);
        System.out.println("Student found: " + student);
    }

    private static void handleDeactivateStudent() throws EntityNotFoundException {
        System.out.print("Enter Student ID to deactivate: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        studentService.deactivateStudent(id);
        System.out.println("Student with ID " + id + " has been deactivated.");
    }

    private static void handleAddCourse() throws InvalidInputException {
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        InputValidator.validateStringNotEmpty(name, "Course Name");

        System.out.print("Enter Course Description: ");
        String desc = scanner.nextLine();
        InputValidator.validateStringNotEmpty(desc, "Course Description");

        System.out.print("Enter Duration (in weeks): ");
        int duration = Integer.parseInt(scanner.nextLine().trim());
        InputValidator.validatePositiveInteger(duration, "Duration");

        Course course = courseService.addCourse(name, desc, duration);
        System.out.println("Course added successfully: " + course);
    }

    private static void handleViewCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("--- Course List ---");
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    private static void handleToggleCourseStatus() throws EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter status (true for Active, false for Inactive): ");
        boolean status = Boolean.parseBoolean(scanner.nextLine().trim());
        courseService.toggleCourseStatus(id, status);
        System.out.println("Course with ID " + id + " status updated to " + (status ? "Active" : "Inactive") + ".");
    }

    private static void handleEnrollStudent() throws EntityNotFoundException, InvalidInputException {
        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine().trim());

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrollment successful: " + enrollment);
    }

    private static void handleViewEnrollments() throws EntityNotFoundException {
        System.out.print("Enter Student ID (Enter 0 for all enrollments): ");
        int studentId = Integer.parseInt(scanner.nextLine().trim());
        
        List<Enrollment> enrollments;
        if (studentId == 0) {
            enrollments = enrollmentService.getAllEnrollments();
        } else {
            enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
        }

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        System.out.println("--- Enrollment List ---");
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
    }

    private static void handleUpdateEnrollmentStatus() throws EntityNotFoundException, InvalidInputException {
        System.out.print("Enter Enrollment ID: ");
        int enrollmentId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter new status (ACTIVE, COMPLETED, CANCELLED): ");
        String statusStr = scanner.nextLine().trim().toUpperCase();
        
        EnrollmentStatus newStatus;
        try {
            newStatus = EnrollmentStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid status provided. Use ACTIVE, COMPLETED, or CANCELLED.");
        }

        enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus);
        System.out.println("Enrollment ID " + enrollmentId + " status updated to " + newStatus + ".");
    }
}

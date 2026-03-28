package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;
import java.util.Optional;

public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, String description, int duration) {
        Course newCourse = new Course(IdGenerator.getNextCourseId(), name, description, duration);
        courseRepository.add(newCourse);
        return newCourse;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) throws EntityNotFoundException {
        Optional<Course> courseOpt = courseRepository.findById(id);
        if (courseOpt.isEmpty()) {
            throw new EntityNotFoundException("Course with ID " + id + " not found.");
        }
        return courseOpt.get();
    }

    public void toggleCourseStatus(int id, boolean status) throws EntityNotFoundException {
        boolean success = courseRepository.toggleCourseActiveStatus(id, status);
        if (!success) {
            throw new EntityNotFoundException("Course with ID " + id + " not found. Cannot update status.");
        }
    }
}

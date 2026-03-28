package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public void add(Course course) {
        courses.add(course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
    
    public boolean toggleCourseActiveStatus(int id, boolean status) {
        Optional<Course> courseOpt = findById(id);
        if (courseOpt.isPresent()) {
            courseOpt.get().setActive(status);
            return true;
        }
        return false;
    }
}

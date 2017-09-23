package com.staslabs.xstas.data.repositories;

import com.staslabs.xstas.data.entity.Course;
import com.staslabs.xstas.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getById(long id);
    List<Course> getByTeacher(User teacher);
}

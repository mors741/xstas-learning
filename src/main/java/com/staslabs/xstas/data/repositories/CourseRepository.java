package com.staslabs.xstas.data.repositories;

import com.staslabs.xstas.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

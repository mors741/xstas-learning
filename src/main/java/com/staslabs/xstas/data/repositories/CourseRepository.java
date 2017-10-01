package com.staslabs.xstas.data.repositories;

import com.staslabs.xstas.data.entity.Course;
import com.staslabs.xstas.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getById(long id);
    List<Course> getByTeacher(User teacher);

    @Query("select c from Course c where c.teacher.email = :teacherEmail")
    List<Course> getByTeacherEmail(@Param("teacherEmail") String teacherEmail);
}

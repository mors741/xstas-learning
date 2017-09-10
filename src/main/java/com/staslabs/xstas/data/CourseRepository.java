package com.staslabs.xstas.data;

import com.staslabs.xstas.data.entity.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> getPopular(int count);

    Course findOne(long id);

    List<Course> find(int categoryId, String filter, int level, String price, String sort);
}

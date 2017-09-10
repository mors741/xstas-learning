package com.staslabs.xstas.data;

import com.staslabs.xstas.data.entity.Course;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class CourseRepositoryImpl implements CourseRepository {

    private List<Course> courses = Arrays.asList(
            new Course(0, 1, "Java programming", BigDecimal.valueOf(12), 1),
            new Course(1, 1, "Matlab computing", BigDecimal.ZERO, 2),
            new Course(2, 2, "Kebab inc", BigDecimal.ZERO, 1),
            new Course(3, 4, "50 shades", BigDecimal.valueOf(100), 3)
    );

    @Override
    public List<Course> getPopular(int count) {
        if (count < courses.size()) {
            return courses.subList(0, count);
        } else {
            return courses;
        }
    }

    @Override
    public Course findOne(long id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> find(int categoryId, String filter, int level, String price, String sort) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (categoryId == 0 || categoryId == course.getCategoryId()) {
                if (filter == null || course.getName().contains(filter)) {
                    if (level == 0 || course.getLevel() == level) {
                        if (price == null
                                || (price.equals("free") && course.getPrice().compareTo(BigDecimal.ZERO) == 0)
                                || (price.equals("paid") && course.getPrice().compareTo(BigDecimal.ZERO) != 0)) {
                            result.add(course);
                        }
                    }
                }
            }
        }
        return result;
    }
}

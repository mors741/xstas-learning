package com.staslabs.xstas.web;

import com.staslabs.xstas.data.entity.Course;
import com.staslabs.xstas.data.entity.State;
import com.staslabs.xstas.data.entity.User;
import com.staslabs.xstas.data.repositories.CourseRepository;
import com.staslabs.xstas.data.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(path = {"/teacher"})
public class TeacherController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public TeacherController(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String showDashboard(Model model, Principal principal) {
        model.addAttribute(courseRepository.getByTeacherEmail(principal.getName()));
        return "teacher/dashboard";
    }

    @RequestMapping(path = "/new-course", method = RequestMethod.GET)
    public String showNewCourse(Model model) {
        model.addAttribute(new Course());
        return "teacher/new-course";
    }

    @RequestMapping(path = "/new-course", method = RequestMethod.POST)
    public String createNewCourse(Model model, Course course, Principal principal) {
        course.setState(State.NOT_PUBLISHED);
        User teacher = userRepository.getByEmail(principal.getName());
        course.setTeacher(teacher);
        Course savedCourse = courseRepository.save(course);
        return "redirect:/teacher/course/" + savedCourse.getId();
    }

    @RequestMapping(path = "/course/{id}", method = RequestMethod.GET)
    public String showEditCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute(courseRepository.getById(id));
        return "teacher/course";
    }
}

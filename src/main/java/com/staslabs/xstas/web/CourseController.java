package com.staslabs.xstas.web;

import com.staslabs.xstas.data.repositories.CourseRepository;
import com.staslabs.xstas.data.dict.CategoryDictionary;
import com.staslabs.xstas.data.dict.CourseLevelDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/course")
public class CourseController {

    private CourseRepository courseRepository;
    private CategoryDictionary categoryDictionary;
    private CourseLevelDictionary levelDictionary;

    @Autowired
    public CourseController(CourseRepository courseRepository, CategoryDictionary categoryDictionary, CourseLevelDictionary levelDictionary) {
        this.courseRepository = courseRepository;
        this.categoryDictionary = categoryDictionary;
        this.levelDictionary = levelDictionary;
    }

    @RequestMapping(path = "/search/{category}", method = RequestMethod.GET)
    public String findCourses(
            @PathVariable("category") String category,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "level", required = false) String level,
            @RequestParam(name = "price", required = false) String price,
            @RequestParam(name = "sort", required = false) String sort,
            Model model) {
        int categoryId = categoryDictionary.getId(category);
        int levelId = levelDictionary.getId(level);
//        model.addAttribute(courseRepository.find(categoryId, filter, levelId, price, sort));
        return "searchCourses";
    }

    @RequestMapping(path = "/{courseId}", method = RequestMethod.GET)
    public String showDetailInfo(@PathVariable("courseId") long courseId, Model model) {
        model.addAttribute(courseRepository.findOne(courseId));
        return "course";
    }
}

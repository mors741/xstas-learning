package com.staslabs.xstas.web;

import com.staslabs.xstas.data.repositories.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = {"/"})
public class HomeController {

    private final CourseRepository repository;

    public HomeController(CourseRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
//        model.addAttribute(repository.getPopular(12));
        return "home";
    }
}

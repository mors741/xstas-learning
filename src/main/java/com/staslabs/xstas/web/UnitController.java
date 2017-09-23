package com.staslabs.xstas.web;

import com.staslabs.xstas.data.entity.Format;
import com.staslabs.xstas.data.entity.Module;
import com.staslabs.xstas.data.entity.Unit;
import com.staslabs.xstas.data.entity.User;
import com.staslabs.xstas.data.repositories.CourseRepository;
import com.staslabs.xstas.data.repositories.ModuleRepository;
import com.staslabs.xstas.data.repositories.UnitRepository;
import com.staslabs.xstas.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/units")
public class UnitController {

    // TODO: trash there cause we don't know how course construction is designing
    private UnitRepository unitRepository;
    private ModuleRepository moduleRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;

    @Autowired
    public UnitController(UnitRepository unitRepository, ModuleRepository moduleRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.unitRepository = unitRepository;
        this.moduleRepository = moduleRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("unit", new Unit());
        List<Module> modules = moduleRepository.findByCourse(courseRepository.getById(1));
        model.addAttribute("modules", modules);
        model.addAttribute("formats", Format.values());
        return "units/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String processRegistration(Model model, @Valid Unit user, Errors errors) {
        if (errors.hasErrors()) {
            return "units/create";
        }
        unitRepository.save(user);
        return "redirect:/units/create";
    }

}

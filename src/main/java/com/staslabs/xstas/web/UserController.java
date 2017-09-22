package com.staslabs.xstas.web;

import com.staslabs.xstas.data.repositories.UserRepository;
import com.staslabs.xstas.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        return "registrationForm";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String processRegistration(Model model, @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "registrationForm";
        }
        repository.save(user);
        model.addAttribute("username", user.getEmail());
        return "redirect:/user/profile";
    }

    @RequestMapping("/profile")
    public String showUserProfile(Model model, Principal principal) {
        model.addAttribute(repository.getByEmail(principal.getName()));
        return "profile";
    }
}

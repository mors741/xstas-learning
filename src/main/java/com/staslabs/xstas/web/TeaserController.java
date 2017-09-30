package com.staslabs.xstas.web;

import com.staslabs.xstas.data.entity.Teaser;
import com.staslabs.xstas.data.entity.User;
import com.staslabs.xstas.data.repositories.TeaserRepository;
import com.staslabs.xstas.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/teasers")
public class TeaserController {

    private TeaserRepository teaserRepository;
    private UserRepository userRepository;

    @Autowired
    public TeaserController(TeaserRepository teaserRepository, UserRepository userRepository) {
        this.teaserRepository = teaserRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("teaser", new Teaser());
        return "teasers/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String crete(Model model, @Valid Teaser teaser, Errors errors) {
        if (errors.hasErrors()) {
            return "teasers/create";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User creator = userRepository.getByEmail(email);
        teaser.setCreator(creator);
        teaserRepository.save(teaser);
        return "redirect:/";
    }

    @RequestMapping(path = "/watch/{teaserId}", method = RequestMethod.GET)
    public String watch(@PathVariable long teaserId, Model model) {
        model.addAttribute("teaser", teaserRepository.findOne(teaserId));
        return "teasers/watch";
    }

    @RequestMapping(path = "/edit/{teaserId}", method = RequestMethod.GET)
    public String edit(@PathVariable long teaserId, Model model) {
        model.addAttribute("teaser", teaserRepository.findOne(teaserId));
        return "teasers/create";
    }
}

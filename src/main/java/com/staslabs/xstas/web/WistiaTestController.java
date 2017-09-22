package com.staslabs.xstas.web;

import com.staslabs.xstas.data.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/wistia")
public class WistiaTestController {

    private UnitRepository unitRepository;

    @Autowired
    public WistiaTestController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "wistia";
    }

    @RequestMapping(path = "/play/{unitId}", method = RequestMethod.GET)
    public String play(@PathVariable("unitId") long unitId, Model model) {
        model.addAttribute(unitRepository.findOne(unitId));
        return "play";
    }
}

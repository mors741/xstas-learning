package com.staslabs.xstas.web;

import com.staslabs.xstas.data.entity.Unit;
import com.staslabs.xstas.data.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "/save-unit", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String saveUnit(@RequestBody Unit unit) {
        unitRepository.save(unit);
        return "home";
    }
}

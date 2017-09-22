package com.staslabs.xstas.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/wistia")
public class WistiaTestController {


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "wistia";
    }
}

package org.bitnick.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChuckController {

    @RequestMapping(path = "/chuck/")
    public String chuck() {
        return "chuck";
    }
}

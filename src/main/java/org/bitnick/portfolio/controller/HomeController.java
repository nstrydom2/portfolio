package org.bitnick.portfolio.controller;

import org.bitnick.portfolio.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping(path = "/index")
    public String index(Model model) {
        Project project = new Project();
        project.setName("Test");
        project.setAppUrl("https://google.com/");
        project.setSourceUrl("https://google.com/");

        List<Project> projects = Arrays.asList(project, project, project, project);

        model.addAttribute("projects", projects);

        return "index";
    }
}

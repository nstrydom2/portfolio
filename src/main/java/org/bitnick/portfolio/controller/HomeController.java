package org.bitnick.portfolio.controller;

import org.bitnick.portfolio.model.Project;
import org.bitnick.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(path = "/")
    public String index(Model model) {
        List<Project> projects = projectService.getAllProjects();

        model.addAttribute("welcome", "Hi, I'm Nick, a Backend Developer");
        model.addAttribute("projects", projects);
        model.addAttribute("email", "nstrydom@gmail.com");

        return "index";
    }
}

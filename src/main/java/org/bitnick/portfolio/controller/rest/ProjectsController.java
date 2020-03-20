package org.bitnick.portfolio.controller.rest;

import org.bitnick.portfolio.model.Project;
import org.bitnick.portfolio.model.User;
import org.bitnick.portfolio.service.ProjectsService;
import org.bitnick.portfolio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
//@ApiIgnore
public class ProjectsController {
    private ProjectsService projectsService;

    private Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable(name = "id") String id) {
        return projectsService.getProject(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Project saveProject(@RequestBody Project projectToSave) {
        return projectsService.saveProject(projectToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Project updateProject(@RequestBody Project projectToUpdate, @PathVariable(name = "id") String id) {
        return projectsService.updateProject(projectToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") String id) {
        projectsService.deleteProject(id);
    }
}

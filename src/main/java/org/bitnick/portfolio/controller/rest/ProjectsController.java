package org.bitnick.portfolio.controller.rest;

import org.bitnick.portfolio.model.Project;
import org.bitnick.portfolio.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/project")
@ApiIgnore
public class ProjectsController {
    private ProjectService projectService;

    private Logger LOG = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable(name = "id") String id) {
        return projectService.getProject(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Project saveProject(@RequestBody Project projectToSave) {
        return projectService.saveProject(projectToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Project updateProject(@RequestBody Project projectToUpdate, @PathVariable(name = "id") String id) {
        return projectService.updateProject(projectToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") String id) {
        projectService.deleteProject(id);
    }
}

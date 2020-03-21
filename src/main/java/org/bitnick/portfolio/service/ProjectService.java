package org.bitnick.portfolio.service;

import org.bitnick.portfolio.model.Project;
import org.bitnick.portfolio.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    private Logger LOG = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    public void setProductRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProject(String id) {
        LOG.info("Getting the product with the given id: " + id);
        return projectRepository.getOne(id);
    }

    public List<Project> getAllProjects() {
        LOG.info("Getting all projects...");
        return projectRepository.findAll();
    }

    public Project saveProject(Project project) {
        Project projectToSave = null;

        try {
            LOG.info("Saving project...");

            projectToSave = projectRepository.save(project);

            return projectToSave;
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new Project();
        }
    }

    public Project updateProject(Project project, String id) {
        Project projectToUpdate = null;

        try {
            projectToUpdate = projectRepository.getOne(id);
            projectToUpdate.setName(project.getName());
            projectToUpdate.setAppUrl(project.getAppUrl());
            projectToUpdate.setSourceUrl(project.getSourceUrl());

            return projectRepository.save(projectToUpdate);
        } catch (Exception ex) {
            LOG.error("An error occurred during update of product" + ex.getMessage());

            return projectToUpdate != null ? projectToUpdate : new Project();
        }
    }

    public void deleteProject(String id) {
        try {
            projectRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

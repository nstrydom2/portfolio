/*
 * The MIT License
 *
 * Copyright (c) 2020, Nicholas Bruce Strydom
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.bitnick.portfolio.service;

import org.bitnick.portfolio.model.Project;
import org.bitnick.portfolio.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service implementation provides Project entity input/output to
 * and from the database utilizing the Jpa Repository ProjectRepository.java.
 */
@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    private Logger LOG = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    public void setProductRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Retrieves a mapped Project entity by the given id.
     * @param id UUID of the target Project entity to be retrieved
     * @return Project, retrieved via id
     */
    public Project getProject(String id) {
        LOG.info("Getting the product with the given id: " + id);
        return projectRepository.getOne(id);
    }

    /**
     * Retrieves all Project entities
     * @return List of Project entities
     */
    public List<Project> getAllProjects() {
        LOG.info("Getting all projects...");
        return projectRepository.findAll();
    }

    /**
     * Saves a Project entity to the database.
     * @param project Project entity to be saved
     * @return Project, saved Project entity
     */
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

    /**
     * Updates a Project to the database. Any exception thrown will be caught,
     * logged, and a User object will be returned will "null" properties.
     * @param project Project entity with modified properties to be updated
     * @param id UUID of target Project to be updated
     * @return Project, updated Project entity
     */
    public Project updateProject(Project project, String id) {
        Project projectToUpdate = null;

        try {
            projectToUpdate = projectRepository.getOne(id);
            projectToUpdate.setDescription(project.getDescription());
            projectToUpdate.setName(project.getName());
            projectToUpdate.setAppUrl(project.getAppUrl());
            projectToUpdate.setSourceUrl(project.getSourceUrl());

            return projectRepository.save(projectToUpdate);
        } catch (Exception ex) {
            LOG.error("An error occurred during update of product" + ex.getMessage());

            return projectToUpdate != null ? projectToUpdate : new Project();
        }
    }

    /**
     * Deletes a target Project from the database by the given id.
     * @param id UUID of target Project to be deleted
     */
    public void deleteProject(String id) {
        try {
            projectRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

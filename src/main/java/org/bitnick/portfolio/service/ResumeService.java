package org.bitnick.portfolio.service;

import org.bitnick.portfolio.model.Resume;
import org.bitnick.portfolio.repository.ResumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {
    private ResumeRepository resumeRepository;

    private Logger LOG = LoggerFactory.getLogger(ResumeService.class);

    @Autowired
    public void setResumeRepository(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    /**
     * Retrieves a mapped Resume entity by the given id.
     * @param id UUID of the target Resume entity to be retrieved
     * @return Resume, retrieved via id
     */
    public Resume getResume(String id) {
        LOG.info("Getting the product with the given id: " + id);

        try {
            return resumeRepository.getOne(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new Resume();
        }
    }

    /**
     * Retrieves all resume entities
     * @return List of Resume entities
     */
    public List<Resume> getAllResumes() {
        LOG.info("Getting all projects...");

        try {
            return resumeRepository.findAll();
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new ArrayList<>();
        }
    }

    /**
     * Retrieves latest Resume entity
     * @return Resume entity
     */
    public Resume getLatest() {
        LOG.info("Retrieving latest resume...");

        try {
            Resume resume = resumeRepository.findTopByOrderByIdDesc();
            if (resume == null) {
                throw new SQLException("Unable to retrieve latest row");
            }

            return resume;
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving: " + ex.getMessage());

            return new Resume();
        }
    }

    /**
     * Saves a Resume entity to the database.
     * @param resume Resume entity to be saved
     * @return Resume, saved Resume entity
     */
    public Resume saveResume(Resume resume) {
        LOG.info("Saving resume...");

        try {
            Resume resumeToSave = resumeRepository.save(resume);

            return resumeToSave;
        } catch (Exception ex) {
            LOG.error("An error occurred during resume saving:" + ex.getMessage());

            return new Resume();
        }
    }

    /**
     * Updates a Resume to the database. Any exception thrown will be caught,
     * logged, and a Resume object will be returned will "null" properties.
     * @param resume Resume entity with modified properties to be updated
     * @param id UUID of target Resume to be updated
     * @return Resume, updated Resume entity
     */
    public Resume updateResume(Resume resume, String id) {
        Resume resumeToUpdate = null;

        try {
            resumeToUpdate = resumeRepository.getOne(resume.getId());
            resumeToUpdate.setBio(resume.getBio());
            resumeToUpdate.setTitle(resume.getTitle());
            resumeToUpdate.setSkills(resume.getSkills());
            resumeToUpdate.setAwards(resume.getAwards());
            resumeToUpdate.setWorkExperience(resume.getWorkExperience());

            return resumeRepository.save(resumeToUpdate);
        } catch (Exception ex) {
            LOG.error("An error occurred during update of resume" + ex.getMessage());

            return resumeToUpdate != null ? resumeToUpdate : new Resume();
        }
    }

    /**
     * Deletes a target resume from the database by the given id.
     * @param id UUID of target Project to be deleted
     */
    public void deleteResume(String id) {
        try {
            resumeRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

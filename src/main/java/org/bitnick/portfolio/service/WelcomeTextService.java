package org.bitnick.portfolio.service;

import org.bitnick.portfolio.model.WelcomeText;
import org.bitnick.portfolio.repository.WelcomeTextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WelcomeTextService {
    private WelcomeTextRepository welcomeTextRepository;

    private Logger LOG = LoggerFactory.getLogger(WelcomeTextService.class);

    @Autowired
    public void setWelcomeTextRepository(WelcomeTextRepository welcomeTextRepository) {
        this.welcomeTextRepository = welcomeTextRepository;
    }

    /**
     * Retrieves a mapped WelcomeText entity by the given id.
     * @param id UUID of the target WelcomeText entity to be retrieved
     * @return WelcomeText, retrieved via id
     */
    public WelcomeText getWelcomeText(String id) {
        LOG.info("Getting the product with the given id: " + id);

        try {
            return welcomeTextRepository.getOne(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new WelcomeText();
        }
    }

    /**
     * Retrieves all WelcomeText entities
     * @return List of WelcomeText entities
     */
    public List<WelcomeText> getAllWelcomeTexts() {
        LOG.info("Getting all WelcomeTexts...");

        try {
            return welcomeTextRepository.findAll();
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new ArrayList<>();
        }
    }

    /**
     * Retrieves latest WelcomeText entity
     * @return WelcomeText entity
     */
    public WelcomeText getLatest() {
        LOG.info("Retrieving latest WelcomeText...");

        try {
            WelcomeText welcomeText = welcomeTextRepository.findTopByOrderByIdDesc();
            if (welcomeText == null) {
                throw new SQLException("Unable to retrieve latest row");
            }

            return welcomeText;
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new WelcomeText();
        }
    }

    /**
     * Saves a WelcomeText entity to the database.
     * @param WelcomeText WelcomeText entity to be saved
     * @return WelcomeText, saved WelcomeText entity
     */
    public WelcomeText saveWelcomeText(WelcomeText WelcomeText) {
        LOG.info("Saving WelcomeText...");

        try {
            return welcomeTextRepository.save(WelcomeText);
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new WelcomeText();
        }
    }

    /**
     * Updates a WelcomeText to the database. Any exception thrown will be caught,
     * logged, and a User object will be returned will "null" properties.
     * @param welcomeText WelcomeText entity with modified properties to be updated
     * @param id UUID of target WelcomeText to be updated
     * @return WelcomeText, updated WelcomeText entity
     */
    public WelcomeText updateWelcomeText(WelcomeText welcomeText, String id) {
        LOG.info("Updating WelcomeText...");

        WelcomeText welcomeTextToUpdate = null;
        try {
            welcomeTextToUpdate = welcomeTextRepository.getOne(id);
            welcomeTextToUpdate.setWelcomeText(welcomeText.getWelcomeText());

            return welcomeTextRepository.save(welcomeTextToUpdate);
        } catch (Exception ex) {
            LOG.error("An error occurred during update of product" + ex.getMessage());

            return welcomeTextToUpdate != null ? welcomeTextToUpdate : new WelcomeText();
        }
    }

    /**
     * Deletes a target WelcomeText from the database by the given id.
     * @param id UUID of target WelcomeText to be deleted
     */
    public void deleteWelcomeText(String id) {
        try {
            welcomeTextRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

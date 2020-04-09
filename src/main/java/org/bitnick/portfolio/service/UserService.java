package org.bitnick.portfolio.service;

import org.bitnick.portfolio.model.User;
import org.bitnick.portfolio.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service implementation provides User entity input/output to
 * and from the database utilizing the Jpa Repository UserRepository.java.
 */
@Service
public class UserService {
    private UserRepository userRepository;

    private Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public void setProductRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a mapped User entity by the given id.
     * @param id UUID of the target User entity to be retrieved
     * @return User retrieved via id
     */
    public User getUser(String id) {
        LOG.info("Getting the product with the given id: " + id);
        return userRepository.getOne(id);
    }

    /**
     * Saves a User entity to the database.
     * @param user User entity to be saved
     * @return User, saved User entity
     */
    public User saveUser(User user) {
        User userToSave = null;

        try {
            LOG.info("Saving user...");

            userToSave = userRepository.save(user);

            return userToSave;
        } catch (Exception ex) {
            LOG.error("An error occurred during user saving:" + ex.getMessage());

            return new User();
        }
    }

    /**
     * Updates a User to the database.
     * @param user User entity with modified properties to be updated
     * @param id UUID of target User to be updated
     * @return User, updated User entity
     */
    public User updateUser(User user, String id) {
        User userToUpdate = null;

        try {
            userToUpdate = userRepository.getOne(id);
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setLegalName(user.getLegalName());

            return userRepository.save(userToUpdate);
        } catch (Exception ex) {
            LOG.error("An error occurred during update of product" + ex.getMessage());

            // return null if userToUpdate remains null
            return userToUpdate != null ? userToUpdate : new User();
        }
    }

    /**
     * Deletes a target User from the database.
     * @param id UUID of target User to be deleted
     */
    public void deleteUser(String id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

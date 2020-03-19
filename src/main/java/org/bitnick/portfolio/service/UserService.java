package org.bitnick.portfolio.service;

import org.bitnick.portfolio.model.User;
import org.bitnick.portfolio.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    private Logger LOG = LoggerFactory.getLogger(ProductService.class);

    public void setProductRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String id) {
        LOG.info("Getting the product with the given id: " + id);
        return userRepository.getOne(id);
    }

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

            return userToUpdate != null ? userToUpdate : new User();
        }
    }

    public void deleteUser(String id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

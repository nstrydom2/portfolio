package org.bitnick.portfolio.service;

import org.bitnick.portfolio.model.Product;
import org.bitnick.portfolio.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    private Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String id) {
        LOG.info("Getting the product with the given id: " + id);
        return productRepository.getOne(id);
    }

    public Product saveProduct(Product product) {
        Product productToSave = null;

        try {
            LOG.info("Saving product...");

            productToSave = productRepository.save(product);

            return productToSave;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error("An error occurred during product saving: " + ex.getMessage());

            return productToSave != null ? productToSave : new Product();
        }
    }

    public Product updateProduct(Product product, String id) {
        Product productToUpdate = null;

        try {
            productToUpdate = productRepository.getOne(id);
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setType(product.getType());
            productToUpdate.setCategory(product.getCategory());

            return productRepository.save(productToUpdate);
        } catch (Exception ex) {
            LOG.error("An error occurred during update of product" + ex.getMessage());

            return productToUpdate != null ? productToUpdate : new Product();
        }
    }

    public void deleteProduct(String id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

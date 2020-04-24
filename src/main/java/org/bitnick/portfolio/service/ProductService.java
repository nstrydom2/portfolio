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

import org.bitnick.portfolio.model.Product;
import org.bitnick.portfolio.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service implementation provides Product entity input/output to
 * and from the database utilizing the Jpa Repository ProductRepository.java.
 */
@Service
public class ProductService {
    private ProductRepository productRepository;

    private Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a mapped Product entity by the given id.
     * @param id UUID of the target Project entity to be retrieved
     * @return Product, retrieved via id
     */
    public Product getProduct(String id) {
        LOG.info("Getting the product with the given id: " + id);
        return productRepository.getOne(id);
    }

    /**
     * Saves a Product entity to the database.
     * @param product Product entity to be saved
     * @return Product, saved Project entity
     */
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

    /**
     * Updates a Product to the database. Any exception thrown will be caught,
     * logged, and a User object will be returned will "null" properties.
     * @param product Product entity with modified properties to be updated
     * @param id UUID of target Product to be updated
     * @return Product, updated Product entity
     */
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

    /**
     * Deletes a target Product from the database by the given id.
     * @param id UUID of target Product to be deleted
     */
    public void deleteProduct(String id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            LOG.error("An error occurred during deleting of product:" + ex.getMessage());
        }
    }
}

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
package org.bitnick.portfolio.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bitnick.portfolio.model.Product;
import org.bitnick.portfolio.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product")
@Api(value = "ProductControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {
    private ProductService productService;

    private Logger LOG = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Retrieves a product with a specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
    public Product getProduct(@PathVariable(name = "id") String id) {
        return productService.getProduct(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Saves a product")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
    public Product saveProduct(@RequestBody Product productToSave) {
        return productService.saveProduct(productToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Updates a product with a specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {
        return productService.updateProduct(productToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    @ApiOperation("Deletes a product with a specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productService.deleteProduct(id);
    }
}

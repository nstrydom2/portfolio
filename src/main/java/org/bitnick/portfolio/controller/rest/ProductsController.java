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
    @ApiOperation("Gets the product with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
    public Product getProduct(@PathVariable(name = "id") String id) {
        return productService.getProduct(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave) {
        return productService.saveProduct(productToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {
        return productService.updateProduct(productToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productService.deleteProduct(id);
    }
}

package com.safestreets.controller;

import com.safestreets.dto.ProductDTO;
import com.safestreets.model.Product;
import com.safestreets.services.ProductService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Post
    public Product createProduct(@Body ProductDTO product) {
        log.info("Name,description:%s, %s)".formatted(product.getName(), product.getDescription()));
        return productService.createNewProduct(product.getName(), product.getDescription());
    }

    @Put("/{productId}")
    public HttpResponse<?> updateProduct(@Body ProductDTO productDTO, @PathVariable String productId) {
        long id = Long.parseLong(productId);
        if (!productService.productExists(id)) {
            return HttpResponse.notFound();
        }

        Product product = productService.getProduct(id);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        Product updatedproduct = productService.updateProduct(product, product.getId());
        return HttpResponse.ok(updatedproduct);
    }

    @Get(uri = "/{productId}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> getProduct(@PathVariable String productId) {
        var product = productService.getProduct(Long.parseLong(productId));
        if (product == null)
        {
            return HttpResponse.notFound();
        }
        else {
            return HttpResponse.ok(product);
        }
    }

    @Delete("/{productId}")
    @Status(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String productId)
    {
        long id = Long.parseLong(productId);
        productService.deleteProduct(id);
    }
}
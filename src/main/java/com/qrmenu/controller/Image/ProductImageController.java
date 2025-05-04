package com.qrmenu.controller.Image;

import com.qrmenu.entity.Product;
import com.qrmenu.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class ProductImageController {

    private final ProductService productService;

    public ProductImageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/image/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable UUID id) {
        Product product = productService.findById(id);
        if (product == null || product.getImage() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", product.getImageContentType());
        return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
    }
}

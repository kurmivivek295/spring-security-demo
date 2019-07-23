package com.vk.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vk.domain.product.Product;
import com.vk.repository.product.ProductRepo;

@CrossOrigin(origins = "*")
@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Product> product() {
        return productRepo.findAll();
    }
}
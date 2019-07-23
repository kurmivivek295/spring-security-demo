package com.vk.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.domain.product.Product;

public interface ProductRepo extends JpaRepository<Product, String> {

}

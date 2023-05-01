package com.mercadona.ean.repository;

import com.mercadona.ean.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

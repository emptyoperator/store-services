package org.leniv.distributed.systems.order.controller;

import lombok.AllArgsConstructor;
import org.leniv.distributed.systems.order.entity.Product;
import org.leniv.distributed.systems.order.exception.ProductNotFoundException;
import org.leniv.distributed.systems.order.repository.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
class ProductController {
    ProductRepository repository;

    @GetMapping("/{id}")
    Product findById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping
    List<Product> findAll() {
        return repository.findAll();
    }

    @PostMapping
    Product create(@RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping("/{id}")
    Product update(@PathVariable Long id, @RequestBody Product product) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        product.setId(id);
        return repository.save(product);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

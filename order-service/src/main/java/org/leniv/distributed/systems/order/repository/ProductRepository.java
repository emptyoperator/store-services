package org.leniv.distributed.systems.order.repository;

import org.leniv.distributed.systems.order.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

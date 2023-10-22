package org.leniv.distributed.systems.order;

import org.leniv.distributed.systems.order.entity.Product;
import org.leniv.distributed.systems.order.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner dataLoader(ProductRepository productRepository) {
        return args -> {
            Product laptop = Product.builder()
                    .name("Laptop")
                    .price(BigDecimal.valueOf(29_999))
                    .quantity(5)
                    .build();
            Product chair = Product.builder()
                    .name("Chair")
                    .price(BigDecimal.valueOf(3_999))
                    .quantity(15)
                    .build();
            Product watch = Product.builder()
                    .name("Watch")
                    .price(BigDecimal.valueOf(10_999))
                    .quantity(10)
                    .build();
            productRepository.saveAll(List.of(laptop, chair, watch));
        };
    }
}

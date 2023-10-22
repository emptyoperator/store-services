package org.leniv.distributed.systems.order.entity;

import static java.math.BigDecimal.ZERO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Data
@Entity
@Table(name = "order_t")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private long userId;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private Set<Entry> entries;

    @Column
    private boolean paid;

    @Transient
    private BigDecimal total;

    @PostLoad
    @PreUpdate
    @PrePersist
    private void initTotal() {
        total = entries.stream().map(entry -> Optional.ofNullable(entry.getTotal()).orElseGet(() -> {
            entry.initTotal();
            return entry.getTotal();
        })).reduce(BigDecimal::add).orElse(ZERO);
    }

    @Data
    @Entity
    public static class Entry {
        @Id
        @GeneratedValue
        private Long id;

        @OneToOne
        private Product product;

        @Column
        private long quantity;

        @Transient
        private BigDecimal total;

        @PostLoad
        @PreUpdate
        @PrePersist
        private void initTotal() {
            total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
    }
}

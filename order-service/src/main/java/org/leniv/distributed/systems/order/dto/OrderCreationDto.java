package org.leniv.distributed.systems.order.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderCreationDto {
    private Set<EntryCreationDto> entries;

    @Data
    public static class EntryCreationDto {
        private Long productId;
        private long quantity;
    }
}

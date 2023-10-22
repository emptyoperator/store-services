package org.leniv.distributed.systems.order.mapper;

import org.leniv.distributed.systems.order.dto.OrderDto;
import org.leniv.distributed.systems.order.dto.OrderCreationDto;
import org.leniv.distributed.systems.order.entity.Order;
import org.leniv.distributed.systems.order.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public abstract class OrderMapper {
    @Autowired
    protected ProductRepository productRepository;

    public abstract OrderDto orderToOrderDto(Order order);

    public abstract List<OrderDto> ordersToOrderDtos(List<Order> orders);

    public abstract OrderDto.EntryDto entryToEntryDto(Order.Entry entry);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "total", ignore = true)
    public abstract Order orderCreationDtoToOrder(OrderCreationDto orderCreation, long userId);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "total", ignore = true)
    public abstract Order orderCreationDtoToOrder(Long id, OrderCreationDto orderCreation, long userId);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "product", expression = "java(productRepository.findById(entryCreation.getProductId()).orElseThrow())")
    public abstract Order.Entry entryCreationDtoToEntry(OrderCreationDto.EntryCreationDto entryCreation);
}

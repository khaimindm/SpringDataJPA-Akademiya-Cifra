package ru.khaimin.springcourse.mapper;

// Интерфейс для маппинга Order -> OrderDTO

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.khaimin.springcourse.dto.OrderDTO;
import ru.khaimin.springcourse.entity.OrderDishEntity;
import ru.khaimin.springcourse.models.Order;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

    List<OrderDTO> toOrderDTOS(List<Order> orders);

    Order toOrder(OrderDishEntity orderDishEntity);

    List<Order> toOrders(List<OrderDishEntity> orderDishEntities);
}

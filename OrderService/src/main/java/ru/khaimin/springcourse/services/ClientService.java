package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.dto.ClientDTO;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.dto.OrderDTO;
import ru.khaimin.springcourse.mapper.OrderMapper;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.services.clients.KitchenServiceClient;

import java.util.List;

// Сервис клиентов

@Service
public class ClientService {
    private final KitchenServiceClient kitchenServiceClient;
    private final OrderMapper orderMapper;

    @Autowired
    public ClientService(KitchenServiceClient kitchenServiceClient_, OrderMapper orderMapper_) {
        this.kitchenServiceClient = kitchenServiceClient_;
        this.orderMapper = orderMapper_;
    }

    public OrderDTO placeOrder(Order order, ClientDTO clientDTO, List<DishDTO> dishDTOS) {

        OrderDTO orderDTO = orderMapper.toOrderDTO(order);

        orderDTO.setClientDTO(clientDTO);
        orderDTO.setDishDTOS(dishDTOS);

        if (kitchenServiceClient.canAcceptOrder()) {

            return kitchenServiceClient.processOrderInKitchen(orderDTO);
        } else {
            return orderDTO;
        }
    }

}

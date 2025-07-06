package ru.khaimin.springcourse.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.khaimin.springcourse.dto.ClientDTO;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.dto.OrderDTO;
import ru.khaimin.springcourse.dto.OrderDetailDTO;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.mapper.ClientMapper;
import ru.khaimin.springcourse.mapper.DishMapper;
import ru.khaimin.springcourse.mapper.OrderMapper;
import ru.khaimin.springcourse.models.Client;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.request.OrderRequest;
import ru.khaimin.springcourse.response.OrderResponse;
import ru.khaimin.springcourse.services.ClientService;
import ru.khaimin.springcourse.services.DishService;
import ru.khaimin.springcourse.services.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Класс контроллер для OrderService

@RestController
@RequestMapping("/order_service")
public class OrderServiceController {

    private final DishService dishService;
    private final DishMapper dishMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ClientMapper clientMapper;
    private final ClientService clientService;

    @Autowired
    public OrderServiceController(DishService dishService_, DishMapper dishMapper_, OrderService orderService_,
                                  OrderMapper orderMapper_, ClientMapper clientMapper_, ClientService clientService_) {
        this.dishService = dishService_;
        this.dishMapper = dishMapper_;
        this.orderService = orderService_;
        this.orderMapper = orderMapper_;
        this.clientMapper = clientMapper_;
        this.clientService = clientService_;
    }

    @GetMapping("/allDishDTOS")
    public List<DishDTO> getAllDishDTOS() {

        // Не знаю, можно ли напрямую к сервисам обращаться в контроллерах. Буду благодарен за комментарий
        return dishMapper.toDishDTOS(dishService.findAllDishes());

    }

    @PostMapping("/processOrder")
    public List<OrderResponse> processOrder(@RequestBody OrderRequest orderRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> idSelectedDishes = null;

        try {
            // Преобразую JSON в List<Integer>
            idSelectedDishes = objectMapper.readValue(orderRequest.getIdSelectedDishesJson(), new TypeReference<List<Integer>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Client client = new Client(Integer.parseInt(orderRequest.getClientIdString()), orderRequest.getClientName());

        Map<Integer, Integer> dishesAndQuantity = null;
        try {
            // Преобразую JSON в Map<Integer, Integer>
            dishesAndQuantity = objectMapper.readValue(orderRequest.getDishesAndQuantityJson(),
                    new TypeReference<Map<Integer, Integer>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Order order = orderService.createOrderByIdSelectedDishes(idSelectedDishes, client, dishesAndQuantity);

        // Получаю список всех объектов DishDTO в данном заказе
        List<DishDTO> dishDTOS = dishMapper.toDishDTOS(order.getDishes());

        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for (DishDTO dishDTO : dishDTOS) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setDishDTO(dishDTO);
            orderDetailDTO.setPrice(dishDTO.getPrice());

            // Устанавливаю в OrderDetailDTO количество, получая из Map<Integer, Integer> dishesAndQuantity по id блюда
            orderDetailDTO.setQuantity(dishesAndQuantity.get(dishDTO.getId()));
            orderDetailDTOS.add(orderDetailDTO);
        }

        // Получаю объект OrderDTO из Order
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);

        ClientDTO clientDTO = clientMapper.toClientDTO(client);

        orderDTO.setClientDTO(clientDTO);

        List<DishEntity> dishEntities = orderService.getDishEntitiesByIds(idSelectedDishes);

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (DishEntity dishEntity : dishEntities) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setDishName(dishEntity.getName());
            orderResponse.setDishPrice(String.valueOf(dishEntity.getPrice()));
            orderResponse.setDishQuantity(String.valueOf(dishesAndQuantity.get(dishEntity.getId())));

            orderResponses.add(orderResponse);
        }

        if (clientService.placeOrder(order)) {
            return orderResponses;
        } else {
            return null;
        }

    }
}

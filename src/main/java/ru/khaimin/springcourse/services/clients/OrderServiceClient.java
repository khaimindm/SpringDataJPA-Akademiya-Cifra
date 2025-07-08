package ru.khaimin.springcourse.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.dto.OrderRequestDTO;
import ru.khaimin.springcourse.dto.OrderResponseDTO;

import java.util.List;

// Feign-клиент для микросервиса OrderService

@FeignClient(
        name = "order-service",
        url = "${order.service.url}"
)
public interface OrderServiceClient {

    @GetMapping("/order_service/allDishDTOS")
    List<DishDTO> getAllDishDTOS();

    @PostMapping("/order_service/processOrder")
    List<OrderResponseDTO> processOrder(@RequestBody OrderRequestDTO orderRequestDTO);

    @GetMapping("/order_service/{orderId}")
    String getOrderFullCostByOrderId(@PathVariable("orderId") String orderId);
}

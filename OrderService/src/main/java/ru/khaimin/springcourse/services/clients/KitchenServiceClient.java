package ru.khaimin.springcourse.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.khaimin.springcourse.dto.OrderDTO;

@FeignClient(
        name = "kitchen-service",
        url = "${kitchen.service.url}"
)
public interface KitchenServiceClient {

    @GetMapping("/kitchen_service/can_accept_order")
    boolean canAcceptOrder();

    @PostMapping("/kitchen_service/process_order")
    OrderDTO processOrderInKitchen(@RequestBody OrderDTO orderDTO);


}

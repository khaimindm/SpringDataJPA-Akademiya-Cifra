package ru.khaimin.springcourse.controllers;

import org.springframework.web.bind.annotation.*;
import ru.khaimin.springcourse.dto.OrderDTO;
import ru.khaimin.springcourse.services.PizzaService;

@RestController
@RequestMapping("/kitchen_service")
public class KitchenController {

    private final PizzaService pizzaService;

    public KitchenController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


    @GetMapping("/can_accept_order")
    public boolean canAcceptOrder() {
        if (pizzaService.canAcceptOrder()) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/process_order")
    public OrderDTO processOrderInKitchen(@RequestBody OrderDTO orderDTO) {
        return pizzaService.prepareOrder(orderDTO);
    }
}

package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.kitchen.PizzaKitchen;
import ru.khaimin.springcourse.models.Order;

@Service
public class CustomerService {

    private final PizzaKitchen pizzaKitchen;
    private final OrderService orderService;

    @Autowired
    public CustomerService(PizzaKitchen pizzaKitchen_, OrderService orderService_) {
        this.pizzaKitchen = pizzaKitchen_;
        this.orderService = orderService_;
    }

    public boolean placeOrder(Order order) {
        if (pizzaKitchen.canAcceptOrder()) {
            orderService.saveOrder(order);
            pizzaKitchen.prepareOrder(order);

            return true;
        } else {
            return false;
        }
    }

}

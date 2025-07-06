package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.kitchen.PizzaKitchen;
import ru.khaimin.springcourse.models.Order;

// Сервис клиентов

@Service
public class ClientService {

    private final PizzaKitchen pizzaKitchen;

    @Autowired
    public ClientService(PizzaKitchen pizzaKitchen_) {
        this.pizzaKitchen = pizzaKitchen_;
    }

    public boolean placeOrder(Order order) {
        if (pizzaKitchen.canAcceptOrder()) {
            pizzaKitchen.prepareOrder(order);
            return true;
        } else {
            return false;
        }
    }

}

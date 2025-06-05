package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.kitchen.PizzaKitchen;
import ru.khaimin.springcourse.models.Order;

@Service
public class CustomerService {

    @Autowired
    PizzaKitchen pizzaKitchen;

    public void placeOrder(Order order) {
        if (pizzaKitchen.canAcceptOrder()) {
            System.out.println("Prinyat zakaz ot " + order.getClient().getName());
            pizzaKitchen.prepareOrder(order);
        } else {
            System.out.println("Kuhnya peregruzhena, zakaz otklonyon");
        }
    }

}

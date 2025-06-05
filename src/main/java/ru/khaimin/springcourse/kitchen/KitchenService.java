package ru.khaimin.springcourse.kitchen;

import ru.khaimin.springcourse.models.Order;

public interface KitchenService {

    boolean canAcceptOrder();

    void prepareOrder(Order order);

}

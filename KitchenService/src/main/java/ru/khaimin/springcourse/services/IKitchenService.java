package ru.khaimin.springcourse.services;

import ru.khaimin.springcourse.dto.OrderDTO;

public interface IKitchenService {

    boolean canAcceptOrder();

    OrderDTO prepareOrder(OrderDTO orderDTO);

}

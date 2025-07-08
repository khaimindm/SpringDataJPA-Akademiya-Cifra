package ru.khaimin.springcourse.services;

import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.dto.OrderDTO;


@Service
public class JapaneseService implements IKitchenService {

    @Override
    public boolean canAcceptOrder() {
        return false;
    }

    @Override
    public OrderDTO prepareOrder(OrderDTO orderDTO) {
        return null;
    }

}

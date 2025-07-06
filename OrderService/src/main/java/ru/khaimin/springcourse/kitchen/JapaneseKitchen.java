package ru.khaimin.springcourse.kitchen;

import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.models.Order;


@Service
public class JapaneseKitchen implements KitchenService {

    @Override
    public boolean canAcceptOrder() {
        return false;
    }

    @Override
    public void prepareOrder(Order order) {

    }

}

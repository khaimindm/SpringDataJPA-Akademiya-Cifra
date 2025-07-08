package ru.khaimin.springcourse.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.dto.OrderDTO;

@Service
@Primary
@Setter
@Getter
public class PizzaService implements IKitchenService {

    //пример инициализации через параметр
    @Value("${kitchen.capacity}")
    private int capacity;
    private final long timeout;
    private int currentOrders;


    //TODO добавить исключения на корректные значения
    public PizzaService(@Value("${kitchen.timeout}") long timeout) { // пример инициализации через контсруктор
        this.timeout = timeout;
    }

    @Override
    public boolean canAcceptOrder() {
        return currentOrders < capacity;
    }

    @Override
    public OrderDTO prepareOrder(OrderDTO orderDTO) {
        currentOrders++;

        // Имитация приготовления
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        currentOrders--;

        // Изменяю статус готовности заказа на true
        orderDTO.setReadiness(true);

        return orderDTO;
    }

}

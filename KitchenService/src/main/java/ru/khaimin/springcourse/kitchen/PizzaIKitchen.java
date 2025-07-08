//package ru.khaimin.springcourse.kitchen;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//import ru.khaimin.springcourse.models.Order;
//import ru.khaimin.springcourse.services.OrderService;
//
//@Service
//@Primary
//@Setter
//@Getter
//public class PizzaIKitchen implements IKitchenService {
//
//    @Autowired
//    private OrderService orderService;
//
//    //пример инициализации через параметр
//    @Value("${kitchen.capacity}")
//    private int capacity;
//    private final long timeout;
//    private int currentOrders;
//
//
//    //TODO добавить исключения на корректные значения
//    public PizzaIKitchen(@Value("${kitchen.timeout}") long timeout) { // пример инициализации через контсруктор
//        this.timeout = timeout;
//    }
//
//    @Override
//    public boolean canAcceptOrder() {
//        return currentOrders < capacity;
//    }
//
//    @Override
//    public void prepareOrder(Order order) {
//        currentOrders++;
//
//        // Имитация приготовления
//        try {
//            Thread.sleep(timeout);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//        currentOrders--;
//
//        // Изменяю статус готовности заказа на true
//        order.setReadiness(true);
//        orderService.saveOrder(order);
//    }
//
//}

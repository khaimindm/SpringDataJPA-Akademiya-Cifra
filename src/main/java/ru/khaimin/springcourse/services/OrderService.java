package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.entity.OrderDetailEntity;
import ru.khaimin.springcourse.entity.OrderDishEntity;
import ru.khaimin.springcourse.models.Client;
import ru.khaimin.springcourse.models.Dish;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.models.OrderDetail;
import ru.khaimin.springcourse.repositories.OrderDetailRepository;
import ru.khaimin.springcourse.repositories.OrderDishRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Сервис для заказа
@Service
public class OrderService {

    private final OrderDishRepository orderDishRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderService(OrderDishRepository orderDishRepository_, OrderDetailRepository orderDetailRepository_) {
        this.orderDishRepository = orderDishRepository_;
        this.orderDetailRepository = orderDetailRepository_;
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        List<OrderDishEntity> orderDishEntities = orderDishRepository.findAll();
        List<Order> orders = new ArrayList<>();

        // Понимаю, что можно автоматизировать процесс. Скорее всего через MapStruct.
        // Пока оставляю так. В будущем разберусь и перепишу код.
        for (OrderDishEntity orderDishEntity : orderDishEntities) {

            Order order = new Order();

            order.setId(orderDishEntity.getId());
            order.setDate(orderDishEntity.getDate());
            order.setReadiness(orderDishEntity.isReadiness());
            order.setOrderFullCost(orderDishEntity.getOrderFullCost());

            Client client = new Client();
            client.setId(orderDishEntity.getClientEntity().getId());
            client.setName(orderDishEntity.getClientEntity().getName());

            order.setClient(client);

            List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrderDishEntity(orderDishEntity);
            OrderDetail orderDetail = new OrderDetail();

            Map<String, Integer> orderDetailMap = new LinkedHashMap<>();

            for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
                orderDetailMap.put(orderDetailEntity.getDishEntity().getName(), orderDetailEntity.getQuantity());
            }

            orderDetail.setOrderDetail(orderDetailMap);
            order.setOrderDetail(orderDetail);
            orders.add(order);
        }

        return orders;
    }

    @Transactional
    public void saveOrder(Order order, ClientEntity clientEntity) {

        List<Dish> dishes = order.getDishes();

        OrderDishEntity orderDishEntity = new OrderDishEntity();
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

        orderDishEntity.setDate(order.getDate());
        orderDishEntity.setReadiness(false);
        orderDishEntity.setOrderFullCost(getFullCostOfOrder(order));

        DishEntity dishEntity = new DishEntity();

        for (Dish dish : dishes) {
            dishEntity.setName(dish.getName());
            dishEntity.setPrice(dish.getPrice());
            dishEntity.setDType(dish.getDType());

            orderDetailEntity.setDishEntity(dishEntity);
            orderDetailEntity.setQuantity(1);
            orderDetailEntities.add(orderDetailEntity);
        }

        orderDishEntity.setOrderDetailEntities(orderDetailEntities);

        orderDishEntity.setClientEntity(clientEntity);

        orderDishRepository.save(orderDishEntity);

    }

    public double getFullCostOfOrder(Order order) {
        List<Dish> dishes = order.getDishes();
        double fullCostOfOrder = 0;

        for (Dish dish : dishes) {
            fullCostOfOrder += dish.getPrice();
        }

        return fullCostOfOrder;
    }
}

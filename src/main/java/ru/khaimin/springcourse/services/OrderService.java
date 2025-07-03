package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.entity.OrderDetailEntity;
import ru.khaimin.springcourse.entity.OrderDishEntity;
import ru.khaimin.springcourse.models.*;
import ru.khaimin.springcourse.repositories.DishRepository;
import ru.khaimin.springcourse.repositories.OrderDetailRepository;
import ru.khaimin.springcourse.repositories.OrderDishRepository;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

// Сервис для заказа
@Service
public class OrderService {

    private final OrderDishRepository orderDishRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final DishRepository dishRepository;

    @Autowired
    public OrderService(OrderDishRepository orderDishRepository_, OrderDetailRepository orderDetailRepository_,
                        DishRepository dishRepository_) {
        this.orderDishRepository = orderDishRepository_;
        this.orderDetailRepository = orderDetailRepository_;
        this.dishRepository = dishRepository_;
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

            List<OrderDetail> orderDetails = new ArrayList<>();

            for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
                OrderDetail orderDetail = new OrderDetail();

                orderDetail.setId(orderDetailEntity.getId());

                DishEntity dishEntity = orderDetailEntity.getDishEntity();

                Dish dish;

                if (dishEntity.getDType().equals("pizza")) {
                    dish = new Pizza();
                } else {
                    dish = new Sushi();
                }

                dish.setId(dishEntity.getId());
                dish.setName(dishEntity.getName());
                dish.setPrice(dishEntity.getPrice());
                dish.setDType(dishEntity.getDType());

                orderDetail.setDish(dish);

                orderDetails.add(orderDetail);
            }

            order.setOrderDetails(orderDetails);

            orders.add(order);
        }

        return orders;
    }

    @Transactional
    public void saveOrder(Order order) {

        List<Dish> dishes = order.getDishes();

        OrderDishEntity orderDishEntity = new OrderDishEntity();
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

        orderDishEntity.setDate(order.getDate());
        orderDishEntity.setReadiness(order.isReadiness());
        orderDishEntity.setOrderFullCost(getFullCostOfOrderByDishes(order.getDishes()));

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

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(order.getClient().getId());
        clientEntity.setName(order.getClient().getName());

        orderDishEntity.setClientEntity(clientEntity);

        orderDishRepository.save(orderDishEntity);
    }

    public double getFullCostOfOrderByDishes(List<Dish> dishes) {
        double fullCostOfOrder = 0;

        for (Dish dish : dishes) {
            fullCostOfOrder += dish.getPrice();
        }

        return fullCostOfOrder;
    }

    public Order createOrderByIdSelectedDishes(List<Integer> idSelectedDishes, Client client) {
        Order order = new Order();

        order.setDate(new Date());
        order.setReadiness(false);

        List<DishEntity> dishEntities = dishRepository.findAllById(idSelectedDishes);
        List<Dish> dishes = new ArrayList<>();

        for (DishEntity dishEntity : dishEntities) {
            Dish dish;

            if (dishEntity.getDType().equals("pizza")) {
                dish = new Pizza();
            } else {
                dish = new Sushi();
            }

            dish.setId(dishEntity.getId());
            dish.setName(dishEntity.getName());
            dish.setPrice(dishEntity.getPrice());
            dish.setDType(dishEntity.getDType());

            dishes.add(dish);
        }

        order.setOrderFullCost(getFullCostOfOrderByDishes(dishes));
        order.setClient(client);
        order.setDishes(dishes);

        return order;
    }
}

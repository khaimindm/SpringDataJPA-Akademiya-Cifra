package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.entity.OrderDetailEntity;
import ru.khaimin.springcourse.entity.OrderDishEntity;
import ru.khaimin.springcourse.mapper.ClientMapper;
import ru.khaimin.springcourse.mapper.DishMapper;
import ru.khaimin.springcourse.mapper.OrderMapper;
import ru.khaimin.springcourse.models.Client;
import ru.khaimin.springcourse.models.Dish;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.models.OrderDetail;
import ru.khaimin.springcourse.repositories.DishRepository;
import ru.khaimin.springcourse.repositories.OrderDetailRepository;
import ru.khaimin.springcourse.repositories.OrderDishRepository;

import java.util.*;

// Сервис для заказа
@Service
public class OrderService {

    private final OrderDishRepository orderDishRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final DishService dishService;
    private final DishRepository dishRepository;
    private final OrderMapper orderMapper;
    private final ClientMapper clientMapper;
    private final DishMapper dishMapper;

    @Autowired
    public OrderService(OrderDishRepository orderDishRepository_, OrderDetailRepository orderDetailRepository_,
                        DishService dishService_, DishRepository dishRepository_, OrderMapper orderMapper_,
                        ClientMapper clientMapper_, DishMapper dishMapper_) {
        this.orderDishRepository = orderDishRepository_;
        this.orderDetailRepository = orderDetailRepository_;
        this.dishService = dishService_;
        this.dishRepository = dishRepository_;
        this.orderMapper = orderMapper_;
        this.clientMapper = clientMapper_;
        this.dishMapper = dishMapper_;
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        List<OrderDishEntity> orderDishEntities = orderDishRepository.findAll();
        List<Order> orders = orderMapper.toOrders(orderDishEntities);

        for (OrderDishEntity orderDishEntity : orderDishEntities) {

            Order order = new Order();

            Client client = clientMapper.toClient(orderDishEntity.getClientEntity());

            order.setClient(client);

            List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrderDishEntity(orderDishEntity);

            List<OrderDetail> orderDetails = new ArrayList<>();

            for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
                OrderDetail orderDetail = new OrderDetail();

                orderDetail.setId(orderDetailEntity.getId());

                DishEntity dishEntity = orderDetailEntity.getDishEntity();

                Dish dish;

                if (dishEntity.getDType().equals("pizza")) {
                    dish = dishMapper.toPizza(dishEntity);
                } else {
                    dish = dishMapper.toSushi(dishEntity);
                }

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

        DishEntity dishEntity = new DishEntity();

        Map<Integer, Integer> dishesAndQuantity = new HashMap<>();

        List<OrderDetail> orderDetails = order.getOrderDetails();

        for (OrderDetail orderDetail : orderDetails) {
            dishesAndQuantity.put(orderDetail.getDish().getId(), orderDetail.getQuantity());
        }

        for (Dish dish : dishes) {
            dishEntity.setName(dish.getName());
            dishEntity.setPrice(dish.getPrice());
            dishEntity.setDType(dish.getDType());

            orderDetailEntity.setDishEntity(dishEntity);
            orderDetailEntity.setQuantity(1);
            orderDetailEntities.add(orderDetailEntity);
        }

        orderDishEntity.setOrderFullCost(getFullCostOfOrderByDishesAndQuantity(order.getDishes(), dishesAndQuantity));

        orderDishEntity.setOrderDetailEntities(orderDetailEntities);

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(order.getClient().getId());
        clientEntity.setName(order.getClient().getName());

        orderDishEntity.setClientEntity(clientEntity);

        orderDishRepository.save(orderDishEntity);
    }

    public double getFullCostOfOrderByDishesAndQuantity(List<Dish> dishes, Map<Integer, Integer> dishesAndQuantity) {
        double fullCostOfOrder = 0;

        for (Dish dish : dishes) {
            int quantityOfDishes = dishesAndQuantity.get(dish.getId());

            double totalAmountOfDish = dish.getPrice() * quantityOfDishes;

            fullCostOfOrder += totalAmountOfDish;
        }

        return fullCostOfOrder;
    }

    @Transactional(readOnly = true)
    public Order createOrderByIdSelectedDishes(List<Integer> idSelectedDishes, Client client,
                                               Map<Integer, Integer> dishesAndQuantity) {
        Order order = new Order();

        order.setDate(new Date());
        order.setReadiness(false);

        List<DishEntity> dishEntities = dishRepository.findAllById(idSelectedDishes);
        List<Dish> dishes = new ArrayList<>();

        for (DishEntity dishEntity : dishEntities) {
            Dish dish;

            if (dishEntity.getDType().equals("pizza")) {
                dish = dishMapper.toPizza(dishEntity);
            } else {
                dish = dishMapper.toSushi(dishEntity);
            }

            dishes.add(dish);
        }

        order.setOrderFullCost(getFullCostOfOrderByDishesAndQuantity(dishes, dishesAndQuantity));
        order.setClient(client);

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (Dish dish : dishes) {
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrder(order);
            orderDetail.setDish(dish);
            orderDetail.setQuantity(dishesAndQuantity.get(dish.getId()));

            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
        order.setDishes(dishes);

        return order;
    }

}

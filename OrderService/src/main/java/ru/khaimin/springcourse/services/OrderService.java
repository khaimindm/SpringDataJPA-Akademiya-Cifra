package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        orderDishEntity.setClientId(order.getClient().getId());

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

    @Transactional(readOnly = true)
    public List<DishEntity> getDishEntitiesByIds(List<Integer> idSelectedDishes) {
        return dishRepository.findAllById(idSelectedDishes);
    }

    @Transactional(readOnly = true)
    public double getOrderFullCostByOrderId(Integer orderId) {
        OrderDishEntity orderDishEntity = orderDishRepository.findById(orderId).orElse(null);

        if (orderDishEntity != null){
            return orderDishEntity.getOrderFullCost();
        } else {
            return 0;
        }
    }
}

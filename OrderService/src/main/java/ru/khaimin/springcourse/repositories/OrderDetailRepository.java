package ru.khaimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khaimin.springcourse.entity.OrderDetailEntity;
import ru.khaimin.springcourse.entity.OrderDishEntity;

import java.util.List;

// Репозиторий для подробностей заказа

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {

    List<OrderDetailEntity> findByOrderDishEntity(OrderDishEntity orderDishEntity);

}

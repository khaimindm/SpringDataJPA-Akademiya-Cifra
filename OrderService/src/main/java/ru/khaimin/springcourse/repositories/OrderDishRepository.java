package ru.khaimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khaimin.springcourse.entity.OrderDishEntity;

// Репозиторий заказов

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDishEntity, Integer> {

}

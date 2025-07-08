package ru.khaimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khaimin.springcourse.entity.OrderDishEntity;

// Репозиторий заказов

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDishEntity, Integer> {

    @Modifying
    @Query("UPDATE OrderDishEntity e SET e.readiness = ?1 WHERE e.id = ?2")
    void updateOrderReadinessById(boolean isReadiness, Integer orderId);

}

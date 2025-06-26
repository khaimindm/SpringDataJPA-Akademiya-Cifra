package ru.khaimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khaimin.springcourse.entity.DishEntity;

import java.util.List;

// Репозиторий для блюд

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Integer> {

    @Query(value = "select * from dish d where d.dtype = ?1", nativeQuery = true)
    List<DishEntity> findByDType(String dType);

}

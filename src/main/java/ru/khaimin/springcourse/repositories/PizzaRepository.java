package ru.khaimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khaimin.springcourse.models.Pizza;

import java.util.List;

// Репозиторий для обработки данных из базы для пиццы
@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    List<Pizza> findAllByDtype(String dtype);

}

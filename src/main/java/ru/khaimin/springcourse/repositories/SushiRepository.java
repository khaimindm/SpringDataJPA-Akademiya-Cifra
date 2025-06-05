package ru.khaimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khaimin.springcourse.models.Sushi;

import java.util.List;

// Репозиторий для обработки данных из базы для суши
@Repository
public interface SushiRepository extends JpaRepository<Sushi, Integer> {

    List<Sushi> findAllByDtype(String dtype);

}

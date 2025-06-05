package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.models.Pizza;
import ru.khaimin.springcourse.repositories.PizzaRepository;

import java.util.List;

// Сервис для пиццы
@Service
@Transactional(readOnly = true)
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> findAllPizzas() {
        return pizzaRepository.findAllByDtype("pizza");
    }

}

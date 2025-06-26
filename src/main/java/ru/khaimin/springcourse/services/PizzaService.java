package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.models.Pizza;
import ru.khaimin.springcourse.repositories.DishRepository;

import java.util.ArrayList;
import java.util.List;

// Сервис для пиццы
@Service
@Transactional(readOnly = true)
public class PizzaService {

    private final DishRepository dishRepository;

    @Autowired
    public PizzaService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Transactional(readOnly = true)
    public List<Pizza> findAllPizzas() {
        List<DishEntity> dishEntities = dishRepository.findByDType("pizza");
        List<Pizza> pizzas = new ArrayList<>();
        for (DishEntity dishEntity : dishEntities) {
            Pizza pizza = new Pizza();

            pizza.setId(dishEntity.getId());
            pizza.setName(dishEntity.getName());
            pizza.setPrice(dishEntity.getPrice());

            pizzas.add(pizza);
        }

        return pizzas;
    }
}

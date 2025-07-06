package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.models.Dish;

import java.util.ArrayList;
import java.util.List;

// Сервис блюд

@Service
public class DishService {

    private final PizzaService pizzaService;
    private final SushiService sushiService;

    @Autowired
    public DishService(PizzaService pizzaService_, SushiService sushiService_) {
        this.pizzaService = pizzaService_;
        this.sushiService = sushiService_;
    }

    // Метод для получения всех блюд из базы данных
    public List<Dish> findAllDishes() {
        List<Dish> dishes = new ArrayList<>();

        dishes.addAll(pizzaService.findAllPizzas());
        dishes.addAll(sushiService.findAllSushi());

        return dishes;
    }
}

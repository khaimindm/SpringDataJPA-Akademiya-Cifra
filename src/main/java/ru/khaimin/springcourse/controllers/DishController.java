package ru.khaimin.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.models.Dish;
import ru.khaimin.springcourse.services.DishService;

import java.util.ArrayList;
import java.util.List;

// Класс контроллер для блюд

@Controller
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService_) {
        this.dishService = dishService_;
    }

    // Получаю все блюда из базы данных и передаю их используя объекты DishDTO в представление
    @GetMapping("/menu")
    public String showMenu(Model model) {

        // Не знаю, можно ли напрямую к сервисам обращаться в контроллерах. Буду благодарен за комментарий
        List<Dish> dishes = dishService.findAllDishes();
        List<DishDTO> dishDTOS = new ArrayList<>();

        for (Dish dish : dishes) {
            DishDTO dishDTO = new DishDTO();

            dishDTO.setName(dish.getName());
            dishDTO.setPrice(dish.getPrice());

            dishDTOS.add(dishDTO);
        }

        model.addAttribute("dishDTOS", dishDTOS);

        return "menu";
    }

}

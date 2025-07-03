package ru.khaimin.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.khaimin.springcourse.dto.ClientInformationDTO;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.models.Client;
import ru.khaimin.springcourse.models.Dish;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.services.ClientService;
import ru.khaimin.springcourse.services.CustomerService;
import ru.khaimin.springcourse.services.DishService;
import ru.khaimin.springcourse.services.OrderService;

import java.util.ArrayList;
import java.util.List;

// Класс контроллер для домашней страницы

@Controller
public class IndexController {

    private final DishService dishService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ClientService  clientService;

    @Autowired
    public IndexController(DishService dishService_, CustomerService customerService_, OrderService orderService_,
                           ClientService clientService_) {
        this.dishService = dishService_;
        this.customerService = customerService_;
        this.orderService = orderService_;
        this.clientService = clientService_;
    }

    // Получаю все блюда из базы данных и передаю их используя объекты DishDTO в представление
    @GetMapping("/")
    public String showMenu(Model model) {

        // Не знаю, можно ли напрямую к сервисам обращаться в контроллерах. Буду благодарен за комментарий
        List<Dish> dishes = dishService.findAllDishes();
        List<DishDTO> dishDTOS = new ArrayList<>();

        for (Dish dish : dishes) {
            DishDTO dishDTO = new DishDTO();

            dishDTO.setId(dish.getId());
            dishDTO.setName(dish.getName());
            dishDTO.setPrice(dish.getPrice());

            dishDTOS.add(dishDTO);
        }

        model.addAttribute("dishDTOS", dishDTOS);

        return "index";
    }

    @PostMapping("/transferSelections")
    public String transferSelections(@RequestParam("selectedDishes") List<Integer> idSelectedDishes, Model model,
                                     RedirectAttributes redirectAttributes) {
        Client client = clientService.getClientById(1);
        Order order = orderService.createOrderByIdSelectedDishes(idSelectedDishes, client);

        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        clientInformationDTO.setId(client.getId());
        clientInformationDTO.setName(client.getName());

        if (customerService.placeOrder(order)) {
            redirectAttributes.addFlashAttribute("clientInformationDTO",  clientInformationDTO);
            return "redirect:/order_waiting";
        } else {
            return "redirect:/order_error";
        }
    }

}

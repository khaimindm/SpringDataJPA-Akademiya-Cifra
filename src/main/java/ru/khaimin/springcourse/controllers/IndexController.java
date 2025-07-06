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
import ru.khaimin.springcourse.dto.OrderDTO;
import ru.khaimin.springcourse.dto.OrderDetailDTO;
import ru.khaimin.springcourse.mapper.ClientMapper;
import ru.khaimin.springcourse.mapper.DishMapper;
import ru.khaimin.springcourse.mapper.OrderMapper;
import ru.khaimin.springcourse.models.Client;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.services.ClientService;
import ru.khaimin.springcourse.services.DishService;
import ru.khaimin.springcourse.services.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Класс контроллер для домашней страницы

@Controller
public class IndexController {

    private final DishService dishService;
    private final ClientService clientService;
    private final OrderService orderService;
    private final ClientMapper clientMapper;
    private final DishMapper dishMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public IndexController(DishService dishService_, ClientService clientService_, OrderService orderService_,
                           ClientMapper clientMapper_, DishMapper dishMapper_,
                           OrderMapper orderMapper_) {
        this.dishService = dishService_;
        this.clientService = clientService_;
        this.orderService = orderService_;
        this.clientMapper = clientMapper_;
        this.dishMapper = dishMapper_;
        this.orderMapper = orderMapper_;
    }

    // Получаю все блюда из базы данных и передаю их используя объекты DishDTO в представление
    @GetMapping("/")
    public String showMenu(Model model) {

        // Не знаю, можно ли напрямую к сервисам обращаться в контроллерах. Буду благодарен за комментарий
        List<DishDTO> dishDTOS = dishMapper.toDishDTOS(dishService.findAllDishes());
        model.addAttribute("dishDTOS", dishDTOS);
        return "index";
    }

    @PostMapping("/transferSelections")
    public String transferSelections(@RequestParam("selectedDishes") List<Integer> idSelectedDishes,
                                     @RequestParam Map<String, String> dishesAndQuantityParams,
                                     RedirectAttributes redirectAttributes) {
        Client client = clientMapper.toClient(clientService.getClientEntityById(1));

        // Для конвертации Map<String, String> в  Map<Integer, Integer>
        Map<Integer, Integer> dishesAndQuantity = new HashMap<>();

        for (Integer idSelectedDish : idSelectedDishes) {
            String quantityParam = dishesAndQuantityParams.get("quantity_" + idSelectedDish);
            int quantity = 1;

            try {
                quantity = Integer.parseInt(quantityParam);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            dishesAndQuantity.put(idSelectedDish, quantity);
        }

        Order order = orderService.createOrderByIdSelectedDishes(idSelectedDishes, client, dishesAndQuantity);

        ClientInformationDTO clientInformationDTO = clientMapper.toClientInformationDTO(client);

        // Получаю список всех объектов DishDTO в данном заказе
        List<DishDTO> dishDTOS = dishMapper.toDishDTOS(order.getDishes());

        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for (DishDTO dishDTO : dishDTOS) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setDishDTO(dishDTO);
            orderDetailDTO.setPrice(dishDTO.getPrice());

            // Устанавливаю в OrderDetailDTO количество, получая из Map<Integer, Integer> dishesAndQuantity по id блюда
            orderDetailDTO.setQuantity(dishesAndQuantity.get(dishDTO.getId()));
            orderDetailDTOS.add(orderDetailDTO);
        }

        // Получаю объект OrderDTO из Order
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);
        orderDTO.setClientInformationDTO(clientInformationDTO);

        if (clientService.placeOrder(order)) {
            redirectAttributes.addFlashAttribute("orderDTO", orderDTO);
            redirectAttributes.addFlashAttribute("orderDetailDTOS", orderDetailDTOS);
            return "redirect:/order_waiting";
        } else {
            return "redirect:/order_error";
        }
    }

}

package ru.khaimin.springcourse.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.khaimin.springcourse.dto.ClientDTO;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.mapper.ClientMapper;
import ru.khaimin.springcourse.mapper.DishMapper;
import ru.khaimin.springcourse.mapper.OrderMapper;
import ru.khaimin.springcourse.request.OrderRequest;
import ru.khaimin.springcourse.response.OrderResponse;
import ru.khaimin.springcourse.services.clients.ClientServiceClient;
import ru.khaimin.springcourse.services.clients.OrderServiceClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Класс контроллер для домашней страницы

@Controller
public class IndexController {


    private final ClientMapper clientMapper;
    private final DishMapper dishMapper;
    private final OrderMapper orderMapper;
    private final ClientServiceClient clientServiceClient;
    private final OrderServiceClient orderServiceClient;

    @Autowired
    public IndexController(ClientMapper clientMapper_, DishMapper dishMapper_,
                           OrderMapper orderMapper_, ClientServiceClient clientServiceClient_,
                           OrderServiceClient orderServiceClient_) {
        this.clientMapper = clientMapper_;
        this.dishMapper = dishMapper_;
        this.orderMapper = orderMapper_;
        this.clientServiceClient = clientServiceClient_;
        this.orderServiceClient = orderServiceClient_;
    }

    // Получаю все блюда от OrderService и передаю их используя объекты DishDTO в представление
    @GetMapping("/")
    public String showMenu(Model model) {

        List<DishDTO> dishDTOS = orderServiceClient.getAllDishDTOS();
        model.addAttribute("dishDTOS", dishDTOS);
        return "index";
    }

    @PostMapping("/transferSelections")
    public String transferSelections(@RequestParam("selectedDishes") List<Integer> idSelectedDishes,
                                     @RequestParam Map<String, String> dishesAndQuantityParams,
                                     RedirectAttributes redirectAttributes) {

        // Запрашиваю ClientDTO из ClientService
        ClientDTO clientDTO = clientServiceClient.getClientDTOByClientId("1");
//        Client client = clientMapper.toClientFromClientDTO(clientDTO);

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

        ObjectMapper objectMapper = new ObjectMapper();

        String idSelectedDishesJson = null;
        try {
            // Преобразую список в JSON
            idSelectedDishesJson = objectMapper.writeValueAsString(idSelectedDishes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String dishesAndQuantityJson = "";
        try {
            // Преобразую Map в JSON
            dishesAndQuantityJson = objectMapper.writeValueAsString(dishesAndQuantity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdSelectedDishesJson(idSelectedDishesJson);
        orderRequest.setClientIdString(Integer.toString(clientDTO.getId()));
        orderRequest.setClientName(clientDTO.getName());
        orderRequest.setDishesAndQuantityJson(dishesAndQuantityJson);

        List<OrderResponse> orderResponses = orderServiceClient.processOrder(orderRequest);

        if (orderResponses != null) {
            redirectAttributes.addFlashAttribute("clientDTO", clientDTO);
            redirectAttributes.addFlashAttribute("orderResponses", orderResponses);
            return "redirect:/order";
        } else {
            return "redirect:/order_error";
        }
    }
}

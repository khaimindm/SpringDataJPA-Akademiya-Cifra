package ru.khaimin.springcourse.dto;

// Класс заказа DTO

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private int id;
    private Date date;
    private boolean readiness;
    private double orderFullCost;
    private ClientDTO clientDTO;
    private List<DishDTO> dishDTOS;

}

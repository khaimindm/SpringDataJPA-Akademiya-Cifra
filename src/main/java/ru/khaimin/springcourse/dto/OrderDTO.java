package ru.khaimin.springcourse.dto;

// Класс заказа DTO

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDTO {

    private Date date;
    private double orderFullCost;
    private ClientInformationDTO clientInformationDTO;

}

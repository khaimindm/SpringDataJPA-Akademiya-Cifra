package ru.khaimin.springcourse.dto;

import lombok.Getter;
import lombok.Setter;

// Класс блюда DTO

@Getter
@Setter
public class DishDTO {

    private int id;
    private String name;
    private double price;

}

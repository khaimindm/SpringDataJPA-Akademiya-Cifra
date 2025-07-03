package ru.khaimin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Бизнес-сущность OrderDetail

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {

    private int id;
    private Order order;
    private Dish dish;
    private int quantity;

}

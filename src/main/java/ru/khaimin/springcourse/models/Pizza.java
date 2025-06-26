package ru.khaimin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Класс бизнес-сущности Pizza

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pizza implements Dish {

    private int id;
    private String name;
    private double price;
    private String dType;

}

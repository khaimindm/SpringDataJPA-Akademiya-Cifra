package ru.khaimin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Класс бизнес-сущности Sushi

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sushi implements Dish {

    private int id;
    private String name;
    private double price;
    private String dType;

}

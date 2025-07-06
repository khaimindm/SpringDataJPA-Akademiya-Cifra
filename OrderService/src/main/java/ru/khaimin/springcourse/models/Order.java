package ru.khaimin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

//Бизнес-сущность Order

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    private int id;
    private Date date;
    private boolean readiness;
    private double orderFullCost;

    private Client client;

    private List<OrderDetail> orderDetails;

    private List<Dish> dishes;

}

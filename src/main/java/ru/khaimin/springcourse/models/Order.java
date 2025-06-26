package ru.khaimin.springcourse.models;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

// Класс бизнес-сущности Order

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_dish")
public class Order {

    private int id;
    private Date date;
    private boolean readiness;
    private double orderFullCost;

    private Client client;

    private OrderDetail orderDetail;

    private List<Dish> dishes;

}

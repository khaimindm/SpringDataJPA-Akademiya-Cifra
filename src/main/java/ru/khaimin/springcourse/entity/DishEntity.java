package ru.khaimin.springcourse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Entity сущность для таблицы dish

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "dish")
public class DishEntity {

    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dish_name")
    private String name;

    @Column(name = "dish_price")
    private double price;

    @Column(name = "dtype")
    private String dType;

    @OneToOne(mappedBy = "dishEntity")
    private OrderDetailEntity orderDetailEntity;


}

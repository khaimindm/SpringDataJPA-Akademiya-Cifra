package ru.khaimin.springcourse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Абстрактный класс для реализации стратегии Одна таблица, для обработки наследования

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "dish")
public abstract class AbstractDish implements Dish {

    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dish_name")
    private String name;

    @Column(name = "dish_price")
    private double price;

    @OneToOne(mappedBy = "dish")
    private OrderDetail orderDetail;

    public AbstractDish(String name, double price) {

    }
}

package ru.khaimin.springcourse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

// Entity сущность для таблицы order_dish
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_dish")
public class OrderDishEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date")
    private Date date;

    @Column(name = "order_readiness")
    private boolean readiness;

    @Column(name = "order_full_cost")
    private double orderFullCost;

    @OneToMany(mappedBy = "orderDishEntity")
    private List<OrderDetailEntity> orderDetailEntities;

    @ManyToOne
    @JoinColumn(name = "order_dish_client_id", referencedColumnName = "client_id")
    private ClientEntity clientEntity;
}

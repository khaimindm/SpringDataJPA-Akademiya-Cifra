package ru.khaimin.springcourse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Entity сущность для таблицы order_detail. В которой хранится дополнительная информация о заказах

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_detail_order_dish_order_id", referencedColumnName = "order_id")
    private OrderDishEntity orderDishEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_detail_dish_id", referencedColumnName = "dish_id")
    private DishEntity dishEntity;

    @Column(name = "quantity")
    private int quantity;

}

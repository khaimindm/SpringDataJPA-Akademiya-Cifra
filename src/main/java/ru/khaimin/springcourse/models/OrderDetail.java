package ru.khaimin.springcourse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Класс для таблицы order_detail. В которой хранится дополнительная информация о заказах

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_detail_order_dish_order_id", referencedColumnName = "order_id")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_detail_dish_id", referencedColumnName = "dish_id")
    private AbstractDish dish;

}

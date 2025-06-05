package ru.khaimin.springcourse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

// Класс заказа

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_dish")
public class Order {

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

    @ManyToOne
    @JoinColumn(name = "order_dish_client_id", referencedColumnName = "client_id")
    private Client client;

    @OneToMany (mappedBy = "", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    @ElementCollection
    private List<AbstractDish> items;

    public String getDishes() {
        StringBuilder stringBuffer = new StringBuilder();

        for (Dish dish: items) {
            stringBuffer.append(dish.getName()).append("; ");
        }

        return stringBuffer.toString();
    }

}

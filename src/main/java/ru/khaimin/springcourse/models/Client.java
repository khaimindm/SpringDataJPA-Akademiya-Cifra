package ru.khaimin.springcourse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "client")

// Класс клиента
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_name")
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;

}

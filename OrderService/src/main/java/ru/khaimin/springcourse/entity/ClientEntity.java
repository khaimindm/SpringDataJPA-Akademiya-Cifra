//package ru.khaimin.springcourse.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//// Entity сущность для таблицы client
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(name = "client")
//public class ClientEntity {
//
//    @Id
//    @Column(name = "client_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "client_name")
//    private String name;
//
//    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
//    private List<OrderDishEntity> orderDishEntities;
//
//}

package ru.khaimin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Бизнес-сущность Client

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    private int id;
    private String name;

}

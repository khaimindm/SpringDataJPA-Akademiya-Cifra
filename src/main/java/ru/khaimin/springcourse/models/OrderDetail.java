package ru.khaimin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

// Бизнес-сущность OrderDetail

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {

    Map<String, Integer> orderDetail;

}

package ru.khaimin.springcourse.models;

public interface Dish {

    // constructor, getters
    int getId();

    String getName();

    double getPrice();

    String getDType();

    void setId(int id);

    void setName(String name);

    void setPrice(double price);

    void setDType(String dType);
}

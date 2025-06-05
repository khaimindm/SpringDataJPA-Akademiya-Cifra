package ru.khaimin.springcourse.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("pizza")
public class Pizza extends AbstractDish {

    @Column(name = "dtype")
    private String dtype;

    public Pizza() {
    }

    public Pizza(String name_, double price_) {
        super(name_, price_);
    }
}

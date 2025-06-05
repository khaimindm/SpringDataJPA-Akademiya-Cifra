package ru.khaimin.springcourse.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("japanese")
public class Sushi extends AbstractDish {

    @Column(name = "dtype")
    private String dtype;

}

package ru.khaimin.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcourseApplication.class, args);

        System.out.println("Prilozhenie vypolnyaetsya");
    }

}
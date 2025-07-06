package ru.khaimin.springcourse.dto;

import lombok.Getter;
import lombok.Setter;

// DTO объект для информации о клиенте

@Getter
@Setter
public class ClientInformationDTO {

    private int id;
    private String name;

}

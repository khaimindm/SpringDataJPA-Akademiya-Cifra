package ru.khaimin.springcourse.controllers;

// Класс контроллер для клиента в микросервисе

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khaimin.springcourse.dto.ClientDTO;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.mapper.ClientMapper;
import ru.khaimin.springcourse.services.ClientServiceMicroservice;

@RestController
@RequestMapping("/client")
public class ClientMicroserviceController {

    private final ClientServiceMicroservice clientServiceMicroservice;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientMicroserviceController(ClientServiceMicroservice clientServiceMicroservice_,
                                        ClientMapper clientMapper_) {
        this.clientServiceMicroservice = clientServiceMicroservice_;
        this.clientMapper = clientMapper_;
    }

    @GetMapping("/{clientId}")
    public ClientDTO getClientDTOByClientId(@PathVariable String clientId) {

        int clientIdInt =0;

        try {
            clientIdInt = Integer.parseInt(clientId);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        ClientEntity clientEntity = clientServiceMicroservice.getClientEntityById(clientIdInt);

        return clientMapper.toClientDTO(clientEntity);
    }
}

package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.repositories.ClientRepository;

import java.util.Optional;

// Микросервис клиент

@Service
public class ClientServiceMicroservice {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceMicroservice(ClientRepository clientRepository_) {
        this.clientRepository = clientRepository_;
    }

    public ClientEntity getClientEntityById(int id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        return clientEntity.orElse(null);
    }
}

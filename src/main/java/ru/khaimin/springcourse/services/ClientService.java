package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.models.Client;
import ru.khaimin.springcourse.repositories.ClientRepository;

import java.util.Optional;

// Сервис клиентов

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository_) {
        this.clientRepository = clientRepository_;
    }

    public ClientEntity getClientEntityById(int id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        return clientEntity.orElse(null);
    }

    public Client getClientById(int id) {
        ClientEntity clientEntity = getClientEntityById(id);

        return new Client(clientEntity.getId(), clientEntity.getName());
    }
}

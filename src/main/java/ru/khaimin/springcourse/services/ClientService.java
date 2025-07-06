package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.kitchen.PizzaKitchen;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.repositories.ClientRepository;

import java.util.Optional;

// Сервис клиентов

@Service
public class ClientService {

    private final PizzaKitchen pizzaKitchen;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(PizzaKitchen pizzaKitchen_, ClientRepository clientRepository_) {
        this.pizzaKitchen = pizzaKitchen_;
        this.clientRepository = clientRepository_;
    }

    public boolean placeOrder(Order order) {
        if (pizzaKitchen.canAcceptOrder()) {
            pizzaKitchen.prepareOrder(order);
            return true;
        } else {
            return false;
        }
    }

    public ClientEntity getClientEntityById(int id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        return clientEntity.orElse(null);
    }
}

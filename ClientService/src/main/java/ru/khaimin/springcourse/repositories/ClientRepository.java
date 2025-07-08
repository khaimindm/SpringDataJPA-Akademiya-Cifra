package ru.khaimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khaimin.springcourse.entity.ClientEntity;

// Репозиторий для клиентов

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}

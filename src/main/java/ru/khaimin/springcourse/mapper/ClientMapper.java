package ru.khaimin.springcourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.khaimin.springcourse.dto.ClientInformationDTO;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.models.Client;

import java.util.List;

// Интерфейс для маппинга Client -> ClientInformationDTO

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientInformationDTO toClientInformationDTO(Client client);

    List<ClientInformationDTO> toClientInformationDTOS(List<Client> clients);

    Client toClient(ClientEntity clientEntity);

    List<Client> toClients(List<ClientEntity> clientEntities);

}

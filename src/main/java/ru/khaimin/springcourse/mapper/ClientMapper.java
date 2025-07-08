package ru.khaimin.springcourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.khaimin.springcourse.dto.ClientDTO;
import ru.khaimin.springcourse.models.Client;

import java.util.List;

// Интерфейс для маппинга Client -> ClientInformationDTO

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientDTO toClientDTO(Client client);

    List<ClientDTO> toClientDTOS(List<Client> clients);

    Client toClientFromClientDTO(ClientDTO clientDTO);

    List<Client> toClientsFromClientDTOS(List<ClientDTO> clientDTOS);

}

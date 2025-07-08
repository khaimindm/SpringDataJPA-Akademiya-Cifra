package ru.khaimin.springcourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.khaimin.springcourse.dto.ClientDTO;
import ru.khaimin.springcourse.entity.ClientEntity;

import java.util.List;

// Интерфейс для маппинга ClientEntity -> ClientDTO

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientDTO toClientDTO(ClientEntity clientEntity);

    List<ClientDTO> toClientDTOS(List<ClientEntity> clientEntities);

}

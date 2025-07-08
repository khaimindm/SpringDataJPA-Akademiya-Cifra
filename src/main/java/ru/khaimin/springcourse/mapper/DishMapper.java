package ru.khaimin.springcourse.mapper;

// Интерфейс для маппинга Dish -> DishDTO

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.models.Dish;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishMapper {

    DishDTO toDishDTO(Dish dish);

    List<DishDTO> toDishDTOS(List<Dish> dishes);

}

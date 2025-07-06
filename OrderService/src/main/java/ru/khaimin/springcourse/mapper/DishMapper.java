package ru.khaimin.springcourse.mapper;

// Интерфейс для маппинга Dish -> DishDTO

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.khaimin.springcourse.dto.DishDTO;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.models.Dish;
import ru.khaimin.springcourse.models.Pizza;
import ru.khaimin.springcourse.models.Sushi;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishMapper {

    DishDTO toDishDTO(Dish dish);

    List<DishDTO> toDishDTOS(List<Dish> dishes);

    Pizza toPizza(DishEntity dishEntity);

    List<Pizza> toPizzas(List<DishEntity> dishEntities);

    Sushi toSushi(DishEntity dishEntity);

    List<Sushi> toSushiList(List<DishEntity> dishEntities);

}

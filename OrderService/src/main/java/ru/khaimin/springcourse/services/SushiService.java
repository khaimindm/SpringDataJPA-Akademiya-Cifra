package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.mapper.DishMapper;
import ru.khaimin.springcourse.models.Sushi;
import ru.khaimin.springcourse.repositories.DishRepository;

import java.util.List;

// Сервис для суши

@Service
public class SushiService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Autowired
    public SushiService(DishRepository dishRepository, DishMapper dishMapper_) {
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper_;
    }

    @Transactional(readOnly = true)
    public List<Sushi> findAllSushi() {
        List<DishEntity> dishEntities = dishRepository.findByDType("japanese");

        return dishMapper.toSushiList(dishEntities);
    }
}

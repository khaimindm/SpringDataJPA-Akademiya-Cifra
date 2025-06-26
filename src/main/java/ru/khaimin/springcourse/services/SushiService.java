package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.entity.DishEntity;
import ru.khaimin.springcourse.models.Sushi;
import ru.khaimin.springcourse.repositories.DishRepository;

import java.util.ArrayList;
import java.util.List;

// Сервис для суши
@Service
@Transactional(readOnly = true)
public class SushiService {

    private final DishRepository dishRepository;

    @Autowired
    public SushiService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Transactional(readOnly = true)
    public List<Sushi> findAllSushi() {
        List<DishEntity> dishEntities = dishRepository.findByDType("japanese");
        List<Sushi> sushi = new ArrayList<>();

        for (DishEntity dishEntity : dishEntities) {
            Sushi sushiTemp = new Sushi();

            sushiTemp.setId(dishEntity.getId());
            sushiTemp.setName(dishEntity.getName());
            sushiTemp.setPrice(dishEntity.getPrice());

            sushi.add(sushiTemp);
        }

        return sushi;
    }
}

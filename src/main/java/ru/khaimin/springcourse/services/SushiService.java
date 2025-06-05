package ru.khaimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khaimin.springcourse.models.Sushi;
import ru.khaimin.springcourse.repositories.SushiRepository;

import java.util.List;

// Сервис для суши
@Service
@Transactional(readOnly = true)
public class SushiService {

    private final SushiRepository sushiRepository;

    @Autowired
    public SushiService(SushiRepository sushiRepository) {
        this.sushiRepository = sushiRepository;
    }

    public List<Sushi> findAllSushi() {
        return sushiRepository.findAllByDtype("japanese");
    }

}

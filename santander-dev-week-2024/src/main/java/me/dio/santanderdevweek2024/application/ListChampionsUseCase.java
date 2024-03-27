package me.dio.santanderdevweek2024.application;


import me.dio.santanderdevweek2024.domain.model.Champions;
import me.dio.santanderdevweek2024.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository repository){
    public List<Champions> findAll(){
        return repository.findAll();
    }
}

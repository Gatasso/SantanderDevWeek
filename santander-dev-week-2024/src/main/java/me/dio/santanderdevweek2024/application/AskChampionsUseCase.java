package me.dio.santanderdevweek2024.application;

import me.dio.santanderdevweek2024.domain.exception.ChampionNotFoundException;
import me.dio.santanderdevweek2024.domain.model.Champions;
import me.dio.santanderdevweek2024.domain.ports.ChampionsRepository;

import java.util.List;


public record AskChampionsUseCase(ChampionsRepository repository) {
    public String askChampion(Long championId, String question) {
        Champions champion = repository.findByID(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        //TODO: Evoluir o retorno integração com IAs Generativas.

        return champion.generateContextByQuestion(question);
    }
}

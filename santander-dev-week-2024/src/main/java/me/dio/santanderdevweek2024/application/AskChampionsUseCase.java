package me.dio.santanderdevweek2024.application;

import me.dio.santanderdevweek2024.domain.exception.ChampionNotFoundException;
import me.dio.santanderdevweek2024.domain.model.Champions;
import me.dio.santanderdevweek2024.domain.ports.ChampionsRepository;
import me.dio.santanderdevweek2024.domain.ports.GenerativeAI_Service;

import java.util.List;


public record AskChampionsUseCase(ChampionsRepository repository, GenerativeAI_Service generativeAI_API) {
    public String askChampion(Long championId, String question) {
        Champions champion = repository.findByID(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String championContext = champion.generateContextByQuestion(question);
        String objective = """
                Atue como um assistente, se comportando e conversando tais quais os Campeões do League of Legends
                Responda as perguntas incorporando as personalidades, caráter e estilo dos personagens.
                Lembre-se, vocẽ é um aliado, não um inimigo.
                Você receberá a pergunta, o nome do Campeão e sua respectiva lore(história)
                """;

        return generativeAI_API.generateContent(objective, championContext);
    }
}

package me.dio.santanderdevweek2024.adapters.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.santanderdevweek2024.application.AskChampionsUseCase;
import me.dio.santanderdevweek2024.application.ListChampionsUseCase;
import me.dio.santanderdevweek2024.domain.model.Champions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Campeões", description = "Endpoint do domínio de Campeões")
@RestController
@RequestMapping("/champions")
public record AskChampionsRestController(AskChampionsUseCase useCase) {

    @PostMapping("/{championId}/ask")
    public AskChampionResponse askChampion(@PathVariable Long championId, @RequestBody AskChampionRequest request) {

        String answer = useCase.askChampion(championId, request.question());

        return new AskChampionResponse(answer);
    }

    public record AskChampionRequest(String question){}
    public record AskChampionResponse(String answer){}
}

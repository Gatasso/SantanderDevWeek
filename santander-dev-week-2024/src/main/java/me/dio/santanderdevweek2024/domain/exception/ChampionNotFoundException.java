package me.dio.santanderdevweek2024.domain.exception;

import static org.yaml.snakeyaml.nodes.Tag.STR;

public class ChampionNotFoundException extends RuntimeException {
    public ChampionNotFoundException(Long championId) {
        super("Champion %d not found".formatted(championId));
    }
}

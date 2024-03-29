package me.dio.santanderdevweek2024.domain.model;

public record Champions(
        Long id,
        String name,
        String role,
        String lore,
        String imageURL
) {
    public String generateContextByQuestion(String question) {
        return """
                Pergunta: %s
                Nome do campeão: %s
                Função: %s       \s
                Lore: %s       \s
                """.formatted(question, this.name, this.role, this.lore);
    }
}

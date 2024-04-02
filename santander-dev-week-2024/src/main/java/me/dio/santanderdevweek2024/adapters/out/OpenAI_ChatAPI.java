package me.dio.santanderdevweek2024.adapters.out;

import feign.RequestInterceptor;
import me.dio.santanderdevweek2024.domain.ports.GenerativeAI_Service;
import net.sf.jsqlparser.statement.select.First;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.util.List;


@FeignClient(name = "openAiChatApi", url = "${openai.base-url}", configuration = OpenAI_ChatAPI.Config.class)
public interface OpenAI_ChatAPI extends GenerativeAI_Service {

    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResponse chatCompletion(OpenAiChatCompletionRequest request);

    @Override
    default String generateContent(String objective, String context) {
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
                new Message("system", objective),
                new Message("user", context)
        );
        OpenAiChatCompletionRequest request = new OpenAiChatCompletionRequest(model, messages);
        OpenAiChatCompletionResponse response = chatCompletion(request);

        return response.choices().get(0).message().content();
    }

    record OpenAiChatCompletionRequest(String model, List<Message> messages) {
    }

    record Message(String role, String content) {
    }

    record OpenAiChatCompletionResponse(List<Choice> choices) {
    }

    record Choice(Message message) {
    }

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.header(
                    HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey));
        }
    }
}

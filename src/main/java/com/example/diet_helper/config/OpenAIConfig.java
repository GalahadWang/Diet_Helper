package com.example.diet_helper.config;

import com.theokanning.openai.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {
    @Value("Your own OpenAI Token")
    private String openaiToken;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openaiToken, 5000);
    }
}

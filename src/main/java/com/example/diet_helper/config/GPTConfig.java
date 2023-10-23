package com.example.diet_helper.config;

import com.example.diet_helper.service.impl.GPT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GPTConfig {
    @Bean
    public GPT gpt() {
        return new GPT();
    }
}

package com.example.diet_helper.service.impl;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPTService {
    @Autowired
    private OpenAiService openAiService;

    public String getFromGPT(String prompt) {
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(1000)
                .build();

        List<CompletionChoice> choices = openAiService.createCompletion(request).getChoices();
        if (!choices.isEmpty()) {
            return choices.get(0).getText().trim();
        }
        return "未能获取建议。";
    }
    public String getQuestion(String prompt){
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(1000)
                .build();
        List<CompletionChoice> choices = openAiService.createCompletion(request).getChoices();
        if (!choices.isEmpty()) {
            return choices.get(0).getText().trim();
        }
        return "未能获取建议。";
    }
}

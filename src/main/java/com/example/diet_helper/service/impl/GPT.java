package com.example.diet_helper.service.impl;

import com.example.diet_helper.pojo.dto.User;
import com.example.diet_helper.pojo.vo.request.CommonQuestionVo;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GPT {
    @Autowired
    private OpenAiService openAiService;

    private final Random random = new Random();

    public String AnswerQuestion(CommonQuestionVo commonQuestionVo){
        String prompt = "From the standard of ordinary people, answer the question:"+commonQuestionVo.getQuestion()+
                " based on the user's height:"+commonQuestionVo.getHeight()+
                ", weight:"+commonQuestionVo.getWeight()+", age:"+commonQuestionVo.getAge();
//                ", target weight:"+commonQuestionVo.getTargetWeight();
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(100)
                .temperature(0.7)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    public String EncourageSenctence(){
        String prompt = "Please generate a sentence that encourages people to live a healthy life：\n";

        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(200)
                .temperature(0.7)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    // 生成一道题目的问题文本
    public String generateQuestionText() {
        // 实现逻辑以生成问题文本的提示
        String prompt = "Please only generate one question about healthy eating：\n";

        // 调用ChatGPT生成问题文本
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(200)
                .temperature(0.3)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    // 生成一个选项（A、B、C、D）
    public String generateOption(String questionText, String optionLetter) {
        // 构建提示以生成特定选项
        String prompt = "Please generate only an option" + optionLetter + ", based on this question：\n" + questionText + "\n";

        // 调用ChatGPT生成选项
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(100)
                .temperature(0.3)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    // 生成正确答案（D）
    public String generateCorrectOption() {
        // 从ABCD四个选项中随机选择一个作为正确答案
        String[] options = new String[] {"A", "B", "C", "D"};
        String correctOption = options[random.nextInt(options.length)];
        return correctOption;
    }

    // 生成一道完整的选择题
    public String generateMultipleChoiceQuestion() {
        // 生成问题文本
        String questionText = generateQuestionText();

        // 生成选项 A、B、C
        String optionA = generateOption(questionText, "A");
        String optionB = generateOption(questionText, "B");
        String optionC = generateOption(questionText, "C");
        String optionD = generateOption(questionText, "D");
        // 生成正确答案
        String correctOption = generateCorrectOption();

        // 构建完整的选择题
        String multipleChoiceQuestion = "问题：" + questionText + "\n" +
                "A. " + optionA + "\n" +
                "B. " + optionB + "\n" +
                "C. " + optionC + "\n" +
                "D. " + optionC + "\n" +
                "The Correct is that" + correctOption;

        return multipleChoiceQuestion;
    }

    // 生成多道选择题
    public List<String> generateMultipleChoiceQuestions(int num) {
        List<String> questions = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            questions.add(generateMultipleChoiceQuestion());
        }
        return questions;
    }
}
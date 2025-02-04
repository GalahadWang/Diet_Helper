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

    // Generate problem text for a topic
    public String generateQuestionText() {
        // Implement the logic to generate a prompt for the problem text
        String prompt = "Please only generate one question about healthy eating：\n";

        // Call ChatGPT to generate question text
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(200)
                .temperature(0.3)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    // Generate an option (A, B, C, D)
    public String generateOption(String questionText, String optionLetter) {
        // Build prompts to generate specific options
        String prompt = "Please generate only an option" + optionLetter + ", based on this question：\n" + questionText + "\n";

        // Call the ChatGPT build option
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(100)
                .temperature(0.3)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    // Generate the correct answer
    public String generateCorrectOption() {
        String[] options = new String[] {"A", "B", "C", "D"};
        String correctOption = options[random.nextInt(options.length)];
        return correctOption;
    }

    // Generate a complete multiple choice question
    public String generateMultipleChoiceQuestion() {
        // Generate problem text
        String questionText = generateQuestionText();

        // Generate problem text
        String optionA = generateOption(questionText, "A");
        String optionB = generateOption(questionText, "B");
        String optionC = generateOption(questionText, "C");
        String optionD = generateOption(questionText, "D");
        // Generate options A, B, C
        String correctOption = generateCorrectOption();

        // Construct complete multiple choice questions
        String multipleChoiceQuestion = "问题：" + questionText + "\n" +
                "A. " + optionA + "\n" +
                "B. " + optionB + "\n" +
                "C. " + optionC + "\n" +
                "D. " + optionC + "\n" +
                "The Correct is that" + correctOption;

        return multipleChoiceQuestion;
    }

    // Construct complete multiple choice questions
    public List<String> generateMultipleChoiceQuestions(int num) {
        List<String> questions = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            questions.add(generateMultipleChoiceQuestion());
        }
        return questions;
    }
}
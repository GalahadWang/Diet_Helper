package com.example.diet_helper.service.impl;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//public class GPT {
//    @Autowired
//    private static OpenAiService openAiService;
//
//    public static String generateOptionE(String userInfo, String optionA, String optionB, String optionC,String optionD) {
//        // 构建GPT-3请求的prompt，包括用户信息和选项信息
//        String prompt = userInfo + "Here are the options:\n" +
//                "A. " + optionA + "\n" +
//                "B. " + optionB + "\n" +
//                "C. " + optionC + "\n" +
//                "D. " + optionC + "\n" +
//                "E. ";
//
//        // 使用GPT-3生成选项D
//        CompletionRequest request = CompletionRequest.builder()
//                .model("gpt-3.5-turbo-instruct")
//                .prompt(prompt)
//                .maxTokens(1)  // 生成一个单词，即选项D
//                .build();
//
//        List<CompletionChoice> choices = openAiService.createCompletion(request).getChoices();
//        if (!choices.isEmpty()) {
//            return choices.get(0).getText().trim();
//        }
//        return "未能生成选项E。";
//    }
//}

//public class GPT {
//    @Autowired
//    private static OpenAiService openAiService;
//
//    public static String generateQuestionText() {
//        // Construct a prompt for generating the question text in Chinese
//        String prompt = "生成一个关于健康的多选题问题：\n";
//
//        // Implement the logic to call ChatGPT to generate the question text in Chinese
//        CompletionRequest request = CompletionRequest.builder()
//                .model("text-davinci-002")  // Use the appropriate GPT-3 model
//                .prompt(prompt)
//                .maxTokens(50)  // Adjust the token limit as needed
//                .temperature(0.7)  // Adjust the temperature parameter as needed
//                .build();
//
//        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
//    }
//
//    public static String generateOptionA(String questionText) {
//        // Construct a prompt for generating option A in Chinese based on the provided question text
//        String prompt = "生成选项A，基于以下问题：\n" + questionText + "\n";
//
//        // Implement the logic to call ChatGPT to generate option A in Chinese
//        CompletionRequest request = CompletionRequest.builder()
//                .model("text-davinci-002")  // Use the appropriate GPT-3 model
//                .prompt(prompt)
//                .maxTokens(20)  // Adjust the token limit as needed
//                .temperature(0.7)  // Adjust the temperature parameter as needed
//                .build();
//
//        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
//    }
//
//    public static String generateOptionB(String questionText) {
//        // Construct a prompt for generating option B in Chinese based on the provided question text
//        String prompt = "生成选项B，基于以下问题：\n" + questionText + "\n";
//
//        // Implement the logic to call ChatGPT to generate option B in Chinese
//        CompletionRequest request = CompletionRequest.builder()
//                .model("text-davinci-002")  // Use the appropriate GPT-3 model
//                .prompt(prompt)
//                .maxTokens(20)  // Adjust the token limit as needed
//                .temperature(0.7)  // Adjust the temperature parameter as needed
//                .build();
//
//        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
//    }
//
//    public static String generateOptionC(String questionText) {
//        // Construct a prompt for generating option C in Chinese based on the provided question text
//        String prompt = "生成选项C，基于以下问题：\n" + questionText + "\n";
//
//        // Implement the logic to call ChatGPT to generate option C in Chinese
//        CompletionRequest request = CompletionRequest.builder()
//                .model("text-davinci-002")  // Use the appropriate GPT-3 model
//                .prompt(prompt)
//                .maxTokens(20)  // Adjust the token limit as needed
//                .temperature(0.7)  // Adjust the temperature parameter as needed
//                .build();
//
//        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
//    }
//
//    public static String generateOptionD(String questionText, String optionA, String optionB, String optionC) {
//        // Construct a prompt for generating option D in Chinese based on the provided question text and options A, B, and C
//        String prompt = "生成选项D，基于以下问题和选项：\n" +
//                questionText + "\n" +
//                "选项A：" + optionA + "\n" +
//                "选项B：" + optionB + "\n" +
//                "选项C：" + optionC + "\n";
//
//        // Implement the logic to call ChatGPT to generate option D in Chinese
//        CompletionRequest request = CompletionRequest.builder()
//                .model("text-davinci-002")  // Use the appropriate GPT-3 model
//                .prompt(prompt)
//                .maxTokens(20)  // Adjust the token limit as needed
//                .temperature(0.7)  // Adjust the temperature parameter as needed
//                .build();
//
//        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
//    }    public static String generateOptionD(String questionText) {
//        // Construct a prompt for generating option C in Chinese based on the provided question text
//        String prompt = "生成选项D，基于以下问题：\n" + questionText + "\n";
//
//        // Implement the logic to call ChatGPT to generate option C in Chinese
//        CompletionRequest request = CompletionRequest.builder()
//                .model("text-davinci-002")  // Use the appropriate GPT-3 model
//                .prompt(prompt)
//                .maxTokens(20)  // Adjust the token limit as needed
//                .temperature(0.7)  // Adjust the temperature parameter as needed
//                .build();
//
//        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
//    }
//
//    public static String generateCorrectOption(String questionText, String optionA, String optionB, String optionC, String optionD) {
//        // Construct a prompt for generating the correct option (D) in Chinese based on the provided question text and options (A, B, C, D)
//        String prompt = "生成正确答案 (E)，基于以下问题和选项：\n" +
//                questionText + "\n" +
//                "选项A：" + optionA + "\n" +
//                "选项B：" + optionB + "\n" +
//                "选项C：" + optionC + "\n" +
//                "选项D：" + optionD + "\n";
//
//        // Implement the logic to call ChatGPT to generate the correct option (D) in Chinese
//        CompletionRequest request = CompletionRequest.builder()
//                .model("text-davinci-002")  // Use the appropriate GPT-3 model
//                .prompt(prompt)
//                .maxTokens(20)  // Adjust the token limit as needed
//                .temperature(0.7)  // Adjust the temperature parameter as needed
//                .build();
//
//        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
//    }
//}
@Service
public class GPT {
    @Autowired
    private OpenAiService openAiService;

    private final Random random = new Random();

    // 生成一道题目的问题文本
    public String generateQuestionText() {
        // 实现逻辑以生成问题文本的提示
        String prompt = "生成一个有关于健康饮食的问题：\n";

        // 调用ChatGPT生成问题文本
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    // 生成一个选项（A、B、C、D）
    public String generateOption(String questionText, String optionLetter) {
        // 构建提示以生成特定选项
        String prompt = "生成选项" + optionLetter + "，基于以下问题：\n" + questionText + "\n";

        // 调用ChatGPT生成选项
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    // 生成正确答案（D）
    public String generateCorrectOption(String questionText, String optionA, String optionB, String optionC , String optionD) {
        // 从ABCD四个选项中随机选择一个作为正确答案
        String[] options = new String[] {optionA, optionB, optionC, optionD};
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
        String correctOption = generateCorrectOption(questionText, optionA, optionB, optionC,optionD);

        // 构建完整的选择题
        String multipleChoiceQuestion = "问题：" + questionText + "\n" +
                "A. " + optionA + "\n" +
                "B. " + optionB + "\n" +
                "C. " + optionC + "\n" +
                "D. " + correctOption;

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
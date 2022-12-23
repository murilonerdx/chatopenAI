//package com.murilonerdx.basico;
//
//import com.openai.api.Client;
//import com.openai.api.GPT3Request;
//import com.openai.api.GPT3Response;
//import com.openai.api.exception.OpenAIException;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Gpt3Example {
//    public static void main(String[] args) throws IOException, OpenAIException {
//        // Set up the OpenAI API client
//        Client client = new Client("YOUR_API_KEY");
//
//        // Prompt the user for a prompt
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a prompt: ");
//        String prompt = scanner.nextLine();
//        scanner.close();
//
//        // Use the GPT-3 API to generate text based on the prompt
//        GPT3Request request = new GPT3Request.Builder()
//                .model("text-davinci-002")
//                .prompt(prompt)
//                .build();
//        GPT3Response response = client.gpt3().generate(request);
//        String generatedText = response.getData().getText();
//
//        // Display the generated text
//        System.out.println(generatedText);
//    }
//}
//
//
//
//

package com.example.hilel_7_test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class NewsController {

    @GetMapping("/news")
    public String getNews() {

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(
                "https://newsapi.org/v2/everything?q=tesla&from=2024-02-29&sortBy=publishedAt&apiKey=8d35587f6fb44192864a4bfefef1570d",
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder result = new StringBuilder();

        try {
            JsonNode jsonNode = objectMapper.readTree(response);

            JsonNode articles = jsonNode.get("articles");

            for (JsonNode article : articles) {
                String title = article.get("title").asText();
                String description = article.get("description").asText();

                result.append("Title: Glory to Ukraine ").append(title).append("\n");
                result.append("Description: Glory to Ukraine ").append(description).append("\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result.toString();
    }
}
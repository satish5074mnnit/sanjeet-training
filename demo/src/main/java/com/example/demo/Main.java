package com.example.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Array containing parts of the HTML content
        String[] htmlContentArray = {
                "<html>",
                "<body>",
                "<div>",
                "<p>",
                "ElevenLabs is an AI audio research and deployment company. "
                        + "Our mission is to make content universally accessible in any language and in any voice.",
                "</p>",
                "<p>",
                "Our research team develops AI audio models that generate realistic, versatile "
                        + "and contextually-aware speech, voices, and sound effects across 32 languages. "
                        + "Our product team tailors these models to the needs of everyday users, prosumers, and businesses.",
                "</p>",
                "<p>",
                "Our technology is used to voice audiobooks and news articles, "
                        + "animate video game characters, help in film pre-production, "
                        + "localize media in entertainment, create dynamic audio content for social "
                        + "media and advertising, and train medical professionals. "
                        + "It has also given back voices to those who have lost them and helped "
                        + "individuals with accessibility needs in their daily lives.",
                "</p>",
                "<p>",
                "We develop our tools mindful of their impact. AI voices offer a preview "
                        + "into the future of digital interaction and making them safe is our priority. "
                        + "Our goal is to ensure that our products are developed, deployed and "
                        + "used safely, while continuing to drive positive and creative applications.",
                "</p>",
                "</div>",
                "</body>",
                "</html>"
        };

        // File where the HTML will be written
        File file = new File("output_article.html");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write each part of the HTML content from the array to the file
            for (String part : htmlContentArray) {
                writer.write(part);
                writer.newLine();  // Add a new line after each part
            }
            System.out.println("HTML file 'output_article.html' created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//package com.example.demo.kafka;
//
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//public class CreateTopic {
//
//    @Bean
//    public NewTopic createnewTopic(){
//        try {
//            return TopicBuilder.name("topic1234").build();
//        }
//        catch ( Exception e)
//        {
//            System.out.println("NO topic created ");
//        }
//        return null;
//    }
//}

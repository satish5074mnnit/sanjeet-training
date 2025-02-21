//package com.example.demo.kafka;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Producer {
//    private static final Logger loger= LoggerFactory.getLogger(KafkaProducer.class);
//
//    private KafkaTemplate<String ,String> kafkaTemplate;
//
//    public Producer(KafkaTemplate<String,String> kafkaTemplate){
//        this.kafkaTemplate=kafkaTemplate;
//    }
//
//
//    public void publish(String msg){
//        loger.info(String.format("message sent %s",msg));
//        kafkaTemplate.send("topic",msg);
//    }
//
//}

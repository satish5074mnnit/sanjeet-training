//package com.example.demo;
//
//import lombok.extern.slf4j.Slf4j;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisPooled;
//@Slf4j
//public class cache2 {
//    public static void main(String[] args) {
//        HostAndPort address = new HostAndPort("localhost", 6379);
//
//        try (JedisPooled jedis = new JedisPooled(address)) {
//            System.out.println(jedis.get("foo"));
//        }
//    }
//}

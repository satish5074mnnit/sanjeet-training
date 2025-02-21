//package com.example.demo.cacheFile;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPooled;
//
//@RestController
//@Slf4j
//public class API {
//
//    @Autowired
//    @Qualifier("Jedis")
//    JedisPooled jedisPool;
//
//    @PostMapping ("/cachedata")
//    public String dataCache(@RequestParam("data") String data){
//
//        log.info(data);
//        return jedisPool.set(data,"dataset"+data);
//    }
//
//    @GetMapping("/getData")
//    public String getdataCache(@RequestParam("data") String data){
//
//        data=jedisPool.get(data);
//        log.info(data);
//      if(StringUtils.isEmpty(data))throw new RuntimeException("data not found in cache");
//        return data;
//    }
//
//
//}

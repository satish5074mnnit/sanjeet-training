package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
@Slf4j
@RestController
public class api {

    jsonhelper jsonhelper1=new jsonhelper();


ObjectMapper objectMapper=new ObjectMapper();
    private Gson gson = new Gson();
//    JedisPool pool = new JedisPool("localhost", 1234);
//
//    Jedis jedis = pool.getResource();

    @GetMapping("/add")
    public  Object added() throws IOException {
        Object o=null;


      Response response=new Response();

        Object o1=gson.fromJson(jsonhelper1.readFile("/Users/satishbharati/Desktop/mock/mock.json"),Object.class);
     //   String json="{\"feeds\":[{\"id\":\"63bf338c-3e91-434a-8035-7e217580a446\",\"source\":\"SPOTLIGHT\",\"sourceId\":\"b6c7a1fb-89fd-4b95-968c-89bd044cb870\",\"publishedAt\":1702294767856,\"feed\":[{\"characteristics\":{\"campaign\":{\"contentDescription\":\"Dođiiuživajublagdanskimiznenađenjima\",\"campaignName\":\"Advent_Spotlight_dan11\",\"serviceIds\":[],\"subType\":\"SPOTLIGHT\",\"title\":\"OsvojiSubmarinemenu\",\"state\":\"show\",\"icon\":{\"darkUrl\":\"\",\"lightUrl\":\"https://cms-cdn.yo-digital.com/oa-resources/asset_manager/src/Campaign/oneapp/hr/images/original/asset_pantsuits.png\"},\"categories\":[\"telekom\"]}},\"cta\":[{\"ctaId\":117831,\"ctaType\":\"url\",\"deeplink\":\"telekom://benefits/adventskikalendar2023\"}],\"id\":\"1_24661\",\"media\":[{\"contentMeta\":{\"index\":0,\"placeHolder\":\"DEFAULT\"},\"contentType\":\"IMAGE\",\"lightUrl\":\"https://cms-cdn.yo-digital.com/oa-resources/asset_manager/processed/Campaign/oneapp/hr/images/xxhdpi/asset_pasteurizes.png\"}],\"engagement\":{\"downVotes\":0,\"upVotes\":0,\"impressions\":22158,\"userReaction\":\"NOT_YET\"},\"source\":\"CAMPAIGN\",\"sourceId\":\"24661\",\"publishedAt\":1702249169000}]}]}";

//        Employee employee = new Employee(1, "Karan", "IT", 5000);
        String employeeJsonString = this.gson.toJson(o1);

        try {
            o=objectMapper.readValue(employeeJsonString,Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

//    @PostMapping("/unlock")
//    public  Object unlock(@RequestBody String satish){
//
//        String s=jedis.set(satish,"cjnkjc");
//
//        if(StringUtils.isEmpty(s))
//            return "not  added";
//        else return s;
//
//    }

//    @GetMapping("/get")
//    public  Object lock(@RequestParam("satish") String  satish){
//
//     String s=jedis.get("foo");
//     if(StringUtils.isEmpty(s))
//         return "not found";
//     else return s;
//    }

    @GetMapping("/all")
    public  Object all() throws IOException {
        Object o=null;

        Response response=new Response();

        Object o1=gson.fromJson(jsonhelper1.readFile("/Users/satishbharati/Desktop/mock/mock2.json"),Object.class);
        //   String json="{\"feeds\":[{\"id\":\"63bf338c-3e91-434a-8035-7e217580a446\",\"source\":\"SPOTLIGHT\",\"sourceId\":\"b6c7a1fb-89fd-4b95-968c-89bd044cb870\",\"publishedAt\":1702294767856,\"feed\":[{\"characteristics\":{\"campaign\":{\"contentDescription\":\"Dođiiuživajublagdanskimiznenađenjima\",\"campaignName\":\"Advent_Spotlight_dan11\",\"serviceIds\":[],\"subType\":\"SPOTLIGHT\",\"title\":\"OsvojiSubmarinemenu\",\"state\":\"show\",\"icon\":{\"darkUrl\":\"\",\"lightUrl\":\"https://cms-cdn.yo-digital.com/oa-resources/asset_manager/src/Campaign/oneapp/hr/images/original/asset_pantsuits.png\"},\"categories\":[\"telekom\"]}},\"cta\":[{\"ctaId\":117831,\"ctaType\":\"url\",\"deeplink\":\"telekom://benefits/adventskikalendar2023\"}],\"id\":\"1_24661\",\"media\":[{\"contentMeta\":{\"index\":0,\"placeHolder\":\"DEFAULT\"},\"contentType\":\"IMAGE\",\"lightUrl\":\"https://cms-cdn.yo-digital.com/oa-resources/asset_manager/processed/Campaign/oneapp/hr/images/xxhdpi/asset_pasteurizes.png\"}],\"engagement\":{\"downVotes\":0,\"upVotes\":0,\"impressions\":22158,\"userReaction\":\"NOT_YET\"},\"source\":\"CAMPAIGN\",\"sourceId\":\"24661\",\"publishedAt\":1702249169000}]}]}";

//        Employee employee = new Employee(1, "Karan", "IT", 5000);
        String employeeJsonString = this.gson.toJson(o1);

        try {
            o=objectMapper.readValue(employeeJsonString,Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return o;
    }
    RestTemplate restTemplate=new RestTemplate();


    @GetMapping("/call")
    public String getHtml(){

        int i=500;
        while(i>0){
            Response response=new Response();
            Object request=gson.fromJson(response.getJosnAll(),Object.class);
            Object o=null;

             try {
                 o = restTemplate.postForObject("http://localhost:9000/engagement/fetch?hideOutOfStockDeals=false&isCompactMode=false&lat=28.437804&lng=77.080908&moreCategoryPlacement=false&page=0&showWalletWidgetOnTop=false&size=15", request, Object.class);
             } catch (RestClientException e) {
                 System.out.println("fail");
             }


              if(o!=null)
                  System.out.println();
              else System.out.println("fail");

            i--;
        }

        return "success";

    }

    RestTemplate restTemplate1=new RestTemplate();


    @GetMapping("/per")
    public  Object per(){

        Object o=null;
        o= restTemplate.postForEntity("https://34d6i62lbc.execute-api.eu-central-1.amazonaws.com/feeddps-np/api-np-sagemaker/",request.builder().test(Arrays.asList("best movie ever")).build(),Object.class);

        return o;
    }



}

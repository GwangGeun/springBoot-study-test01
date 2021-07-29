package com.carrot.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {

    @Test
    void getTest1(){

//        Map<String,Object> hm = new HashMap<>();

        String url = "https://reqres.in/api/users?page=2";

        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders header = new HttpHeaders();
        header.add("Bearer ", "1232323232323");
        HttpEntity<?> entity = new HttpEntity<>(header);

        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map<String,Object> body = exchange.getBody();
        System.out.println("body = " + body);

    }

    @Test
    void getTest2(){

//        Map<String,Object> hm = new HashMap<>();

//        String url = "https://reqres.in/api/users";

        String url = "http://localhost:8080/ping";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
//        params.put("name", "morpheus");
//        params.put("job", "leader");

        HttpHeaders header = new HttpHeaders();
        header.add("Bearer", "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwibmFtZSI6Iuygleq0keq3vCIsImlhdCI6MTYxODcyMTY0NSwiZXhwIjoxNjE4ODA4MDQ1fQ.IG8eVcgB3idRpPokzaoQCfJw7QzYFNI1wxrZSNGvXQA");
        HttpEntity<?> entity = new HttpEntity(params, header);


        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String body = exchange.getBody();
        System.out.println("body = " + body);

//        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
//        Map<String,Object> body = exchange.getBody();
//        System.out.println("body = " + body);

    }

}

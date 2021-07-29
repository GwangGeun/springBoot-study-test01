package com.carrot.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
// @ConfigurationProperties 참고 : https://www.baeldung.com/configuration-properties-in-spring-boot
@ConfigurationProperties(prefix = "jgg0328.limit")
public class EnvConfig {

    // 방법 1 : 기본형으로 바인딩 받기
    private int cancel;
    // 방법 2 : nested class 사용
    private Returns returns;
    // 방법 3 : Map 사용
    private Map<String, Integer> exchange;

    @Data
    public static class Returns{
        private int type01;
        private int type02;
    }

}

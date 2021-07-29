package com.carrot.test.controller;

import com.carrot.test.config.EnvConfig;
import com.carrot.test.config.EnvConfig.Returns;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/env")
public class EnvTestController {

    private final EnvConfig envConfig;

    @GetMapping("/ping")
    public String hello02(){
        int cancel = envConfig.getCancel();
        log.info("cancel, {}", cancel);

        Returns returns = envConfig.getReturns();
        int type01 = returns.getType01();
        int type02 = returns.getType02();
        log.info("returns, {} {}",type01,type02);

        Map<String, Integer> exchange = envConfig.getExchange();
        Integer exchanges = exchange.get("type01");
        log.info("exchange, {}", exchanges);


        return "pong";
    }


}

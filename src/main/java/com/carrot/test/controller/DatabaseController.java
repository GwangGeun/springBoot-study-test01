package com.carrot.test.controller;

import com.carrot.test.service.DataBaseService;
import com.carrot.test.service.DataBaseService02;
import com.carrot.test.service.DatabaseService03;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
@RequiredArgsConstructor
public class DatabaseController {

    private final DataBaseService databaseService;
    private final DataBaseService02 dataBaseService02;
    private final DatabaseService03 databaseService03;


    @GetMapping("/account:post")
    public void saveAccount(){
        databaseService.saveAccount();
    }

    @GetMapping("/account:get")
    public String getAccount(){
        return databaseService.getAccount();
    }

    @GetMapping("/log:post")
    public void postLog(){
        databaseService.saveLog();
    }

    @GetMapping("/log:get")
    public String getLog(){
        return databaseService.getLog();
    }

    @GetMapping("/test01:post")
    public void test() {
        databaseService.test01_outer();
    }

    @GetMapping("/test02:post")
    public void test02() {
        databaseService.test02_outer();
    }

    @GetMapping("/test03:post")
    public void test03() {
        databaseService03.chainedTransactionManagerTest01();
    }
}

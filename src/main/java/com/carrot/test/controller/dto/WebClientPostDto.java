package com.carrot.test.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WebClientPostDto {

    @Data
    public static class RequestDto{
        private final String name;
        private final String job;
    }

    @Data
    public static class ResponseDto{
        private String name;
        private String job;
        private String id;
        private LocalDateTime createdAt;
    }

}

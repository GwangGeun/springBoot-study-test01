package com.carrot.test.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class WebClientMultiDto {

    private int page;
    private int perPage;
    private int total;
    private int totalPage;
    private List<Datas> data;

    @Data
    public static class Datas{
        private int id;
        private String email;
        private String firstName;
        private String lastName;
        private String avatar;
    }
}

package com.carrot.test.controller;

import com.carrot.test.controller.dto.WebClientMultiDto;
import com.carrot.test.controller.dto.WebClientPostDto;
import com.carrot.test.controller.dto.WebClientPostDto.RequestDto;
import com.carrot.test.controller.dto.WebClientPostDto.ResponseDto;
import com.carrot.test.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/webclient")
public class WebClientController {

    private final WebClient webClient;

    static final String baseUrl = "https://reqres.in/";

    @GetMapping("/single")
    public String getData(){
        return webClient
                .mutate()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri("api/unknown/23")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status.is4xxClientError()
                                || status.is5xxServerError()
                        , clientResponse ->
                                clientResponse.bodyToMono(String.class)
                                        .map(body -> new CustomApiException("failed"))

                        )
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/multi")
    public WebClientMultiDto getDatas(){
        return webClient
                .mutate()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri("api/users?page=2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status.is4xxClientError()
                                || status.is5xxServerError()
                        , clientResponse ->
                                clientResponse.bodyToMono(String.class)
                                        .map(body -> new CustomApiException("failed"))

                )
                .bodyToMono(WebClientMultiDto.class)
                .block();
    }


    @PostMapping("/post")
    public ResponseDto post(){
        RequestDto requestDto = new RequestDto("morpheus", "leader");
        return webClient
                .mutate()
                .baseUrl(baseUrl)
                .build()
                .post()
                .uri("api/users")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .retrieve()
//                .onStatus(status -> status.is4xxClientError()
//                                || status.is5xxServerError()
//                        , clientResponse ->
//                                clientResponse.bodyToMono(String.class)
//                                        .map(body -> new CustomApiException("failed"))
//
//                )
                .bodyToMono(ResponseDto.class)
                .block();
    }


}

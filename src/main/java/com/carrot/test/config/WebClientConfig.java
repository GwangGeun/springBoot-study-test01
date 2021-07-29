package com.carrot.test.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * 참고
 * 1. 기본 사용법 : https://medium.com/@odysseymoon/spring-webclient-%EC%82%AC%EC%9A%A9%EB%B2%95-5f92d295edc0
 * 2. retrieve vs exchange : https://docs.spring.io/spring-framework/docs/5.3.0-SNAPSHOT/spring-framework-reference/web-reactive.html#webflux-client-exchange
 */
@Slf4j
@Configuration
public class WebClientConfig {

    private static final int DEFAULT_CONNECTION_TIMEOUT_SECONDS = 5;
    private static final int DEFAULT_READ_TIMEOUT_SECONDS = 5;
    private static final int DEFAULT_WRITE_TIMEOUT_SECONDS = 5;
    private static final String SERVICE_USER_AGENT = "test-project";

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient
                                        .create()
                                        // Connection TimeOut
                                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, DEFAULT_CONNECTION_TIMEOUT_SECONDS*1000)
                                        // ReadTimeOut & WriteTimeOut
                                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(DEFAULT_READ_TIMEOUT_SECONDS)).addHandlerLast(new WriteTimeoutHandler(DEFAULT_WRITE_TIMEOUT_SECONDS)))

                        )
                )
                .defaultHeader(HttpHeaders.USER_AGENT, SERVICE_USER_AGENT)
                .build();
    }

    public WebClient webClientStudy() {

        /**
         * 다음 2가지 부분을 위한 설정
         * 1. codec 처리를 위한 in-memory buffer 값의 default size : 256KB
         * 2. logging 출력
         */
//        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 50))
//                .build();
//        exchangeStrategies
//                .messageWriters().stream()
//                .filter(LoggingCodecSupport.class::isInstance)
//                .forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));

        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient
                                        .create()
                                        // Connection TimeOut
                                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120_000)
                                        // ReadTimeOut & WriteTimeOut
                                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(180)).addHandlerLast(new WriteTimeoutHandler(180)))

                        )
                )
//                .exchangeStrategies(exchangeStrategies)

                /**
                 * 이하 request, response 데이터 조작 혹은 추가 작업
                 */
//                .filter(ExchangeFilterFunction.ofRequestProcessor(
//                        clientRequest -> {
//                            log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
//                            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
//                            return Mono.just(clientRequest);
//                        }
//                ))
//                .filter(ExchangeFilterFunction.ofResponseProcessor(
//                        clientResponse -> {
//                            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
//                            return Mono.just(clientResponse);
//                        }
//                ))
                .defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
                .build();
    }
}

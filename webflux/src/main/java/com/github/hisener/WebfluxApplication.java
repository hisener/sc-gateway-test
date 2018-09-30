package com.github.hisener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.server.WebFilter;

@SpringBootApplication
public class WebfluxApplication {

    private static final Logger logger = LogManager.getLogger(WebfluxApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @Controller
    static class DummyController {

        @GetMapping("/redirect")
        public RedirectView redirectWithUsingRedirectPrefix() {
            return new RedirectView("http://google.com");
        }

        @GetMapping("/empty-response")
        public ResponseEntity<Void> responseEntity() {
            return ResponseEntity.ok().build();
        }

        @GetMapping("/foo")
        public ResponseEntity<String> string() {
            return ResponseEntity.ok("foo");
        }
    }

    @Bean
    WebFilter webFilter() {
        return (exchange, chain) ->
                chain.filter(exchange)
                        .doOnRequest(value -> {
                            logger.error("webFilter" + " doOnRequest");
                        })
                        .doOnSuccessOrError((aVoid, throwable) -> {
                            logger.error("webFilter" + " doOnSuccessOrError");
                        })
                        .doAfterSuccessOrError((aVoid, throwable) -> {
                            logger.error("webFilter" + " doAfterSuccessOrError");
                        });
    }
}

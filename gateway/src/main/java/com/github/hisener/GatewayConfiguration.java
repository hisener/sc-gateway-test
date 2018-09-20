package com.github.hisener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@EnableAutoConfiguration
@Configuration
public class GatewayConfiguration {

  private static final Logger logger = LogManager.getLogger(GatewayConfiguration.class);

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(r -> r.path("/**")
            .uri("http://localhost:9001").filter(gatewayFilter()))
        .build();
  }

  @Bean
  public GlobalFilter globalFilter() {
    return (exchange, chain) ->
        chain.filter(exchange)
            .doOnRequest(value -> {
              logger.error("globalFilter" + " doOnRequest");
            })
            .doOnSuccessOrError((aVoid, throwable) -> {
              logger.error("globalFilter" + " doOnSuccessOrError");
            })
            .doAfterSuccessOrError((aVoid, throwable) -> {
              logger.error("globalFilter" + " doAfterSuccessOrError");
            });
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

  @Bean
  GatewayFilter gatewayFilter() {
    return (exchange, chain) ->
        chain.filter(exchange)
            .doOnRequest(value -> {
              logger.error("gatewayFilter" + " doOnRequest");
            })
            .doOnSuccessOrError((aVoid, throwable) -> {
              logger.error("gatewayFilter" + " doOnSuccessOrError");
            })
            .doAfterSuccessOrError((aVoid, throwable) -> {
              logger.error("gatewayFilter" + " doAfterSuccessOrError");
            });
  }

}

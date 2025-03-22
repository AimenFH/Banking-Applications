package fh_campuswien.banking_applications.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator bankApplicationConfig(RouteLocatorBuilder builder, RouteToRequestUrlFilter routeToRequestUrlFilter, RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/campuswien-banking/**")
                        .filters(f -> f.rewritePath("/campuswien-banking/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("accountsCircuitBreaker")))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p
                        .path("/campuswien-banking/**")
                        .filters(f -> f.rewritePath("/campuswien-banking/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARDS"))
                .route(p -> p
                        .path("/campuswien-banking/**")
                        .filters(f -> f.rewritePath("/campuswien-banking/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS")).build();
    }

}

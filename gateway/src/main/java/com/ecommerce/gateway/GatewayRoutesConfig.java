package com.ecommerce.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.gateway.security.JwtAuthenticationFilter;

@Configuration
public class GatewayRoutesConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public GatewayRoutesConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            // ✅ Auth routes (public, no JWT)
            .route("user-auth", r -> r.path("/auth/**")
                                      .uri("lb://user-service"))

            // ✅ User-service secure endpoints
            .route("user-service", r -> r.path("/users/**")
                                         .filters(f -> f.filter(jwtFilter.apply(new JwtAuthenticationFilter.Config())))
                                         .uri("lb://user-service"))

            // ✅ Product-service (JWT required)
            .route("product-service", r -> r.path("/products/**")
                                            .filters(f -> f.filter(jwtFilter.apply(new JwtAuthenticationFilter.Config())))
                                            .uri("lb://product-service"))

            // ✅ Order-service (JWT required)
            .route("order-service", r -> r.path("/orders/**")
                                          .filters(f -> f.filter(jwtFilter.apply(new JwtAuthenticationFilter.Config())))
                                          .uri("lb://order-service"))
            .build();
    }
}


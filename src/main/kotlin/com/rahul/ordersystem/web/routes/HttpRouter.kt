package com.rahul.ordersystem.web.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class HttpRouter(
    private val productHttpRouter: ProductHttpRouter
) {
    @Bean
    fun route(): RouterFunction<ServerResponse> =

        router {
            "/v1".nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    add(productHttpRouter.router())
                }
            }
        }
}


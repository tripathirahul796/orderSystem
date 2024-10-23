package com.rahul.ordersystem.web.routes

import com.rahul.ordersystem.web.handler.CustomerHandler
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.router

@RestController
class CustomerHttpRouter(
    private val customerHandler: CustomerHandler
) {
    fun router() = router {
        "/customer".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/", customerHandler::getAllCustomer)
                GET("/{customerId}",customerHandler::getCustomerById)
                POST("/",customerHandler::createUpdateCustomer)
                DELETE("/{customerId}",customerHandler::deleteCustomerDetails)
            }
        }
    }

}
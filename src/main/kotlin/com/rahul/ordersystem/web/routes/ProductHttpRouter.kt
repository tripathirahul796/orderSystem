package com.rahul.ordersystem.web.routes

import com.rahul.ordersystem.web.handler.ProductHandler
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.router

@RestController
class ProductHttpRouter(
    private val productHandler: ProductHandler
) {

    fun router() = router {
        "/product".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/", productHandler::getAllProducts)
                GET("/{productId}",productHandler::getProduct)
//                GET("/category", productHandler::getAllCategory)
                POST("/createProduct",productHandler::createUpdateProduct)

//                DELETE("/delete/{productId}",productHandler::deleteProduct)
            }
        }
    }

}
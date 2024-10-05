package com.rahul.ordersystem.web.handler

import com.rahul.ordersystem.entities.Product
import com.rahul.ordersystem.usecase.ProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ProductHandler (@Autowired val productUseCase: ProductUseCase){

    private  val logger = LoggerFactory.getLogger(ProductHandler::class.java)

    fun getAllProducts(serverRequest: ServerRequest): Mono<ServerResponse> {
        logger.info("Request initiated!!")
//        return productUseCase.getAllProducts()
//            .collectList()
//           .flatMap {  ServerResponse.ok().bodyValue(it) }
        return productUseCase.getAllProducts()
            .doOnNext { logger.info("Emitting product: $it") } // Log emitted products
            .collectList()
            .doOnSuccess { logger.info("Collected products: $it") } // Log the collected list
            .flatMap { ServerResponse.ok().bodyValue(it) }
            .doOnError { logger.error("Error occurred: ${it.message}") } // Log errors
    }

    fun getAllCategory(serverRequest: ServerRequest): Mono<ServerResponse>{
        return productUseCase.findAllCatagory()
            .collectList()
            .flatMap{ServerResponse.ok().bodyValue(it)}
    }

    fun getItemByCategory(@PathVariable categoryName:String ): Mono<Product>? {
        return productUseCase.findItemByCategoryName(categoryName)
    }


}
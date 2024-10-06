package com.rahul.ordersystem.web.handler

import com.rahul.ordersystem.application.service.ProductService
import com.rahul.ordersystem.web.model.ProductRequest
import com.rahul.ordersystem.web.mapper.ProductRequestDTOMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ProductHandler(@Autowired val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductHandler::class.java)

    fun getAllProducts(serverRequest: ServerRequest): Mono<ServerResponse> {
        logger.info("Request initiated!!")
        return productService.getAllProduct()
            .doOnNext { logger.info("Emitting product: $it") } // Log emitted products
            .collectList()
            .doOnSuccess { logger.info("Collected products: $it") } // Log the collected list
            .flatMap { ServerResponse.ok().bodyValue(it) }
            .doOnError { logger.error("Error occurred: ${it.message}") } // Log errors
    }

    fun getProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.let {
            val productId = it.pathVariable("productId")
            productService.getProduct(productId).flatMap {
                ServerResponse
                    .ok()
                    .bodyValue(it)
            }.doOnError {
                logger.error("No Record found for productId $productId")
            }
        }.log()
    }

    fun createUpdateProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.let {
            val requestBody = it.bodyToMono(ProductRequest::class.java)
            requestBody.flatMap { el ->
                productService.createUpdateProduct(ProductRequestDTOMapper.toProductRequestDTO(el))
            }.flatMap { res ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(res)
            }.doOnError {
                logger.error("Error occurred while creating product " + serverRequest.uri())
            }.log()
        }
    }

//    fun updateProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
//        return serverRequest.let {
//            val newProduct = it.bodyToMono(ProductRequest::class.java)
//            newProduct.flatMap { el ->
//                productService.updateProduct(ProdcutRequestDTOMapper.toProductRequestDTO(el))
//            }.flatMap { res ->
//                ServerResponse
//                    .ok()
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .bodyValue(res)
//            }.doOnError {
//                logger.error("Error occurred while creating product " + serverRequest.uri())
//            }.log()
//
//        }
//    }

//    fun deleteProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
//        return productService.deleteProduct(serverRequest)
//            .toMono()
//            .flatMap(ServerResponse.ok()::bodyValue)
//    }
//
//    fun getAllCategory(serverRequest: ServerRequest): Mono<ServerResponse> {
//        return productService.findAllCatagory()
//            .collectList()
//            .flatMap { ServerResponse.ok().bodyValue(it) }
//    }
//
//    fun getItemByCategory(@PathVariable categoryName: String): Mono<Product>? {
//        return productService.findItemByCategoryName(categoryName)
//    }

}
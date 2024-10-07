package com.rahul.ordersystem.application.service

import com.rahul.ordersystem.entities.domain.dto.request.ProductRequestDTO
import com.rahul.ordersystem.entities.domain.model.Product
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductService {
    fun getAllProduct(): Flux<Product>
    fun getProduct(productId : String) : Mono<Product>
    fun createUpdateProduct(productRequestDTO: ProductRequestDTO): Mono<Product>
    fun updateProduct(productId:String, productRequestDTO: ProductRequestDTO): Mono<Product>
    fun deleteProduct(productId : String) :Mono<Void>

}


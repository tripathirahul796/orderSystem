package com.rahul.ordersystem.application.service

import com.rahul.ordersystem.entities.domain.dto.request.ProductRequestDTO
import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.web.model.request.ProductRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductService {
    fun getAllProduct(): Flux<Product>
    fun getProduct(productId : String) : Mono<Product>
    fun createUpdateProduct(productRequest: ProductRequest): Mono<Product>
    fun updateProduct(productId:String, productRequestDTO: ProductRequestDTO): Mono<Product>
    fun deleteProduct(productId : String) :Mono<Void>

}


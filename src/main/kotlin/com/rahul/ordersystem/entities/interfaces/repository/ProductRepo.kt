package com.rahul.ordersystem.entities.interfaces.repository

import com.rahul.ordersystem.entities.domain.model.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ProductRepo : ReactiveCrudRepository<Product, String> {
    fun findByProductId(productId: String): Mono<Product>
    fun findByCategoryName(categoryName: String) : Mono<Product>

    fun deleteByCategoryName(categoryName: String) : Mono<Void>
}
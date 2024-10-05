package com.rahul.ordersystem.infrastructure.repository.interfaces

import com.rahul.ordersystem.entities.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ProductRepo : ReactiveCrudRepository<Product, Int> {

    fun findItemByCategoryName(categoryName : String) : Mono<Product>?
}



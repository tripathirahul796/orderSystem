package com.rahul.ordersystem.entities.interfaces.repository

import com.rahul.ordersystem.entities.domain.model.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface CustomerRepo : ReactiveCrudRepository<Customer, String> {

    fun findByName(customerName:String) : Mono<Customer>
}
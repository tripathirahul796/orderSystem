package com.rahul.ordersystem.application.service

import com.rahul.ordersystem.entities.domain.model.Customer
import com.rahul.ordersystem.web.model.request.CustomerRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {

    fun getAllCustomer(): Flux<Customer>

    fun getCustomer(customerId: String): Mono<Customer>

    fun createUpdateCustomer(customerRequest: CustomerRequest) : Mono<Customer>

    fun deleteCustomer(customerId: String): Mono<Void>
}
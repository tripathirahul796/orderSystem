package com.rahul.ordersystem.usecase.mapper

import com.rahul.ordersystem.entities.domain.model.Customer
import com.rahul.ordersystem.web.model.request.CustomerRequest
import java.time.LocalDateTime
import java.util.*

object CustomerUseCaseMapper {

    fun toCustomerDomain(customerRequest: CustomerRequest, customerId: String? = null, createDate: String? = null): Customer {

        return Customer(
            customerId = customerId ?: UUID.randomUUID().toString(),
            name = customerRequest.name,
            contact = customerRequest.contact,
            createDate = createDate ?: LocalDateTime.now().toString(),
            lastUpdate = LocalDateTime.now().toString()
        )
    }
}
package com.rahul.ordersystem.entities.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Customer")
data class Customer(
    @Id
    val customerId: String? = null,
    val name: String? = null,
    val contact: String? = null,
    val createDate: String? = null,
    val lastUpdate: String? = null,
)

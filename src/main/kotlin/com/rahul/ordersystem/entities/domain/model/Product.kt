package com.rahul.ordersystem.entities.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Product")
data class Product(
    @Id
    val productId: String? = null,
    val categoryName: String? = null,
    val createdDate: String? = null,
    val lastUpdated: String? = null,
    val items: List<Items>? = null,
)

data class Items(
    val itemId: String? = null,
    val itemName: String? = null,
    val price: Float? = null,
    val isAvailable: Boolean? = null,
    val ingredients: List<String>? = null,
    val preparationTime: String? = null,
    val calories: Float? = null,
)

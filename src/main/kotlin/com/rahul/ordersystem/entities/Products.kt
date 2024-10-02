package com.rahul.ordersystem.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "Products")
data class Products(
    @Id
    val categoryId: ObjectId,
    val categoryName: String,
    val createdDate: String,
    val lastUpdated: String,
    val items: List<Items> = emptyList(),
)

data class Items(
//    val itemId: Int,
    val itemName: String,
    val price: Float,
    @Field("available")
    val isAvailable: Boolean,
    val ingredients: List<String> = emptyList(),
    val preparationTime: String,
    val calories: Float,
)

data class Ingredients(
//    val id: Int,
    val ingredientName: String,
)
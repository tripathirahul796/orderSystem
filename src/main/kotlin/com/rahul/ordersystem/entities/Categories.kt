package com.rahul.ordersystem.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Category")
data class Category(
    @Id
    val categoryId: Int,
    val name:String, )

@Document("restaurants")
data class Restaurant(
    @Id
    val id: ObjectId = ObjectId(),
    val borough: String = "",
    val cuisine: String = "",
    val name: String = "",
)

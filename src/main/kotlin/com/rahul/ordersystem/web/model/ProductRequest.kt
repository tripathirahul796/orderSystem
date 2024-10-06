package com.rahul.ordersystem.web.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductRequest(
    @field:JsonProperty("categoryName")
    val categoryName: String? = null,
    @field: JsonProperty("items")
    val items: List<ItemRequest>? = null,
)

data class ItemRequest(
    @field : JsonProperty("itemName")
    val itemName: String? = null,
    @field : JsonProperty("price")
    val price: Float? = null,
    @field : JsonProperty("available")
    val isAvailable: Boolean? = null,
    @field : JsonProperty("ingredients")
    val ingredients: List<String>? = null,
    @field : JsonProperty("preparationTime")
    val preparationTime: String? = null,
    @field : JsonProperty("calories")
    val calories: Float? = null,
)
data class Ingredients(
    @field : JsonProperty("ingredientName")
    val ingredientName: String? = null,
)
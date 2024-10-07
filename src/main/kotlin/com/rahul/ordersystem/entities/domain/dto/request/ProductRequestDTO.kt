package com.rahul.ordersystem.entities.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductRequestDTO(
    @field:JsonProperty("categoryName")
    val categoryName: String? = null,
    @field: JsonProperty("items")
    val items: List<ItemRequestDTO>? = null,
)

data class ItemRequestDTO(
    @field : JsonProperty("itemId")
    val itemId : String? = null,
    @field : JsonProperty("itemName")
    val itemName: String? = null,
    @field : JsonProperty("price")
    val price: Float? = null,
    @field : JsonProperty("isAvailable")
    val isAvailable: Boolean? = null,
    @field : JsonProperty("ingredients")
    val ingredients: List<String>? = null,
    @field : JsonProperty("preparationTime")
    val preparationTime: String? = null,
    @field : JsonProperty("calories")
    val calories: Float? = null,
)

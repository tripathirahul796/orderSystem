package com.rahul.ordersystem.entities.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.rahul.ordersystem.web.model.Ingredients

data class ProductResponseDTO(
    @field:JsonProperty("categoryName")
    val categoryName: String? = null,
    @field: JsonProperty("items")
    val items: List<ItemResponseDTO>? = null,
)

data class ItemResponseDTO(
    @field : JsonProperty("itemName")
    val itemName: String? = null,
    @field : JsonProperty("price")
    val price: Float? = null,
    @field : JsonProperty("isAvailable")
    val isAvailable: Boolean? = null,
    @field : JsonProperty("ingredients")
    val ingredients: List<Ingredients>? = null,
    @field : JsonProperty("preparationTime")
    val preparationTime: String? = null,
    @field : JsonProperty("calories")
    val calories: Float? = null,
)

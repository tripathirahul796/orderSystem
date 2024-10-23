package com.rahul.ordersystem.web.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CustomerRequest(
    @field:JsonProperty("customerName")
    val name: String? = null,
    @field: JsonProperty("contact")
    val contact: String? = null,
)

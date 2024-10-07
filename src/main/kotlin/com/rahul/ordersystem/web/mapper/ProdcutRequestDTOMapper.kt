package com.rahul.ordersystem.web.mapper

import com.rahul.ordersystem.entities.domain.dto.request.ItemRequestDTO
import com.rahul.ordersystem.entities.domain.dto.request.ProductRequestDTO
import com.rahul.ordersystem.web.model.ProductRequest

object ProductRequestDTOMapper {

    fun toProductRequestDTO(productRequest: ProductRequest) : ProductRequestDTO {
        return productRequest.let {
            ProductRequestDTO(
                categoryName = it.categoryName,
                items = getItem(it),
            )
        }
    }

    private fun getItem(productRequest: ProductRequest) : List<ItemRequestDTO> {
        return productRequest.let {
            val productItemRequest = mutableListOf<ItemRequestDTO>()
            it.items?.forEach { el ->
                productItemRequest.add(
                    ItemRequestDTO(
                        itemId = el.itemId,
                        itemName = el.itemName,
                        price = el.price,
                        isAvailable = el.isAvailable,
                        ingredients = el.ingredients,
                        preparationTime = el.preparationTime,
                        calories = el.calories
                    ),
                )
            }
            productItemRequest
        }
    }


}
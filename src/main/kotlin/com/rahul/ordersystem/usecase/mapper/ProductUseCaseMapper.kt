package com.rahul.ordersystem.usecase.mapper

import com.rahul.ordersystem.entities.domain.model.Items
import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.entities.domain.dto.request.ProductRequestDTO
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.UUID

@Component
object ProductUseCaseMapper {

    fun toProductDomain(productRequestDTO: ProductRequestDTO): Product {
        return productRequestDTO.let {
            Product(
                productId = UUID.randomUUID().toString(),
                categoryName = it.categoryName,
                createdDate = LocalDateTime.now().toString(),
                lastUpdated = LocalDateTime.now().toString(),
                items = getItem(productRequestDTO)
            )
        }
    }

    private fun getItem(productRequestDTO: ProductRequestDTO): List<Items> {
        return productRequestDTO.let {
            val productItemRequest = mutableListOf<Items>()
            it.items?.forEach { el ->
                productItemRequest.add(
                    Items(
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
package com.rahul.ordersystem.usecase.mapper

import com.rahul.ordersystem.entities.domain.model.Items
import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.web.model.request.ProductRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
object ProductUseCaseMapper {

    fun toProductDomain(productRequest: ProductRequest, productId: String? = null, createdDate: String? = null): Product {
        return Product(
            productId = productId ?: UUID.randomUUID().toString(),
            categoryName = productRequest.categoryName,
            createdDate = createdDate ?: LocalDateTime.now().toString(),
            lastUpdated = LocalDateTime.now().toString(),
            items = getItem(productRequest)
        )
    }

    private fun getItem(productRequest: ProductRequest): List<Items> {
        return productRequest.let {
            val productItemRequest = mutableListOf<Items>()
            it.items?.forEach { el ->
                productItemRequest.add(
                    Items(
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
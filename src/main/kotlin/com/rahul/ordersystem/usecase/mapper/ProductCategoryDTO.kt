package com.rahul.ordersystem.usecase.mapper

import com.rahul.ordersystem.entities.domain.model.Product
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ProductCategoryDTO
{
    fun toCategory(products: Flux<Product>) : Flux<String>
    {
        return products.map { e -> e.categoryName }
    }
}

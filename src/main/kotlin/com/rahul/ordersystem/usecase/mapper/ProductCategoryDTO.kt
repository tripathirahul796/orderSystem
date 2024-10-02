package com.rahul.ordersystem.usecase.mapper

import com.rahul.ordersystem.entities.Products
import org.springframework.stereotype.Service

@Service
class ProductCategoryDTO
{
    fun toCategory(products: List<Products>) : List<String>
    {
        return products.map { e -> e.categoryName }
    }
}

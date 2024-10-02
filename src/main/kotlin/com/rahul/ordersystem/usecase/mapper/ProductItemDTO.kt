package com.rahul.ordersystem.usecase.mapper

import com.rahul.ordersystem.entities.Items
import com.rahul.ordersystem.entities.Products
import org.springframework.stereotype.Service

@Service
class ProductItemDTO {

    fun toItem(products: Products?): List<Items> {
        return products?.items!!
    }
}

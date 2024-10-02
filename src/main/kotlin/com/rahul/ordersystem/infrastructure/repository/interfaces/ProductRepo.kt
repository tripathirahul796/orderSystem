package com.rahul.ordersystem.infrastructure.repository.interfaces

import com.rahul.ordersystem.entities.Products
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepo : MongoRepository<Products, Int> {

    fun findItemByCategoryName(categoryName : String) : Products?
}
package com.rahul.ordersystem.usecase.util

import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.entities.interfaces.repository.ProductRepo
import reactor.core.publisher.Flux

class ProductValidation(private val productRepo: ProductRepo) {

    fun isProductExist(categoryName: String): Flux<Product> {
      return  productRepo.findByCategoryName(categoryName)
    }

}
package com.rahul.ordersystem.usecase.util

import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.entities.interfaces.repository.ProductRepo
import reactor.core.publisher.Mono

class ProductValidation(private val productRepo: ProductRepo) {

    fun isProductExist(categoryName: String): Mono<Product> {
      return  productRepo.findByCategoryName(categoryName)
    }

}
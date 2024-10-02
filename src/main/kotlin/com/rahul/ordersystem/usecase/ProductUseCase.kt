package com.rahul.ordersystem.usecase

import com.rahul.ordersystem.entities.Items
import com.rahul.ordersystem.entities.Products
import com.rahul.ordersystem.infrastructure.repository.interfaces.ProductRepo
import com.rahul.ordersystem.usecase.mapper.ProductCategoryDTO
import com.rahul.ordersystem.usecase.mapper.ProductItemDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ProductUseCase(@Autowired val productRepo: ProductRepo, val productCategoryDTO: ProductCategoryDTO, val productItemDTO: ProductItemDTO) {

    fun getAllProducts(): MutableList<Products> {
        return productRepo.findAll()
    }

    fun findAllCatagory(): List<String> {
       return  productCategoryDTO.toCategory( productRepo.findAll())
    }

    fun findItemByCategoryName(categoryName : String): List<Items> {
        return productItemDTO.toItem(productRepo.findItemByCategoryName(categoryName))
    }
}
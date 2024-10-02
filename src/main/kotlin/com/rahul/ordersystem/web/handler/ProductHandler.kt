package com.rahul.ordersystem.web.handler

import com.rahul.ordersystem.entities.Items
import com.rahul.ordersystem.entities.Products
import com.rahul.ordersystem.usecase.ProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products/")
class ProductHandler (@Autowired val productUseCase: ProductUseCase){

    private  val logger = LoggerFactory.getLogger(ProductHandler::class.java)
    @GetMapping("/")
    fun getAllProducts(): MutableList<Products> {
        logger.info("Request initiated!!")
        return productUseCase.getAllProducts()
    }

    @GetMapping("/category")
    fun getAllCategory(): List<String> {
        return productUseCase.findAllCatagory()
    }

    @GetMapping("/category/{categoryName}")
    fun getItemByCategory(@PathVariable categoryName:String ): List<Items> {
        return productUseCase.findItemByCategoryName(categoryName)
    }


}
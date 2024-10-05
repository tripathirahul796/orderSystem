package com.rahul.ordersystem.usecase

import com.rahul.ordersystem.entities.Product
import com.rahul.ordersystem.infrastructure.repository.interfaces.ProductRepo
import com.rahul.ordersystem.usecase.mapper.ProductCategoryDTO
import com.rahul.ordersystem.usecase.mapper.ProductItemDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class ProductUseCase(@Autowired val productRepo: ProductRepo, val productCategoryDTO: ProductCategoryDTO, val productItemDTO: ProductItemDTO) {

    private  val logger = LoggerFactory.getLogger(ProductUseCase::class.java)
    fun getAllProducts(): Flux<Product> {
//        val findAll: Disposable = productRepo.findAll()
        val findAll: Flux<Product> = productRepo.findAll()
            .doOnNext { logger.info("Product found: $it") }
            .doOnComplete { logger.info("Completed fetching products") }
        return findAll
    }

    fun findAllCatagory(): Flux<String> {
       return  productCategoryDTO.toCategory( productRepo.findAll())
    }

    fun findItemByCategoryName(categoryName : String): Mono<Product>? {
        return  productRepo.findItemByCategoryName(categoryName)
    }
}
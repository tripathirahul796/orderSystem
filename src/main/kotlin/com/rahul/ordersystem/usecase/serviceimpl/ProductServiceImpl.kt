package com.rahul.ordersystem.usecase.serviceimpl

import com.rahul.ordersystem.application.service.ProductService
import com.rahul.ordersystem.entities.domain.dto.request.ProductRequestDTO
import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.entities.interfaces.repository.ProductRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CategoryServiceImpl(@Autowired val productRepo: ProductRepo) : ProductService {
    override fun getAllProduct(): Flux<Product> {
        return productRepo.findAll()
    }

    override fun getProduct(productId: String): Mono<Product> {
        return productRepo.findByProductId(productId)
    }

    override fun createUpdateProduct(productRequestDTO: ProductRequestDTO): Mono<Product> {
        TODO("Not yet implemented")
    }

    override fun updateProduct(productId: String, productRequestDTO: ProductRequestDTO): Mono<Product> {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(productId: String): Mono<Void> {
        return productRepo.deleteById(productId)
    }

}
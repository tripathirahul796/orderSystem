package com.rahul.ordersystem.usecase.serviceimpl

import com.rahul.ordersystem.application.service.ProductService
import com.rahul.ordersystem.entities.domain.dto.request.ProductRequestDTO
import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.entities.interfaces.repository.ProductRepo
import com.rahul.ordersystem.usecase.mapper.ProductUseCaseMapper
import com.rahul.ordersystem.web.handler.ProductHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.LocalDateTime

@Service
class ProductServiceImpl(@Autowired val productRepo: ProductRepo) : ProductService {

    private val logger = LoggerFactory.getLogger(ProductServiceImpl::class.java)

    override fun getAllProduct(): Flux<Product> {
        return productRepo.findAll()
    }

    override fun getProduct(productId: String): Mono<Product> {
        return productRepo.findByProductId(productId)
    }

    override fun createUpdateProduct(productRequestDTO: ProductRequestDTO): Mono<Product> {
        return productRequestDTO.let { pit ->
            val existCategory = productRepo.findByCategoryName(pit.categoryName.toString())
            val newProduct = ProductUseCaseMapper.toProductDomain(productRequestDTO)
            existCategory.doOnNext { cit ->
                logger.info("Product already exist for category ${cit.categoryName} with productId ${cit.productId}")
                logger.info("Updating the product with latest data!!")
                val updatedProduct =
                    newProduct.copy(productId = cit.productId, lastUpdated = LocalDateTime.now().toString())
                productRepo.save(updatedProduct)
                logger.info("Updated the product with latest data for productId: ${cit.productId} and category: ${cit.categoryName} ")
            }.switchIfEmpty {
                logger.info("Creating new product ")
                productRepo.save(newProduct)
            }.doOnError {
                logger.error("Unable to create product with error ${it.printStackTrace()}")
            }
        }
    }


    override fun updateProduct(productId: String, productRequestDTO: ProductRequestDTO): Mono<Product> {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(productId: String): Mono<Void> {
        return productRepo.deleteById(productId)
    }

}


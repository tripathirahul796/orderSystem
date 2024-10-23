package com.rahul.ordersystem.usecase.serviceimpl

import com.rahul.ordersystem.application.service.ProductService
import com.rahul.ordersystem.entities.domain.dto.request.ProductRequestDTO
import com.rahul.ordersystem.entities.domain.model.Product
import com.rahul.ordersystem.entities.interfaces.repository.ProductRepo
import com.rahul.ordersystem.usecase.mapper.ProductUseCaseMapper
import com.rahul.ordersystem.web.model.request.ProductRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class ProductServiceImpl(@Autowired val productRepo: ProductRepo) : ProductService {

    private val logger = LoggerFactory.getLogger(ProductServiceImpl::class.java)

    override fun getAllProduct(): Flux<Product> {
        return productRepo.findAll()
    }

    override fun getProduct(productId: String): Mono<Product> {
        return productRepo.findByProductId(productId)
    }

    override fun createUpdateProduct(productRequest: ProductRequest): Mono<Product> {
        return productRequest.let { pit ->
            val existingCategory = productRepo.findByCategoryName(pit.categoryName.toString())
            existingCategory.flatMap { cit ->
                logger.info("Product already exist for category ${cit.categoryName} with productId ${cit.productId}")
                logger.info("Updating the product with latest data!!")
                val updatedProduct =
                    ProductUseCaseMapper.toProductDomain(
                        productRequest,
                        productId = cit.productId,
                        createdDate = cit.createdDate
                    )
                productRepo.save(updatedProduct).doOnSuccess {
                    logger.info("Updated the product with latest data for productId: ${cit.productId} and category: ${cit.categoryName} ")
                }
            }
        }.switchIfEmpty {
            val newProduct = ProductUseCaseMapper.toProductDomain(productRequest)
            logger.info("Creating new product ")
            productRepo.save(newProduct).doOnSuccess {
                logger.info("Successfully created a product with productId ${newProduct.productId}  and category: ${newProduct.categoryName}")
            }
        }.doOnError {
            logger.error("Unable to create product with error ${it.printStackTrace()}")
        }
    }


    override fun updateProduct(productId: String, productRequestDTO: ProductRequestDTO): Mono<Product> {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(productId: String): Mono<Void> {
        return productRepo.deleteById(productId)
    }

}


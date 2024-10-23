package com.rahul.ordersystem.usecase.serviceimpl

import com.rahul.ordersystem.application.service.CustomerService
import com.rahul.ordersystem.entities.domain.model.Customer
import com.rahul.ordersystem.entities.interfaces.repository.CustomerRepo
import com.rahul.ordersystem.usecase.mapper.CustomerUseCaseMapper
import com.rahul.ordersystem.web.model.request.CustomerRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class CustomerServiceImpl(private val customerRepo: CustomerRepo) : CustomerService {

    private val logger = LoggerFactory.getLogger(CustomerServiceImpl::class.java)

    override fun getAllCustomer(): Flux<Customer> {
        return customerRepo.findAll()
    }

    override fun getCustomer(customerId: String): Mono<Customer> {
        return customerRepo.findById(customerId)
    }

    override fun createUpdateCustomer(customerRequest: CustomerRequest): Mono<Customer> {
        return customerRequest.let { pit ->
            val existCustomer = customerRepo.findByName(customerRequest.name.toString())
            existCustomer.flatMap { cit ->
                logger.info("Customer already exist for customerId ${cit.customerId} with Name ${cit.name}")
                logger.info("Updating the customer details with latest data!!")
                val updatedCustomer =
                    CustomerUseCaseMapper.toCustomerDomain(
                        customerRequest,
                        customerId = cit.customerId,
                        createDate = cit.createDate
                    )
                customerRepo.save(updatedCustomer).doOnSuccess {
                    logger.info("Updated the customer details with latest data for customerId: ${cit.customerId} and name: ${updatedCustomer.name} ")
                }
            }.switchIfEmpty {

                val newCustomer = CustomerUseCaseMapper.toCustomerDomain(customerRequest)
                logger.info("Creating a new customer ")
                customerRepo.save(newCustomer).doOnSuccess {
                    logger.info("Successfully created a new customer with customerId ${newCustomer.customerId} and name ${newCustomer.name} ")
                }
            }.doOnError { ex ->
                logger.error("Unable to create or update customer with error ${ex.message}", ex)
            }
        }

    }

    override fun deleteCustomer(customerId: String): Mono<Void> {
        return customerRepo.deleteById(customerId)
    }
}
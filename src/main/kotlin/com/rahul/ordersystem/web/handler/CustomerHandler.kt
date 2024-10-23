package com.rahul.ordersystem.web.handler

import com.rahul.ordersystem.application.service.CustomerService
import com.rahul.ordersystem.web.model.request.CustomerRequest
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class CustomerHandler(val customerService: CustomerService) {
    private val logger = LoggerFactory.getLogger(CustomerHandler::class.java)

    fun getAllCustomer(serverRequest: ServerRequest): Mono<ServerResponse> {
        return customerService.getAllCustomer().collectList()
            .flatMap {
                ServerResponse
                    .ok()
                    .bodyValue(it)
            }.doOnNext {
                logger.info("Successfully retrieved all the customer details !!, uri ${serverRequest.uri()}")
            }.doOnError {
                logger.error("Error occurred while retrieving the all customer details, path ${serverRequest.uri()}")
            }
    }

    fun getCustomerById(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.let {
            val customerId = it.pathVariable("customerId")
            customerService.getCustomer(customerId).flatMap {
                ServerResponse
                    .ok()
                    .bodyValue(it)
            }.doOnNext {
                logger.info("Successfully retrieved the customer details !!, uri ${serverRequest.uri()}")
            }.doOnError {
                logger.error("Error occurred while retrieving the customer details for customerId $customerId, path ${serverRequest.uri()}")
            }
        }
    }

    fun createUpdateCustomer(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.let { req ->
            val requestBody = req.bodyToMono(CustomerRequest::class.java)
            requestBody.flatMap { el ->
                customerService.createUpdateCustomer(el)
            }.flatMap {
                ServerResponse.ok().bodyValue(it)
            }.doOnError {
                    logger.error("Error occurred while creating customer details " + serverRequest.uri())
                    ServerResponse
                        .badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(req)
                }
        }
    }

    fun deleteCustomerDetails(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.let { req ->
            val customerId = req.pathVariable("customerId")
            customerService.getCustomer(customerId).flatMap {
                ServerResponse
                    .ok()
                    .bodyValue(it)
            }.doOnNext {
                logger.info("Successfully deleted the customer details !!, uri ${serverRequest.uri()}")
            }.doOnError {
                logger.error("Error occurred while deleting the customer details for customerId $customerId, path ${serverRequest.uri()}")
            }
        }
    }
}
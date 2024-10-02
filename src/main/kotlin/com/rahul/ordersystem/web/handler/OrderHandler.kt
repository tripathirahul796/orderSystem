package com.rahul.ordersystem.web.handler

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderHandler {

    @GetMapping("/")
    fun getAllOrders() = "All orders"

    @GetMapping("/{orderId}")
    fun getOrder(orderId : Int) = "Order"

}
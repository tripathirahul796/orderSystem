package com.rahul.ordersystem

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration()
class OrdersystemApplication

fun main(args: Array<String>) {
	runApplication<OrdersystemApplication>(*args)
}

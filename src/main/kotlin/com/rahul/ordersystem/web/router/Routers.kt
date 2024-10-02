package com.rahul.ordersystem.web.router

import com.rahul.ordersystem.infrastructure.repository.interfaces.RestaurantsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Routers(@Autowired val repo: RestaurantsRepo) {

    @GetMapping("/test")
    fun test() = "Hello World!!"

    @GetMapping("/restaurent/count")
    fun getAllRestaurentv(): Int {
        return repo.findAll().count()
    }




}
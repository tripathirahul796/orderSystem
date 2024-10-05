package com.rahul.ordersystem.web.handler

import com.rahul.ordersystem.infrastructure.repository.interfaces.CategoryRepo
import jdk.jfr.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
@RequestMapping("/category")
class CategoryHandler(@Autowired val categoryRepo: CategoryRepo) {

    @GetMapping("/")
    fun getAllCategory(): Flux<Category> {
        return categoryRepo.findAll()
    }

    }
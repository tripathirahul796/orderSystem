package com.rahul.ordersystem.infrastructure.repository.interfaces

import jdk.jfr.Category
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepo : ReactiveMongoRepository<Category, Int>{
}
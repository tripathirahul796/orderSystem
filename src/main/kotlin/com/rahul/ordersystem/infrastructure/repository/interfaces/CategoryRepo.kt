package com.rahul.ordersystem.infrastructure.repository.interfaces

import jdk.jfr.Category
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepo : MongoRepository<Category, Int>{
}
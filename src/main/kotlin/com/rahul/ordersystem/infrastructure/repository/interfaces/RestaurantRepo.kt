package com.rahul.ordersystem.infrastructure.repository.interfaces

import com.rahul.ordersystem.entities.Restaurant
import org.springframework.data.mongodb.repository.MongoRepository

interface RestaurantsRepo : MongoRepository<Restaurant, String>{

}
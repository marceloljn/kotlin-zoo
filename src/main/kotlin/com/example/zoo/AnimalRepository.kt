package com.example.zoo

import java.util.*
import org.springframework.data.mongodb.repository.MongoRepository

interface AnimalRepository : MongoRepository<Animal, String> {
    fun findByName(name: String): Optional<Animal>
}

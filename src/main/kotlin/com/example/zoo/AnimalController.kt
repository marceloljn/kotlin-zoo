
package com.example.zoo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("animals")
class AnimalController(var repository : AnimalRepository){

    @PostMapping
    fun create(@RequestBody animal: Animal) = ResponseEntity.ok(repository.save(animal))

    @GetMapping
    fun read() = ResponseEntity.ok(repository.findAll())

    @PutMapping("{id}")
    fun update(@PathVariable id: String, @RequestBody animal : Animal) : ResponseEntity<Animal>{
        val toSave = repository.findById(id)
                .orElseThrow{ RuntimeException("Animal with id $id not found")}
                .copy(name = animal.name, age = animal.age)
        return ResponseEntity.ok(repository.save(toSave))
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String) = repository
            .findById(id)
            .ifPresent {repository.delete(it)}
}

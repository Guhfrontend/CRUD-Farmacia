package com.performance_go.CRUD_Farmacia.controller;

import com.performance_go.CRUD_Farmacia.model.Categoria;
import com.performance_go.CRUD_Farmacia.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){
        if (categoria.getId() == null)
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        return categoriaRepository.findById(id).map(res -> ResponseEntity.ok(res)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Categoria>> findByCategoria(@PathVariable String categoria){
        return ResponseEntity.ok(categoriaRepository.findAllByCategoriaContainingIgnoreCase(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria){
        return categoriaRepository.findById(categoria.getId()).map(res -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoriaRepository.deleteById(id);
    }
}

package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.CategoriaGastoModel;
import com.empiricus.statusviajante.repository.CategoriaGastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
public class CategoriaGastoController {

    @Autowired

    private CategoriaGastoRepository categoriaGastoRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaGastoModel>> GetAll() {
        return ResponseEntity.ok(categoriaGastoRepository.findAll());

    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaGastoModel> GetById(@PathVariable Long idCategoria) {

        return categoriaGastoRepository.findById(idCategoria)


                .map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CategoriaGastoModel>> GetByNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaGastoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody CategoriaGastoModel categoriaGastoModel) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaGastoRepository.save(categoriaGastoModel));
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity put(@RequestBody CategoriaGastoModel categoriaGastoModel) {
        try {
            return ResponseEntity.ok(categoriaGastoRepository.save(categoriaGastoModel));
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{idCategoria}")
    public void Delete(@PathVariable Long idCategoria) {
        categoriaGastoRepository.deleteById(idCategoria);
    }
}


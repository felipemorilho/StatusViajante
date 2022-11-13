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
    private CategoriaGastoRepository repositoryCategoria;

    @GetMapping
    public ResponseEntity<List<CategoriaGastoModel>> GetAll() {
        return ResponseEntity.ok(repositoryCategoria.findAll());
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaGastoModel> GetById(@PathVariable Long idCategoria) {
        return repositoryCategoria.findById(idCategoria)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CategoriaGastoModel>> GetByNome(@PathVariable String nome) {
        return ResponseEntity.ok(repositoryCategoria.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<CategoriaGastoModel> post(@RequestBody CategoriaGastoModel categoriaGastoModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryCategoria.save(categoriaGastoModel));
    }
    @PutMapping
    public ResponseEntity<CategoriaGastoModel> put(@RequestBody CategoriaGastoModel categoriaGastoModel) {
        return ResponseEntity.ok(repositoryCategoria.save(categoriaGastoModel));
    }
    @DeleteMapping("/{idCategoria}")
    public void Delete(@PathVariable Long idCategoria) {
        repositoryCategoria.deleteById(idCategoria);
    }
}


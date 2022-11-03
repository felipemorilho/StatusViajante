package com.empiricus.statusviajante.controller;


import com.empiricus.statusviajante.model.CadastroUsuario;
import com.empiricus.statusviajante.model.CategoriaViagem;
import com.empiricus.statusviajante.repository.CategoriaViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
public class CategoriaViagemController {

    @Autowired
    private CategoriaViagemRepository repositoryCategoria;

    @GetMapping
    public ResponseEntity<List<CategoriaViagem>> GetAll() {
        return ResponseEntity.ok(repositoryCategoria.findAll());
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaViagem> GetById(@PathVariable Long idCategoria) {
        return repositoryCategoria.findById(idCategoria)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CategoriaViagem>> GetByNome(@PathVariable String nome) {
        return ResponseEntity.ok(repositoryCategoria.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<CategoriaViagem> post(@RequestBody CategoriaViagem categoriaViagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryCategoria.save(categoriaViagem));
    }

    //Apaga Usuário por ID
    @DeleteMapping("/{idCategoria}")
    public void Delete(@PathVariable Long idCategoria) {
        repositoryCategoria.deleteById(idCategoria);
    }
}


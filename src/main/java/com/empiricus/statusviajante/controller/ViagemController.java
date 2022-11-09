package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.GastoViagemModel;
import com.empiricus.statusviajante.model.ViagemModel;
import com.empiricus.statusviajante.repository.ViagemRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
@CrossOrigin("*")
public class ViagemController {

    @Autowired
    private ViagemRepository repository;

    //Lista todas as Viagens
    @GetMapping
    public ResponseEntity<List<ViagemModel>> GetAll(){
        return ResponseEntity.ok(repository.findAll());
    };

    //Lista Viagem por ID
    @GetMapping ("/{id}")
    public ResponseEntity<ViagemModel> GetById(@PathVariable Long id){
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    };

    //Cria Viagem
    @PostMapping
    public ResponseEntity<ViagemModel> Post(@RequestBody ViagemModel viagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(viagem));
    }
    @PutMapping
    public ResponseEntity<ViagemModel> put(@RequestBody ViagemModel viagem) {
        return ResponseEntity.ok(repository.save(viagem));
    }
    @DeleteMapping ("/{id}")
    public void Delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}

package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.Cadastro;
import com.empiricus.statusviajante.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class CadastroController {


    @Autowired
    private CadastroRepository repCadastro;

    @GetMapping
    public ResponseEntity<List<Cadastro>> GetAll(){
        return ResponseEntity.ok(repCadastro.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Cadastro> GetById(@PathVariable Long idUsuario){
        return repCadastro.findById(idUsuario)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cadastro>> GetByNome(@PathVariable String nome){
        return ResponseEntity.ok(repCadastro.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Cadastro> post(@RequestBody Cadastro cadastro){
        return ResponseEntity.status(HttpStatus.CREATED).body(repCadastro.save(cadastro));
    }
    }

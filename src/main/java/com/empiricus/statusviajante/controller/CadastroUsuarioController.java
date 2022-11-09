package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.CadastroUsuarioModel;
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    @Autowired
    private CadastroUsuarioRepository repCadastro;

    @GetMapping
    public ResponseEntity<List<CadastroUsuarioModel>> GetAll(){
        return ResponseEntity.ok(repCadastro.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<CadastroUsuarioModel> GetById(@PathVariable Long idUsuario){
        return repCadastro.findById(idUsuario)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CadastroUsuarioModel>> GetByNome(@PathVariable String nome){
        return ResponseEntity.ok(repCadastro.findAllByNomeContainingIgnoreCase(nome));
    }
    @PostMapping
    public ResponseEntity<CadastroUsuarioModel> post(@RequestBody CadastroUsuarioModel cadastroUsuarioModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(repCadastro.save(cadastroUsuarioModel));
    }
    //Apaga Usuário por ID
    @DeleteMapping ("/{idUsuario}")
    public void Delete(@PathVariable Long idUsuario){
        repCadastro.deleteById(idUsuario);
    }
    }


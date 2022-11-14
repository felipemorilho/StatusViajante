package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.CadastroUsuarioModel;
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository;
import com.empiricus.statusviajante.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    @Autowired
    private CadastroUsuarioRepository cadastroUsuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @GetMapping
    public ResponseEntity<List<CadastroUsuarioModel>> GetAll() {

        return ResponseEntity.ok(cadastroUsuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<CadastroUsuarioModel> GetById(@PathVariable Long idUsuario) {
        return cadastroUsuarioRepository.findById(idUsuario)

                .map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CadastroUsuarioModel>> GetByNome(@PathVariable String nome) {
        return ResponseEntity.ok(cadastroUsuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    //@PostMapping
    //public ResponseEntity<CadastroUsuarioModel> post(@RequestBody CadastroUsuarioModel cadastroUsuarioModel) {
    //   return ResponseEntity.status(HttpStatus.CREATED)
    //         .body(cadastroUsuarioRepository.save(cadastroUsuarioModel)); }
    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroUsuarioModel> post(@RequestBody CadastroUsuarioModel usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cadastroUsuarioService.CadastrarUsuario(usuario));
    }
    @PostMapping("/logar")
    public ResponseEntity<CadastroUsuarioModel> Autentication(@RequestBody Optional<CadastroUsuarioModel> user) {
    return cadastroUsuarioService.Logar(user)
                .map(resp -> ResponseEntity.ok(resp))
               .orElse(ResponseEntity
                     .status(HttpStatus.UNAUTHORIZED)
                   .build());
    }

    @PutMapping
    public ResponseEntity<CadastroUsuarioModel> put(@RequestBody CadastroUsuarioModel cadastroUsuarioModel) {
        return ResponseEntity.ok(cadastroUsuarioRepository.save(cadastroUsuarioModel));
    }

    @DeleteMapping("/{idUsuario}")
    public void Delete(@PathVariable Long idUsuario) {
        cadastroUsuarioRepository.deleteById(idUsuario);
    }
}



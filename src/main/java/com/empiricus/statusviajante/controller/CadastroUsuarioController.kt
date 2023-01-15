package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.dto.UsuarioDto;
import com.empiricus.statusviajante.model.CadastroUsuarioModel;
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository;
import com.empiricus.statusviajante.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@Component
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    @Autowired
    private CadastroUsuarioRepository cadastroUsuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> GetAll() {
        return ResponseEntity.ok(cadastroUsuarioService.getAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> GetById(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(cadastroUsuarioService.getById(idUsuario));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CadastroUsuarioModel>> GetByNome(@PathVariable String nome) {
        return ResponseEntity.ok(cadastroUsuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity post(@RequestBody UsuarioDto usuarioDto) {
        try {
           return ResponseEntity.status(HttpStatus.CREATED)
                   .body(cadastroUsuarioService.CadastrarUsuario(usuarioDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
       }
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
    public ResponseEntity put(@RequestBody UsuarioDto usuarioDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cadastroUsuarioService.CadastrarUsuario(usuarioDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idUsuario}")
    public void Delete(@PathVariable Long idUsuario) {
        cadastroUsuarioService.deleteUser(idUsuario);
    }
}



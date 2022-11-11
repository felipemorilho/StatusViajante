package com.empiricus.statusviajante.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.empiricus.statusviajante.model.UsuarioLoginModel;
import com.empiricus.statusviajante.model.CadastroUsuarioModel;
import com.empiricus.statusviajante.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioLoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLoginModel> Autentication(@RequestBody Optional<UsuarioLoginModel> user){
        return usuarioService.Logar(user)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .build());
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroUsuarioModel> Post(@RequestBody CadastroUsuarioModel usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.CadastrarUsuario(usuario));
    }

}


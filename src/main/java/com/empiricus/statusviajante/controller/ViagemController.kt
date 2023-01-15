package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.ViagemModel;
import com.empiricus.statusviajante.service.ViagemService;
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
    private ViagemService viagemService;

    //Lista todas as Viagens
    @GetMapping
    public ResponseEntity<List<ViagemModel>> GetAll() {
        return ResponseEntity.ok(viagemService.buscarTodasViagens());
    }

    //Lista Viagem por ID
    @GetMapping("/{id}")
    public ResponseEntity<ViagemModel> GetById(@PathVariable Long id) {
        return viagemService.buscarViagemId(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    //Cria Viagem
    @PostMapping
    public ResponseEntity Post(@RequestBody ViagemModel viagem) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(viagemService.salvarViagem(viagem));
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity put(@RequestBody ViagemModel viagem) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(viagemService.salvarViagem(viagem));
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    //Deleta Viagem por ID
    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id) {
        viagemService.deletarViagemId(id);
    }

    //NOVOS ENDPOINTS
    //Lista Viagens por Usuario ID
    @GetMapping("usuario/{idUsuario}")
    public ResponseEntity<List<ViagemModel>> GetAllViagenByIdUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(viagemService.buscarViagemPorUsuario(idUsuario));
    }
}

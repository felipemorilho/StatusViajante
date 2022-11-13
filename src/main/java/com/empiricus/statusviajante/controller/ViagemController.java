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
    private ViagemRepository viagemRepository;

    //Lista todas as Viagens
    @GetMapping
    public ResponseEntity<List<ViagemModel>> GetAll(){
        return ResponseEntity.ok(viagemRepository.findAll());
    };

    //Lista Viagem por ID
    @GetMapping ("/{id}")
    public ResponseEntity<ViagemModel> GetById(@PathVariable Long id){
        return viagemRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    };

    //Cria Viagem
    @PostMapping
    public ResponseEntity<ViagemModel> Post(@RequestBody ViagemModel viagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagem));
    }
    @PutMapping
    public ResponseEntity<ViagemModel> put(@RequestBody ViagemModel viagem) {
    return ResponseEntity.ok(viagemRepository.save(viagem));
    }

    //Deleta Viagem por ID
    @DeleteMapping ("/{id}")
    public void Delete(@PathVariable Long id){
        viagemRepository.deleteById(id);
    }

    //NOVOS ENDPOINTS
    //Lista Viagens por Usuario ID
    @GetMapping("usuario/{idUsuario}")
    public ResponseEntity<List<ViagemModel>> GetAllViagenByIdUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(viagemRepository.findByUsuario_idUsuario(idUsuario));
    }
}

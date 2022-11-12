package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.GastoViagem;
import com.empiricus.statusviajante.repository.GastoViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/gasto_viagem")
public class GastoViagemController {

    @Autowired
    private GastoViagemRepository repositoryGastoViagem;

    @GetMapping
    public ResponseEntity<List<GastoViagem>> GetAll() {
        return ResponseEntity.ok(repositoryGastoViagem.findAll());
    }

    @GetMapping("/{idGasto}")
    public ResponseEntity<GastoViagem> GetById(@PathVariable Long idGasto) {
        return repositoryGastoViagem.findById(idGasto)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GastoViagem> post(@RequestBody GastoViagem gastoViagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryGastoViagem.save(gastoViagem));

    }
    @DeleteMapping("/{idGasto}")
    public void Delete(@PathVariable Long idGasto) {
        repositoryGastoViagem.deleteById(idGasto);
    }

    //NOVOS ENDPOINTS
    @GetMapping("viagem/{idViagem}")
    public ResponseEntity<List<GastoViagem>> GetAllGastosByIdViagem(@PathVariable Long idViagem) {
        return ResponseEntity.ok(repositoryGastoViagem.findByViagem_idViagem(idViagem));

    }
    @PutMapping("/{idGasto}")
    public ResponseEntity<GastoViagem> putGastoById( @RequestBody GastoViagem gastoViagem) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repositoryGastoViagem.save(gastoViagem));
    }
}

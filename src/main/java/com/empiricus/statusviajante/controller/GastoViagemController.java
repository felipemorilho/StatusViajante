package com.empiricus.statusviajante.controller;

import com.empiricus.statusviajante.model.CategoriaGastoModel;
import com.empiricus.statusviajante.model.GastoViagemModel;
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
    public ResponseEntity<List<GastoViagemModel>> GetAll() {
        return ResponseEntity.ok(repositoryGastoViagem.findAll());
    }

    @GetMapping("/{idGasto}")
    public ResponseEntity<GastoViagemModel> GetById(@PathVariable Long idGasto) {
        return repositoryGastoViagem.findById(idGasto)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GastoViagemModel> post(@RequestBody GastoViagemModel gastoViagemModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryGastoViagem.save(gastoViagemModel));

    }
    //@PutMapping
    //public ResponseEntity<GastoViagemModel> put(@RequestBody GastoViagemModel gastoViagemModel) {
        //return ResponseEntity.ok(repositoryGastoViagem.save(gastoViagemModel));
    //}
    @DeleteMapping("/{idGasto}")
    public void Delete(@PathVariable Long idGasto) {
        repositoryGastoViagem.deleteById(idGasto);
    }

    //NOVOS ENDPOINTS
    @GetMapping("viagem/{idViagem}")
    public ResponseEntity<List<GastoViagemModel>> GetAllGastosByIdViagem(@PathVariable Long idViagem) {
        return ResponseEntity.ok(repositoryGastoViagem.findByViagem_idViagem(idViagem));

    }
    @PutMapping("/{idGasto}")
    public ResponseEntity<GastoViagemModel> putGastoById( @RequestBody GastoViagemModel gastoViagem) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repositoryGastoViagem.save(gastoViagem));
    }
}

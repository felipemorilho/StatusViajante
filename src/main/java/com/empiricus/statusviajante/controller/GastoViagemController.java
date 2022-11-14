package com.empiricus.statusviajante.controller;

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
    private GastoViagemRepository gastoViagemRepository;

    @GetMapping
    public ResponseEntity<List<GastoViagemModel>> GetAll() {
        return ResponseEntity.ok(gastoViagemRepository.findAll());
    }

    @GetMapping("/{idGasto}")
    public ResponseEntity<GastoViagemModel> GetById(@PathVariable Long idGasto) {
        return gastoViagemRepository.findById(idGasto)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GastoViagemModel> post(@RequestBody GastoViagemModel gastoViagemModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoViagemRepository.save(gastoViagemModel));
    }

    @PutMapping
    public ResponseEntity<GastoViagemModel> put(@RequestBody GastoViagemModel gastoViagemModel) {
        return ResponseEntity.ok(gastoViagemRepository.save(gastoViagemModel));
    }

    @DeleteMapping("/{idGasto}")
    public void Delete(@PathVariable Long idGasto) {
        gastoViagemRepository.deleteById(idGasto);
    }

    //NOVOS ENDPOINTS
    @GetMapping("viagem/{idViagem}")
    public ResponseEntity<List<GastoViagemModel>> GetAllGastosByIdViagem(@PathVariable Long idViagem) {
        return ResponseEntity.ok(gastoViagemRepository.findByViagem_idViagem(idViagem));

    }

}

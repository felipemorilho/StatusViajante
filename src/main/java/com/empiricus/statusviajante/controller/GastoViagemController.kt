package com.empiricus.statusviajante.controller

import com.empiricus.statusviajante.model.GastoViagemModel
import com.empiricus.statusviajante.repository.GastoViagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.function.Function

@RestController
@CrossOrigin("*")
@RequestMapping("/gasto_viagem")
class GastoViagemController {
    @Autowired
    private val gastoViagemRepository: GastoViagemRepository? = null
    @GetMapping
    fun GetAll(): ResponseEntity<List<GastoViagemModel?>> {
        return ResponseEntity.ok(gastoViagemRepository!!.findAll())
    }

    @GetMapping("/{idGasto}")
    fun GetById(@PathVariable idGasto: Long?): ResponseEntity<GastoViagemModel> {
        return gastoViagemRepository!!.findById(idGasto!!)
            .map<ResponseEntity<GastoViagemModel>>(Function { response: GastoViagemModel? -> ResponseEntity.ok(response) })
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun post(@RequestBody gastoViagemModel: GastoViagemModel): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(gastoViagemRepository!!.save(gastoViagemModel))
        } catch (exception: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
        }
    }

    @PutMapping
    fun put(@RequestBody gastoViagemModel: GastoViagemModel): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(gastoViagemRepository!!.save(gastoViagemModel))
        } catch (exception: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
        }
    }

    @DeleteMapping("/{idGasto}")
    fun Delete(@PathVariable idGasto: Long) {
        gastoViagemRepository!!.deleteById(idGasto)
    }

    //NOVOS ENDPOINTS
    @GetMapping("viagem/{idViagem}")
    fun GetAllGastosByIdViagem(@PathVariable idViagem: Long?): ResponseEntity<List<GastoViagemModel?>?> {
        return ResponseEntity.ok(gastoViagemRepository!!.findByViagem_idViagem(idViagem))
    }
}
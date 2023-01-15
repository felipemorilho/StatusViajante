package com.empiricus.statusviajante.controller

import com.empiricus.statusviajante.model.ViagemModel
import com.empiricus.statusviajante.service.ViagemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.function.Function

@RestController
@RequestMapping("/viagens")
@CrossOrigin("*")
class ViagemController {
    @Autowired
    private val viagemService: ViagemService? = null

    //Lista todas as Viagens
    @GetMapping
    fun GetAll(): ResponseEntity<List<ViagemModel?>> {
        return ResponseEntity.ok(viagemService!!.buscarTodasViagens())
    }

    //Lista Viagem por ID
    @GetMapping("/{id}")
    fun GetById(@PathVariable id: Long?): ResponseEntity<ViagemModel> {
        return viagemService!!.buscarViagemId(id)
            .map<ResponseEntity<ViagemModel>>(Function { resp: ViagemModel? -> ResponseEntity.ok(resp) })
            .orElse(ResponseEntity.notFound().build())
    }

    //Cria Viagem
    @PostMapping
    @Throws(Exception::class)
    fun Post(@RequestBody viagem: ViagemModel?): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(viagemService!!.salvarViagem(viagem!!))
        } catch (exception: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
        }
    }

    @PutMapping
    fun put(@RequestBody viagem: ViagemModel?): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(viagemService!!.salvarViagem(viagem!!))
        } catch (exception: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
        }
    }

    //Deleta Viagem por ID
    @DeleteMapping("/{id}")
    fun Delete(@PathVariable id: Long?) {
        viagemService!!.deletarViagemId(id!!)
    }

    //NOVOS ENDPOINTS
    //Lista Viagens por Usuario ID
    @GetMapping("usuario/{idUsuario}")
    fun GetAllViagenByIdUsuario(@PathVariable idUsuario: Long?): ResponseEntity<List<ViagemModel?>?> {
        return ResponseEntity.ok(viagemService!!.buscarViagemPorUsuario(idUsuario))
    }
}
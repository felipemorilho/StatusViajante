package com.empiricus.statusviajante.controller

import com.empiricus.statusviajante.model.CadastroUsuarioModel
import com.empiricus.statusviajante.model.ViagemModel
import com.empiricus.statusviajante.service.CadastroUsuarioService
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

    @Autowired
    private val cadastroUsuarioService: CadastroUsuarioService? = null

    //Lista todas as Viagens
    @GetMapping
    fun GetAll(): ResponseEntity<List<ViagemModel?>> {
        var currentUserId = cadastroUsuarioService?.getCurrentUserId()

        return ResponseEntity.ok(viagemService?.buscarViagemPorUsuario(currentUserId))
    }

    @GetMapping("/{idViagem}")
    fun GetById(@PathVariable idViagem: Long): ResponseEntity<ViagemModel> {
        var currentUserId = cadastroUsuarioService?.getCurrentUserId()

        return currentUserId?.let {
            viagemService!!.buscarViagemId(idViagem, it)
                ?.map<ResponseEntity<ViagemModel>>(Function { resp: ViagemModel? -> ResponseEntity.ok(resp) })
                ?.orElse(ResponseEntity.notFound().build())
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    @Throws(Exception::class)
    fun Post(@RequestBody viagem: ViagemModel?): ResponseEntity<*> {
        return try {
            var currentIdUser = cadastroUsuarioService?.getCurrentUserId()
            viagem?.usuario = CadastroUsuarioModel(currentIdUser)

            ResponseEntity.status(HttpStatus.CREATED).body(viagemService!!.salvarViagem(viagem!!))
        } catch (exception: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
        }
    }

    @PutMapping
    fun put(@RequestBody viagem: ViagemModel?): ResponseEntity<*> {

        var currentIdUser = cadastroUsuarioService?.getCurrentUserId()
        var currentViagem = viagemService?.buscarViagemId(viagem?.idViagem!!, currentIdUser!!)

        if(currentViagem?.get()?.usuario?.idUsuario?.equals(currentIdUser)!!) {
            try {
               return ResponseEntity.status(HttpStatus.CREATED).body(viagemService!!.salvarViagem(viagem!!))
            } catch (exception: Exception) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
            }
        } else {
            return ResponseEntity.badRequest().body("Modificação não permitida.")
        }
    }

    @DeleteMapping("/{idViagem}")
    fun delete(@PathVariable idViagem: Long?): ResponseEntity<String> {

        var currentIdUser = cadastroUsuarioService?.getCurrentUserId()
        var currentViagem = viagemService?.buscarViagemId(idViagem!!, currentIdUser!!)

        if (currentViagem != null ) {
            if (currentViagem.get().usuario?.idUsuario?.equals(currentIdUser)!!) {
                viagemService?.deletarViagemId(idViagem)
            } else {
                return ResponseEntity.badRequest().body("Viagem não pode ser deletada")
            }
        }
       return ResponseEntity.ok().body("Viagem deletada")
    }

    //NOVOS ENDPOINTS
    //Lista Viagens por Usuario ID
    @GetMapping("usuario/{idUsuario}")
    fun GetAllViagenByIdUsuario(@PathVariable idUsuario: Long?): ResponseEntity<List<ViagemModel?>?> {
        return ResponseEntity.ok(viagemService!!.buscarViagemPorUsuario(idUsuario))
    }
}
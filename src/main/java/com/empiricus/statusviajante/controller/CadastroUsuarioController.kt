package com.empiricus.statusviajante.controller

import com.empiricus.statusviajante.dto.UsuarioDto
import com.empiricus.statusviajante.model.CadastroUsuarioModel
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository
import com.empiricus.statusviajante.service.CadastroUsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.function.Function

@RestController
@Component
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/usuarios")
class CadastroUsuarioController {
    @Autowired
    private val cadastroUsuarioRepository: CadastroUsuarioRepository? = null

    @Autowired
    private val cadastroUsuarioService: CadastroUsuarioService? = null
    @GetMapping
    fun GetAll(): ResponseEntity<List<UsuarioDto>> {
        return ResponseEntity.ok(cadastroUsuarioService!!.all)
    }

    @GetMapping("/{idUsuario}")
    fun GetById(@PathVariable idUsuario: Long?): ResponseEntity<UsuarioDto> {
        return ResponseEntity.ok(cadastroUsuarioService!!.getById(idUsuario!!))
    }

    @GetMapping("/nome/{nome}")
    fun GetByNome(@PathVariable nome: String?): ResponseEntity<List<CadastroUsuarioModel?>?> {
        return ResponseEntity.ok(cadastroUsuarioRepository!!.findAllByNomeContainingIgnoreCase(nome))
    }

    @PostMapping("/cadastrar")
    fun post(@RequestBody usuarioDto: UsuarioDto?): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED)
                .body(cadastroUsuarioService!!.CadastrarUsuario(usuarioDto!!))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/logar")
    fun Autentication(@RequestBody user: Optional<CadastroUsuarioModel>): ResponseEntity<CadastroUsuarioModel> {
        return cadastroUsuarioService!!.Logar(user)
            ?.map(Function { resp: CadastroUsuarioModel -> ResponseEntity.ok(resp) })
            ?.orElse(
                ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build()
            )!!
    }

    @PutMapping
    fun put(@RequestBody usuarioDto: UsuarioDto?): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED)
                .body(cadastroUsuarioService!!.CadastrarUsuario(usuarioDto!!))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @DeleteMapping("/{idUsuario}")
    fun Delete(@PathVariable idUsuario: Long?) {
        cadastroUsuarioService!!.deleteUser(idUsuario!!)
    }
}
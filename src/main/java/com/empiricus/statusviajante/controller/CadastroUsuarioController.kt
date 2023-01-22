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
    fun GetById(@PathVariable idUsuario: Long?): Any? {
        var currentIdUser = cadastroUsuarioService?.getCurrentUserId()

        return if (idUsuario == currentIdUser) {
            ResponseEntity.ok(cadastroUsuarioService!!.getById(idUsuario!!))
        } else {
            ResponseEntity.badRequest().body("User not found or operation not permitted.")
        }
    }

    //TODO: ARRUMAR RETORNO METODO
    @GetMapping("/nome/{nome}")
    fun GetByNome(@PathVariable nome: String?): Any? {
        var currentIdUser = cadastroUsuarioService?.getCurrentUserId()

        var users = cadastroUsuarioRepository!!.findAllByNomeContainingIgnoreCase(nome)

        var foundUsers = users?.filter { user -> user?.idUsuario == currentIdUser }

        return ResponseEntity.ok(foundUsers)
    }

    @PostMapping("/cadastrar")
    fun post(@RequestBody usuarioDto: UsuarioDto?): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED)
                .body(cadastroUsuarioService!!.CadastrarUsuario(usuarioDto!!))
        } catch (e: Exception)  {
            e.printStackTrace()
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/logar")
    fun Autentication(@RequestBody user: Optional<CadastroUsuarioModel>): ResponseEntity<CadastroUsuarioModel> {
        return cadastroUsuarioService!!.logar(user)
            ?.map(Function { resp: CadastroUsuarioModel -> ResponseEntity.ok(resp) })
            ?.orElse(
                ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build()
            )!!
    }

    @PutMapping
    fun put(@RequestBody usuarioDto: UsuarioDto?): ResponseEntity<*> {
        var currentIdUser = cadastroUsuarioService?.getCurrentUserId()

        if (usuarioDto?.idUsuario?.equals(currentIdUser)!!) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cadastroUsuarioService!!.CadastrarUsuario(usuarioDto!!))
            } catch (e: Exception) {
                ResponseEntity.badRequest().body(e.message)
            }
        } else {
            return ResponseEntity.badRequest().body("Modificação não permitida.")
        }

        return ResponseEntity.ok().body("Modificação aceita.")
    }

    @DeleteMapping("/{idUsuario}")
    fun delete(@PathVariable idUsuario: Long?): ResponseEntity<String> {

        var currentIdUser = cadastroUsuarioService?.getCurrentUserId()
        if (idUsuario != null) {
            if (idUsuario.equals(currentIdUser)) {
                cadastroUsuarioService?.deleteUser(idUsuario)
            } else {
                return ResponseEntity.badRequest().body("User not found or operation not permitted.")
            }
        }
        return ResponseEntity.ok().body("Usuario deletado")
    }
}

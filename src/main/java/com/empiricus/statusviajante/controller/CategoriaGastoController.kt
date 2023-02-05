package com.empiricus.statusviajante.controller

import com.empiricus.statusviajante.model.CategoriaGastoModel
import com.empiricus.statusviajante.repository.CategoriaGastoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.function.Function

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
class CategoriaGastoController {
    @Autowired
    private val categoriaGastoRepository: CategoriaGastoRepository? = null
    @GetMapping
    fun GetAll(): ResponseEntity<List<CategoriaGastoModel?>> {
        return ResponseEntity.ok(categoriaGastoRepository!!.findAll())
    }

    @GetMapping("/{idCategoria}")
    fun GetById(@PathVariable idCategoria: Long): ResponseEntity<CategoriaGastoModel> {
        return categoriaGastoRepository!!.findById(idCategoria)
            .map<ResponseEntity<CategoriaGastoModel>>(Function { resp: CategoriaGastoModel? -> ResponseEntity.ok(resp) })
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/nome/{nome}")
    fun GetByNome(@PathVariable nome: String?): ResponseEntity<List<CategoriaGastoModel>> {
        return ResponseEntity.ok(
            categoriaGastoRepository!!.findAllByNomeContainingIgnoreCase(
                nome!!
            )
        )
    }

//    @PostMapping
//    fun post(@RequestBody categoriaGastoModel: CategoriaGastoModel): ResponseEntity<*> {
//        return try {
//            ResponseEntity.status(HttpStatus.CREATED).body(categoriaGastoRepository!!.save(categoriaGastoModel))
//        } catch (exception: Exception) {
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
//        }
//    }

//    @PutMapping
//    fun put(@RequestBody categoriaGastoModel: CategoriaGastoModel): ResponseEntity<*> {
//        return try {
//            ResponseEntity.ok(categoriaGastoRepository!!.save(categoriaGastoModel))
//        } catch (exception: Exception) {
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
//        }
//    }
//
//    @DeleteMapping("/{idCategoria}")
//    fun Delete(@PathVariable idCategoria: Long) {
//        categoriaGastoRepository!!.deleteById(idCategoria)
//    }
}
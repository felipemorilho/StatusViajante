package com.empiricus.statusviajante.repository

import com.empiricus.statusviajante.model.CadastroUsuarioModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CadastroUsuarioRepository : JpaRepository<CadastroUsuarioModel?, Long?> {
    fun findAllByNomeContainingIgnoreCase(nome: String?): List<CadastroUsuarioModel?>?
    fun findByUsuario(username: String?): Optional<CadastroUsuarioModel?>?
}
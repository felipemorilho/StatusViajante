package com.empiricus.statusviajante.repository

import com.empiricus.statusviajante.model.CategoriaGastoModel
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriaGastoRepository : JpaRepository<CategoriaGastoModel?, Long?> {
    fun findAllByNomeContainingIgnoreCase(nome: String): List<CategoriaGastoModel>
}
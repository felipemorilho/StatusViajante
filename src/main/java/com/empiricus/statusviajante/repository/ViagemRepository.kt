package com.empiricus.statusviajante.repository

import com.empiricus.statusviajante.model.ViagemModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface ViagemRepository : JpaRepository<ViagemModel?, Long?> {

    fun findById(idViagem: Long): Optional<ViagemModel?>

    fun findByUsuario_idUsuario(idViagem: Long?): List<ViagemModel?>?

    fun findByUsuario_idUsuarioAndIdViagem(idUsuario: Long?, idViagem: Long): Optional<ViagemModel?>

    @Transactional
    fun deleteByUsuario_idUsuario(idUsuario: Long?) : Int

}
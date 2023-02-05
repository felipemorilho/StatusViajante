package com.empiricus.statusviajante.repository

import com.empiricus.statusviajante.model.GastoViagemModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface GastoViagemRepository : JpaRepository<GastoViagemModel?, Long?> {

    fun findById(idGastoViagem: Long): Optional<GastoViagemModel?>

    fun findByViagem_idViagem(idViagem: Long?): List<GastoViagemModel?>?

    @Transactional
    fun deleteByViagem_idViagem(idViagem: Long?) : Int?
}
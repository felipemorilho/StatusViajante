
package com.empiricus.statusviajante.service

import com.empiricus.statusviajante.model.ViagemModel
import com.empiricus.statusviajante.repository.GastoViagemRepository
import com.empiricus.statusviajante.repository.ViagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ViagemService {
    @Autowired
    private val viagemRepository: ViagemRepository? = null
    @Autowired
    private val gastoViagemRepository : GastoViagemRepository? = null

    @Throws(Exception::class)
    fun salvarViagem(viagemModel: ViagemModel): ViagemModel {
        val dataIda = viagemModel.dataIda
        val dataVolta = viagemModel.dataVolta
        return if (dataIda!!.compareTo(dataVolta) > 0) {
            throw Exception("Data de ida não pode ser maior que a data de volta.")
        } else {
            viagemRepository!!.save(viagemModel)
        }
    }

    fun buscarViagemId(idViagem: Long, idUser : Long): Optional<ViagemModel?>? {
        return  viagemRepository?.findByUsuario_idUsuarioAndIdViagem(idUser, idViagem)
    }

    fun deletarViagemId(idViagem: Long?) {
        gastoViagemRepository!!.deleteByViagem_idViagem(idViagem)
        return viagemRepository!!.deleteById(idViagem)
    }

    fun buscarViagemPorUsuario(idUsuario: Long?): List<ViagemModel?>? {
        return viagemRepository!!.findByUsuario_idUsuario(idUsuario)
    }
}

package com.empiricus.statusviajante.service

import com.empiricus.statusviajante.model.ViagemModel
import com.empiricus.statusviajante.repository.ViagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ViagemService {
    @Autowired
    private val viagemRepository: ViagemRepository? = null
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

    fun buscarTodasViagens(): List<ViagemModel?> {
        return viagemRepository!!.findAll()
    }

    fun buscarViagemId(id: Long?): Optional<ViagemModel?> {
        return viagemRepository!!.findById(id!!)
    }

    fun deletarViagemId(id: Long) {
        viagemRepository!!.deleteById(id)
    }

    fun buscarViagemPorUsuario(idUsuario: Long?): List<ViagemModel?>? {
        return viagemRepository!!.findByUsuario_idUsuario(idUsuario)
    }
}
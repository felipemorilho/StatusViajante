package com.empiricus.statusviajante.dto

import com.empiricus.statusviajante.model.CadastroUsuarioModel
import java.util.*

class UsuarioDto {
    var idUsuario: Long? = null
    var nome: String? = null
    var dataNascimento: Date? = null
    var email: String? = null
    var celular: String? = null
    var usuario: String? = null
    var senha: String? = null
    var senhaEncoded: String? = null
    var token: String? = null
    fun toModel(): CadastroUsuarioModel {
        val cadastroUsuarioModel = CadastroUsuarioModel()
        cadastroUsuarioModel.nome = nome!!
        cadastroUsuarioModel.dataNascimento = dataNascimento
        cadastroUsuarioModel.idUsuario = idUsuario
        cadastroUsuarioModel.email = email!!
        cadastroUsuarioModel.celular = celular!!
        cadastroUsuarioModel.usuario = usuario!!
        cadastroUsuarioModel.senha = senhaEncoded!!
        cadastroUsuarioModel.token = token
        return cadastroUsuarioModel
    }
}
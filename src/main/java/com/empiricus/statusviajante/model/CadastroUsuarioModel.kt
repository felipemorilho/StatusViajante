package com.empiricus.statusviajante.model

import com.empiricus.statusviajante.dto.UsuarioDto
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "usuario", uniqueConstraints = [UniqueConstraint(columnNames = ["usuario"])])
class CadastroUsuarioModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUsuario: Long? = null,

    @field:NotBlank(message = "O nome deve ser preenchido.")
    var nome:  String = "",

    @field:NotNull(message = "A data de nascimento deve ser preenchida.")
    var dataNascimento: Date? = null,

    @field:NotBlank(message = "O email deve ser preenchido.")
    @field:Email
    var email:  String = "",

    @field:NotNull(message = "O celular deve ser preenchido.")
    var celular: String = "",

    @field:NotNull(message = "O usuário deve ser preenchido.")
    var usuario:  String = "",

    @field:NotNull(message = "A senha deve ser preenchida.")
    var senha: String = ""
) {
    var token: String? = null
    fun toDto(): UsuarioDto {
        val usuarioDto = UsuarioDto()
        usuarioDto.nome = nome
        usuarioDto.dataNascimento = dataNascimento
        usuarioDto.idUsuario = idUsuario
        usuarioDto.email = email
        usuarioDto.celular = celular
        usuarioDto.usuario = usuario
        usuarioDto.senha = senha
        usuarioDto.token = token
        return usuarioDto
    }

    @OneToMany(mappedBy = "usuario")
    private val viagens: Set<ViagemModel> = HashSet()
}
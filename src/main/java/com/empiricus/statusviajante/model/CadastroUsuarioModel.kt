package com.empiricus.statusviajante.model

import com.empiricus.statusviajante.dto.UsuarioDto
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "usuario", uniqueConstraints = [UniqueConstraint(columnNames = ["usuario"])])
class CadastroUsuarioModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUsuario: Long? = null,

    var nome: @NotBlank(message = "O nome deve ser preenchido.") @Size(
        min = 3,
        max = 50,
        message = "O nome deve ter mais de 3 e menos de 50 caracteres."
    ) String = "",

    var dataNascimento: @NotNull(message = "A data de nascimento deve ser preenchida.") Date? = null,

    var email: @NotBlank(message = "O email deve ser preenchido.") @Size(
        min = 3,
        max = 50,
        message = "O email deve ter entre 3 e 50 caracteres."
    ) String = "",

    var celular: @NotBlank(message = "O celular deve ser preenchido.") @Size(
        min = 11,
        max = 11,
        message = "O celular deve ser no formato DDD+número, com 11 números."
    ) String = "",

    var usuario: @NotBlank @Size(
        min = 5,
        max = 20,
        message = "O Nome de USuário deve ter entre 5 e 20 caracteres."
    ) String = "",

    var senha: @NotBlank String = ""
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
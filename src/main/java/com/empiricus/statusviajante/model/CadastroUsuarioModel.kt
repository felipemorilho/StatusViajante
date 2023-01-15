package com.empiricus.statusviajante.model

import com.empiricus.statusviajante.dto.UsuarioDto
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "usuario", uniqueConstraints = [UniqueConstraint(columnNames = ["usuario"])])
class CadastroUsuarioModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUsuario: Long? = null,

    //@Pattern(regexp = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "O nome é inválido.")
    var nome: @NotBlank(message = "O nome deve ser preenchido.") @Size(
        min = 3,
        max = 50,
        message = "O nome deve ter mais de 3 e menos de 50 caracteres."
    ) String = "",

    // mudei de NotBlank para NotNull
    //@Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}", flags = {Pattern.Flag.MULTILINE}, message = "A data deve ser no formato dd/mm/aaaa.")
    var dataNascimento: @NotNull(message = "A data de nascimento deve ser preenchida.") Date? = null,

    //@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "O email é inválido.")
    var email: @NotBlank(message = "O email deve ser preenchido.") @Size(
        min = 3,
        max = 50,
        message = "O email deve ter entre 3 e 50 caracteres."
    ) String = "",

    //@Pattern(regexp = "^((\\b[0-9]{11,11}\\b)*){0,}$", message = "O celular deve conter apenas números.")
    var celular: @NotBlank(message = "O celular deve ser preenchido.") @Size(
        min = 11,
        max = 11,
        message = "O celular deve ser no formato DDD+número, com 11 números."
    ) String = "",

    //@Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "O Nome de Usuário não suportado.")
    var usuario: @NotBlank @Size(
        min = 5,
        max = 20,
        message = "O Nome de USuário deve ter entre 5 e 20 caracteres."
    ) String = "",

    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Senha não suportada.")
    var senha: @NotBlank String = ""
        ){
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
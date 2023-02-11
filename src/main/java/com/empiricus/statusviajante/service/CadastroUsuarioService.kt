
package com.empiricus.statusviajante.service

import com.empiricus.statusviajante.dto.UsuarioDto
import com.empiricus.statusviajante.model.CadastroUsuarioModel
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository
import com.empiricus.statusviajante.repository.GastoViagemRepository
import org.apache.commons.codec.binary.Base64
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

//#Lógica de acesso do usuário, aqui verifica como o usuário vai se cadastrar e logar
@Service
class CadastroUsuarioService(
    private val cadastroUsuarioRepository: CadastroUsuarioRepository,
    private  val viagemService : ViagemService,
    private val gastoViagemRepository : GastoViagemRepository
){


    @Throws(Exception::class)
    fun cadastrarUsuario(usuarioDto: UsuarioDto): CadastroUsuarioModel {
        val validadorSenha = Pattern.compile(PASSWORD_PATTERN)
        val matcher = usuarioDto.senha?.let { validadorSenha.matcher(it) }
        if (matcher?.matches() == false) {
            throw Exception("Senha inválida.")
        }
        val encoder = BCryptPasswordEncoder()
        val senhaEncoded = encoder.encode(usuarioDto.senha)
        usuarioDto.senhaEncoded = senhaEncoded
        return cadastroUsuarioRepository.save(usuarioDto.toModel())
    }

    val all: List<UsuarioDto>
        get() {
            val cadastroUsuarioModels = cadastroUsuarioRepository.findAll()
            return cadastroUsuarioModels.stream()
                .map { cadastroUsuarioModel: CadastroUsuarioModel? -> cadastroUsuarioModel!!.toDto() }.toList()
        }

    fun getById(id: Long): UsuarioDto {
        val cadastroUsuarioModel = cadastroUsuarioRepository.findById(id).get()
        return cadastroUsuarioModel.toDto()
    }

    fun deleteUser(idUsuario: Long?) {
        val listaViagens = viagemService.buscarViagemPorUsuario(idUsuario)
        if (listaViagens != null) {
            for (viagem in listaViagens) {
                gastoViagemRepository.deleteByViagem_idViagem(viagem?.idViagem)
            }
        }

        viagemService.deletarViagemByUser(idUsuario)

        return idUsuario.let {
            if (it != null) {
                cadastroUsuarioRepository.deleteById(it)
            }
        }
    }

    fun logar(user: Optional<CadastroUsuarioModel>): Optional<CadastroUsuarioModel>? {
        val encoder = BCryptPasswordEncoder()
        val usuario = cadastroUsuarioRepository.findByUsuario(user.get().usuario)
        if (usuario!!.isPresent) {
            if (encoder.matches(user.get().senha, usuario.get().senha)) {
                val auth = user.get().usuario + ":" + user.get().senha
                val encodedAuth = Base64.encodeBase64(auth.toByteArray(Charset.forName("US-ASCII")))
                val authHeader = "Basic " + String(encodedAuth)
                user.get().token = authHeader
                user.get().nome = usuario.get().nome
                user.get().senha = ""
                return user
            }
        }
        return null
    }

    fun getCurrentUserId(): Long? {
        val loggedUsername = SecurityContextHolder.getContext().authentication.name
        return cadastroUsuarioRepository.findByUsuario(loggedUsername)?.get()?.idUsuario
    }

    companion object {
        /**
         * Password must contain at least one digit [0-9].
         * Password must contain at least one lowercase Latin character [a-z].
         * Password must contain at least one uppercase Latin character [A-Z].
         * Password must contain at least one special character like ! @ # & ( ).
         * Password must contain a length of at least 8 characters and a maximum of 20 characters.
         */
        private const val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
    }
}
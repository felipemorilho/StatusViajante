
package com.empiricus.statusviajante.service

import com.empiricus.statusviajante.dto.UsuarioDto
import com.empiricus.statusviajante.model.CadastroUsuarioModel
import com.empiricus.statusviajante.repository.CadastroUsuarioRepository
import com.empiricus.statusviajante.repository.GastoViagemRepository
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

//#Lógica de acesso do usuário, aqui verifica como o usuário vai se cadastrar e logar
@Service
class CadastroUsuarioService {
    @Autowired
    private val cadastroUsuarioRepository: CadastroUsuarioRepository? = null

    @Autowired
    private  val viagemService : ViagemService? = null

    @Autowired
    private val gastoViagemRepository : GastoViagemRepository? = null

    @Throws(Exception::class)
    fun CadastrarUsuario(usuarioDto: UsuarioDto): CadastroUsuarioModel {
        val validadorSenha = Pattern.compile(PASSWORD_PATTERN)
        val matcher = validadorSenha.matcher(usuarioDto.senha)

        val validadorNome = Pattern.compile(NOME_PATTERN)
        val matcherNome = validadorNome.matcher(usuarioDto.nome)

        val validadorCelular = Pattern.compile(CELULAR_PATTERN)
        val matcherCelular = validadorCelular.matcher(usuarioDto.celular)

        val validadorUsuario = Pattern.compile(USUARIO_PATTERN)
        val matcherUsuario = validadorUsuario.matcher((usuarioDto.usuario))

        if (matcher.matches() == false) {
            throw Exception("Senha inválida.")
        }
        if (matcherNome.matches() == false){
            throw Exception("Nome inválido")
        }
        if (matcherCelular.matches() == false){
            throw Exception("Número de celular inválido")
        }
        if (matcherUsuario.matches() == false){
            throw Exception("Nome de usuário inválido.")
        }
        val encoder = BCryptPasswordEncoder()
        val senhaEncoded = encoder.encode(usuarioDto.senha)
        usuarioDto.senhaEncoded = senhaEncoded
        return cadastroUsuarioRepository!!.save(usuarioDto.toModel())
    }

    val all: List<UsuarioDto>
        get() {
            val cadastroUsuarioModels = cadastroUsuarioRepository!!.findAll()
            return cadastroUsuarioModels.stream()
                .map { cadastroUsuarioModel: CadastroUsuarioModel? -> cadastroUsuarioModel!!.toDto() }.toList()
        }

    fun getById(id: Long): UsuarioDto {
        val cadastroUsuarioModel = cadastroUsuarioRepository!!.findById(id).get()
        return cadastroUsuarioModel.toDto()
    }

    fun deleteUser(idUsuario: Long?) {
        val listaViagens = viagemService?.buscarViagemPorUsuario(idUsuario)
        if (listaViagens != null) {
            for (viagem in listaViagens) {
                gastoViagemRepository?.deleteByViagem_idViagem(viagem?.idViagem)
            }
        }

        viagemService?.deletarViagemByUser(idUsuario)

        return cadastroUsuarioRepository!!.deleteById(idUsuario)
    }

    fun logar(user: Optional<CadastroUsuarioModel>): Optional<CadastroUsuarioModel>? {
        val encoder = BCryptPasswordEncoder()
        val usuario = cadastroUsuarioRepository!!.findByUsuario(user.get().usuario)
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

    fun getCurrentUserId() : Long? {
        var loggedUsername = SecurityContextHolder.getContext().authentication.name
        var currentIdUser = cadastroUsuarioRepository?.findByUsuario(loggedUsername)?.get()?.idUsuario

        return currentIdUser
    }

    companion object {

        private const val NOME_PATTERN = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$"
        private const val CELULAR_PATTERN = "^((\\b[0-9]{11,11}\\b)*){0,}$"
        private const val USUARIO_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$"

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
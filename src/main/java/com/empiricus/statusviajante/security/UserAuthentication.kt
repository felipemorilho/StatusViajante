package com.empiricus.statusviajante.security

import com.empiricus.statusviajante.repository.CadastroUsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

//#Faz a verificação se o usuário está correto de acordo com o Banco de Dados, caso contrario apresenta mensagem de erro
@Service
class UserAuthentication : UserDetailsService {
    @Autowired
    private val cadastroUsuarioRepository: CadastroUsuarioRepository? = null
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val usuario = cadastroUsuarioRepository!!.findByUsuario(username)
        return if (usuario!!.isPresent) UserLogin(usuario.get()) else throw ResponseStatusException(HttpStatus.FORBIDDEN)
    }
}
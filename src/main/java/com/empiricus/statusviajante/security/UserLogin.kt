package com.empiricus.statusviajante.security

import com.empiricus.statusviajante.model.CadastroUsuarioModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

//#Nessa classe pegamos o usuario e senha da classe de cadastro para autorizar o acesso ao sistema
class UserLogin : UserDetails {
    private var userName: String? = null
    private var password: String? = null

    constructor(user: CadastroUsuarioModel) {
        userName = user.usuario
        password = user.senha
    }

    constructor() {}

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return emptyList()
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return userName!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
package com.empiricus.statusviajante.dto;


import com.empiricus.statusviajante.model.CadastroUsuarioModel;

import java.util.Date;

public class UsuarioDto {

    private Long idUsuario;

    private String nome;

    private Date dataNascimento;

    private String email;

    private String celular;

    private String usuario;

    private String senha;

    private String senhaEncoded;

    private String token;

    public CadastroUsuarioModel toModel() {
        CadastroUsuarioModel cadastroUsuarioModel = new CadastroUsuarioModel();

        cadastroUsuarioModel.setNome(this.getNome());
        cadastroUsuarioModel.setDataNascimento(this.getDataNascimento());
        cadastroUsuarioModel.setIdUsuario(this.getIdUsuario());
        cadastroUsuarioModel.setEmail(this.getEmail());
        cadastroUsuarioModel.setCelular(this.getCelular());
        cadastroUsuarioModel.setUsuario(this.getUsuario());
        cadastroUsuarioModel.setSenha(this.getSenhaEncoded());
        cadastroUsuarioModel.setToken(this.getToken());

        return cadastroUsuarioModel;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaEncoded() {
        return senhaEncoded;
    }

    public void setSenhaEncoded(String senhaEncoded) {
        this.senhaEncoded = senhaEncoded;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

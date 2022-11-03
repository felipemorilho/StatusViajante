package com.empiricus.statusviajante.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "usuario")
public class CadastroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @NotNull
    @Size(min = 10, max = 10)
    private String dataNascimento;

    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    @Size(min = 10, max = 11)
    private String celular;

    @NotNull
    @Size(min = 3, max = 15)
    private String senha;

//    @NotNull
//    @Size(min = 3, max = 200)
//    private String nomeUsuario;
//
//    @OneToMany
//    @JsonIgnoreProperties("usuario")
//    private ViagemModel viagem;

//    public ViagemModel getViagem() {
//        return viagem;
//    }
//
//    public void setViagem(ViagemModel viagem) {
//        this.viagem = viagem;
//    }

    @OneToMany(mappedBy = "cadastroUsuario")
    private Set<GastoViagem> gastosViagem = new HashSet<>();

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

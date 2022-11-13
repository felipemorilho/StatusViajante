package com.empiricus.statusviajante.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "usuario")
public class CadastroUsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    //Esse campo deve ter pelo menos um nome e um Sobrenome
    @NotBlank(message = "O nome deve ser preenchido.")
    @Size(min = 3, max = 50, message = "O nome deve ter mais de 3 e menos de 50 caracteres.")
    @Pattern(regexp = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$", flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE }, message = "O nome é inválido.")
    private String nome;

    @NotNull(message = "A data de nascimento deve ser preenchida.")
    private Date dataNascimento;

    @NotBlank(message = "O email deve ser preenchido.")
    @Email(message = "O email deve ser preenchido.")
    private String email;

    //O número de celular deve ser no formato DDD+número, contendo 11 caracteres
    //Ex.: 11998856235
    @NotBlank(message = "O celular deve ser preenchido.")
    @Size(min = 11, max = 11, message = "O celular deve ser no formato DDD+número, com 11 números.")
    @Pattern(regexp = "^((\\b[0-9]{11,11}\\b)*){0,}$", message = "O celular deve conter apenas números.")
    private String celular;

    //Senha contendo ao menos 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caracter especial.
    @NotBlank
    @Size(min = 3, max = 15, message = "A senha deve ter entre 3 e 15 caracteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Senha não suportada.")
    private String senha;

    //nome de usuário ent3 e 18 caracteres, sem espaços e podendo usar números, letra e os caracteres "." "-" e "_"
    @NotBlank
    @Size(min = 5, max = 20, message = "O Nome de USuário deve ter entre 5 e 20 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "O Nome de Usuário não suportado.")
    private String nomeUsuario;

    @OneToMany(mappedBy = "usuario")
    private Set<ViagemModel> viagens = new HashSet<>();

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

}

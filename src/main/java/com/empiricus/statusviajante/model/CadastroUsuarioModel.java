package com.empiricus.statusviajante.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class CadastroUsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "O nome deve ser preenchido.")
    @Size(min = 3, max = 50, message = "O nome deve ter mais de 3 e menos de 50 caracteres.")
    //@Pattern(regexp = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "O nome é inválido.")
    private String nome;

    @NotBlank(message = "A data de nascimento deve ser preenchida.")
    //@Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}", flags = {Pattern.Flag.MULTILINE}, message = "A data deve ser no formato dd/mm/aaaa.")
    private String dataNascimento;

    @NotBlank(message = "O email deve ser preenchido.")
    @Size(min = 3, max = 50, message = "O email deve ter entre 3 e 50 caracteres.")
    //@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "O email é inválido.")
    private String email;

    @NotBlank(message = "O celular deve ser preenchido.")
    @Size(min = 11, max = 11, message = "O celular deve ser no formato DDD+número, com 11 números.")
   // @Pattern(regexp = "^((\\b[0-9]{11,11}\\b)*){0,}$", message = "O celular deve conter apenas números.")
    private String celular;

    @NotBlank
    @Size(min = 5, max = 20, message = "O Nome de USuário deve ter entre 5 e 20 caracteres.")
   // @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "O Nome de Usuário não suportado.")
    private String usuario;

    @NotBlank
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Senha não suportada.")
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String Usuario) {
        this.usuario = Usuario;
    }

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

    public String getDataNascimento() { return dataNascimento;
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

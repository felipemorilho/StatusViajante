package com.empiricus.statusviajante.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "viagens")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViagem;

    @NotNull
    @Size(min = 3, max = 100)
    private String nome_viagem;

    @Size(min = 3, max = 100)
    private String origem;

    @Size(min = 3, max = 100)
    private String destino;

    @Size(min = 3, max = 100)
    private String moeda;

    @Temporal(TemporalType.DATE)
    private Date data_ida;

    @Temporal(TemporalType.DATE)
    private Date data_volta;

    @DecimalMin(value = "0.1")
    private Double orcamento;

    @Min(value = 1)
    private Integer qtd_pessoas;

    @Size(min = 3, max = 200)
    private String descricao_viagem;

    @OneToMany(mappedBy = "viagem")
    private Set<GastoViagemModel> gastosViagems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private CadastroUsuarioModel usuario;

    public Long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Long idViagem) {
        this.idViagem = idViagem;
    }

    public String getNome_viagem() {
        return nome_viagem;
    }

    public void setNome_viagem(String nome_viagem) {
        this.nome_viagem = nome_viagem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Date getData_ida() {
        return data_ida;
    }

    public void setData_ida(Date data_ida) {
        this.data_ida = data_ida;
    }

    public Date getData_volta() {
        return data_volta;
    }

    public void setData_volta(Date data_volta) {
        this.data_volta = data_volta;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public Integer getQtd_pessoas() {
        return qtd_pessoas;
    }

    public void setQtd_pessoas(Integer qtd_pessoas) {
        this.qtd_pessoas = qtd_pessoas;
    }

    public String getDescricao_viagem() {
        return descricao_viagem;
    }

    public void setDescricao_viagem(String descricao_viagem) {
        this.descricao_viagem = descricao_viagem;
    }

    public CadastroUsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(CadastroUsuarioModel usuario) {
        this.usuario = usuario;
    }
}
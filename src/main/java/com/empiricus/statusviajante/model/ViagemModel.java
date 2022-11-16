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

    @Size(min=3, max=100)
    private String nomeViagem;

    @Size(min = 3, max = 100)
    private String origem;

    @Size(min = 3, max = 100)
    private String destino;

    @Size(min = 3, max = 100)
    private String moeda;

    @Temporal(TemporalType.DATE)
    private Date dataIda;

    @Temporal(TemporalType.DATE)
    private Date dataVolta;

    @DecimalMin(value = "0.1")
    private Double orcamento;

    @Min(value=1)
    private Integer qtdPessoas;

    @Size(min=3, max=200)
    private String descricaoViagem;

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

    public String getNomeViagem() {
        return nomeViagem;
    }

    public void setNomeViagem(String nomeViagem) {
        this.nomeViagem = nomeViagem;
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

    public Date getDataIda() {
        return dataIda;
    }

    public void setDataIda(Date dataIda) {
        this.dataIda = dataIda;
    }

    public Date getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(Date dataVolta) {
        this.dataVolta = dataVolta;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public Integer getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(Integer qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public String getDescricaoViagem() {
        return descricaoViagem;
    }

    public void setDescricaoViagem(String descricaoViagem) {
        this.descricaoViagem = descricaoViagem;
    }

    public CadastroUsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(CadastroUsuarioModel usuario) {
        this.usuario = usuario;
    }
}
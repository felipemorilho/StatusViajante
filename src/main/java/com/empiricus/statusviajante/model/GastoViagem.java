package com.empiricus.statusviajante.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "gasto_viagem")
public class GastoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGasto;

    @NotNull
    private Date dataGasto;
    @NotNull
    private double valorGasto;
    private String moeda;
    private String descricaoGasto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private CadastroUsuario cadastroUsuario;

    public Long getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Long idGasto) {
        this.idGasto = idGasto;
    }

    public Date getDataGasto() {
        return dataGasto;
    }

    public void setDataGasto(Date dataGasto) {
        this.dataGasto = dataGasto;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getDescricaoGasto() {
        return descricaoGasto;
    }

    public void setDescricaoGasto(String descricaoGasto) {
        this.descricaoGasto = descricaoGasto;
    }

    public CadastroUsuario getCadastroUsuario() {
        return cadastroUsuario;
    }

    public void setCadastroUsuario(CadastroUsuario cadastroUsuario) {
        this.cadastroUsuario = cadastroUsuario;
    }

}

package com.empiricus.statusviajante.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class CategoriaViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private CategoriaViagem categoriaViagem;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

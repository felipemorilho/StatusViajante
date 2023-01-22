package com.empiricus.statusviajante.model

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "categoria")
class CategoriaGastoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCategoria: Long? = null
    var nome: @NotNull @Size(min = 3, max = 50) String? = null


    @OneToMany(mappedBy = "categoriaViagem")
    private val gastosViagem: Set<GastoViagemModel> = HashSet()
}
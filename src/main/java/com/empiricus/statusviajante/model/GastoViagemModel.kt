package com.empiricus.statusviajante.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "gasto_viagem")
class GastoViagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idGasto: Long? = null
    var dataGasto: @NotNull Date? = null
    var valorGasto: @NotNull Double = 0.0
    var moeda: String? = null
    var descricaoGasto: String? = null

    @ManyToOne
    @JoinColumn(name = "id_viagem")
    var viagem: ViagemModel? = null

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    open var categoriaGastoModel: CategoriaGastoModel? = null
}
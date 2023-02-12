package com.empiricus.statusviajante.model

import com.empiricus.statusviajante.annotation.Positive
import com.empiricus.statusviajante.annotation.Validator
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "gasto_viagem")
class GastoViagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idGasto: Long? = null
    @field:NotNull
    var dataGasto: Date? = null
    @field:NotNull
    @field:Positive()
    var valorGasto: Double = 0.0
    var moeda: String? = null
    var descricaoGasto: String? = null

    

    @ManyToOne
    @JoinColumn(name = "idViagem")
    var viagem: ViagemModel? = null

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    var categoriaViagem: CategoriaGastoModel? = null
}
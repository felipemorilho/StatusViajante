package com.empiricus.statusviajante.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "viagens")
class ViagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idViagem: Long? = null
    var nomeViagem: @NotNull @Size(min = 3, max = 100) String? = null
    var origem: @Size(min = 3, max = 100) String? = null
    var destino: @Size(min = 3, max = 100) String? = null
    var moeda: @Size(min = 3, max = 100) String? = null

    @Temporal(TemporalType.DATE)
    var dataIda: Date? = null

    @Temporal(TemporalType.DATE)
    var dataVolta: Date? = null
    var orcamento: @DecimalMin(value = "0.1") Double? = null
    var qtdPessoas: @Min(value = 1) Int? = null
    var descricaoViagem: @Size(min = 3, max = 200) String? = null

    @OneToMany(mappedBy = "viagem")
    private val gastosViagems: Set<GastoViagemModel> = HashSet()

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    var usuario: CadastroUsuarioModel? = null
}
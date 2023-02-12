package com.empiricus.statusviajante.annotation

import com.empiricus.statusviajante.model.GastoViagemModel

class Validator {

    fun isValid(viagem: GastoViagemModel): Boolean {
        val fields = viagem::class.java.declaredFields

        for (field in fields) {
            field.isAccessible = true
            for (annotation in field.annotations) {
                val valor = field.get(viagem)
                if (field.isAnnotationPresent(Positive::class.java)) {
                    if ((valor as Double) <= 0) {
                        return false
                    }
                }
            }

        }
        return true
    }
}

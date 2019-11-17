package br.edu.ifsp.scl.controlefinanceiro.controller

import android.app.Activity
import br.edu.ifsp.scl.controlefinanceiro.data.ExtratoDAO

class ExtratoController(val activity: Activity) {

    val extratoDAO: ExtratoDAO

    init {
        extratoDAO = ExtratoDAO(activity)
    }

    fun geraExtratoPorConta(idConta: Long){
        extratoDAO.geraExtratoPorConta(idConta)
    }
}
package br.edu.ifsp.scl.controlefinanceiro.controller

import android.app.Activity
import br.edu.ifsp.scl.controlefinanceiro.data.ExtratoDAO
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao

class ExtratoController(val activity: Activity) {

    val extratoDAO: ExtratoDAO

    init {
        extratoDAO = ExtratoDAO(activity)
    }

    fun geraExtratoPorConta(idConta: Long, dataIni: String, dataFim: String): List<Transacao>{
        return extratoDAO.geraExtratoPorConta(idConta, dataIni, dataFim)
    }

    fun geraExtratoPorNatureza(natureza: String): List<Transacao>{
        return extratoDAO.geraExtratoPorNatureza(natureza)
    }

    fun geraExtratoPorTipo(tipo: String): List<Transacao>{
        return extratoDAO.geraExtratoPorTipo(tipo)
    }
}
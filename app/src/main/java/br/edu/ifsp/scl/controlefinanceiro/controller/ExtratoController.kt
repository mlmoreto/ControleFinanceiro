package br.edu.ifsp.scl.controlefinanceiro.controller

import android.app.Activity
import br.edu.ifsp.scl.controlefinanceiro.data.ExtratoDAO
import br.edu.ifsp.scl.controlefinanceiro.dto.TransacaoDto
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao

class ExtratoController(val activity: Activity) {

    val extratoDAO: ExtratoDAO

    init {
        extratoDAO = ExtratoDAO(activity)
    }

    fun geraExtratoPorConta(idConta: Long, dataIni: String): List<TransacaoDto>{
        return extratoDAO.geraExtratoPorConta(idConta, dataIni)
    }

    fun geraExtratoPorNatureza(natureza: String): List<TransacaoDto>{
        return extratoDAO.geraExtratoPorNatureza(natureza)
    }

    fun geraExtratoPorTipo(tipo: String): List<TransacaoDto>{
        return extratoDAO.geraExtratoPorTipo(tipo)
    }
}
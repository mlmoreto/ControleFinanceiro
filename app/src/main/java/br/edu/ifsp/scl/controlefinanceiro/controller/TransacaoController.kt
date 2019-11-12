package br.edu.ifsp.scl.controlefinanceiro.controller

import android.app.Activity
import br.edu.ifsp.scl.controlefinanceiro.data.TransacaoDAO
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao

class TransacaoController(val activity: Activity) {
    val transacaoDAO: TransacaoDAO

    init {
        transacaoDAO = TransacaoDAO(activity)
    }

    fun insereTransacao(transacao: Transacao): Long{
        val id = transacaoDAO.incluirTransacao(transacao)
        return id
    }
}
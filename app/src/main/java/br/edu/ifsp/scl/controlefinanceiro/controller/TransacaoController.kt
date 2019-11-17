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

        // Faz a alteracao do saldo da conta corrente de acordo com a natureza da operacao: soma se for Credito, subtrai se for Debito
        transacaoDAO.atualizaSaldoConta(transacao.valor, transacao.idConta, transacao.natureza)

        return id
    }
}
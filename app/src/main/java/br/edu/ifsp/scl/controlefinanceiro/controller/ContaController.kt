package br.edu.ifsp.scl.controlefinanceiro.controller

import android.app.Activity
import android.database.Cursor
import br.edu.ifsp.scl.controlefinanceiro.data.ContaDAO
import br.edu.ifsp.scl.controlefinanceiro.model.Conta

class ContaController(val activity: Activity) {

    val contaDAO: ContaDAO

    init {
        contaDAO = ContaDAO(activity)
    }

    fun insereConta(conta: Conta){
        contaDAO.incluirConta(conta)
    }

    fun atualiza(conta: Conta) {
        contaDAO.alterarConta(conta)
    }

    fun remove(conta: Conta) {
        contaDAO.excluirConta(conta)
    }

    fun buscaTodas(): List<Conta> {
        return contaDAO.listaContas()
    }

    fun retornarSaldoTotal(): Float {
        return contaDAO.retornarSaldoTotal()
    }
}
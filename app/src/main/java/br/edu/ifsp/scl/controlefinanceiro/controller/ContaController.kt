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
        //buscaTodas()
    }

    fun atualiza(conta: Conta) {
        contaDAO.alterarConta(conta)
        //buscaTodas()
    }

    fun remove(conta: Conta) {
        contaDAO.excluirConta(conta)
        //buscaTodas()
    }

    fun buscaConta(descricao: String){
        //val conta: Conta = contaDAO.listaContas()
        //mainActivity.atualizaLista(mutableListOf(conta))
    }

    fun buscaTodas(): List<Conta> {
        return contaDAO.listaContas()
        //mainActivity.atualizaLista(contaDao.leContas())
    }

    fun retornarSaldoTotal(): Float {
        return contaDAO.retornarSaldoTotal()
    }


}
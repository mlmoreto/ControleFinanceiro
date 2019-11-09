package br.edu.ifsp.scl.controlefinanceiro.controller

import android.app.Activity
import br.edu.ifsp.scl.controlefinanceiro.view.MainActivity
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import br.edu.ifsp.scl.controlefinanceiro.model.ContaDao
import br.edu.ifsp.scl.controlefinanceiro.model.ContaSqlite

class ContaController(val activity: Activity) {

    val contaDao: ContaDao

    init {
        contaDao = ContaSqlite(activity)
    }

    fun insereConta(conta: Conta){
        contaDao.criaConta(conta)
        buscaTodas()
    }

    fun buscaConta(descricao: String){
        val conta: Conta = contaDao.leConta(descricao)
        //mainActivity.atualizaLista(mutableListOf(conta))
    }

    fun buscaTodas() {
        //mainActivity.atualizaLista(contaDao.leContas())
    }

    fun atualiza(conta: Conta) {
        contaDao.alteraContas(conta)
        buscaTodas()
    }

    fun remove(descricao: String) {
        contaDao.deletaConta(descricao)
        buscaTodas()
    }
}
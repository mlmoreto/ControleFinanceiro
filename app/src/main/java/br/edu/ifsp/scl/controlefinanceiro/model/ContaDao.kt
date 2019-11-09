package br.edu.ifsp.scl.controlefinanceiro.model

import br.edu.ifsp.scl.controlefinanceiro.model.Conta

interface ContaDao {
    fun criaConta(conta: Conta)
    fun leConta(descricao: String): Conta
    fun leContas(): MutableList<Conta>
    fun alteraContas(conta: Conta)
    fun deletaConta(descricao: String)
}
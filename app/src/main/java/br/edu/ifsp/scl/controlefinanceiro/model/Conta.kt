package br.edu.ifsp.scl.controlefinanceiro.model

// Valores padrões são Strings vazias
data class Conta(
    var idConta: Int = 0,
    var descricao: String ="",
    var saldo: Float = 0F


) {
    override fun toString(): String {
        return descricao
    }
}
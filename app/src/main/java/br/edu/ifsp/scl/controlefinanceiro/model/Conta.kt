package br.edu.ifsp.scl.controlefinanceiro.model

// Valores padrões são Strings vazias
data class Conta(
    var idConta: Long = 0L,
    var descricao: String ="",
    var saldo: Float = 0F


) {
    override fun toString(): String {
        return descricao
    }
}
package br.edu.ifsp.scl.controlefinanceiro.model

// Valores padrões são Strings vazias
data class Conta(
    val descricao: String ="",
    var saldo: Float = 0F
)
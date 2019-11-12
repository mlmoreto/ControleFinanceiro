package br.edu.ifsp.scl.controlefinanceiro.model

data class Transacao(
    var idTransacao: Int = 0,
    var descricao: String = "",
    var idConta: Int = 0,
    var valor: Float = 0F,
    var natureza: String = "",
    var tipo: String = ""
)
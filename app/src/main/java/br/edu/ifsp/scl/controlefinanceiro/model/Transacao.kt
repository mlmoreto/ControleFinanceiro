package br.edu.ifsp.scl.controlefinanceiro.model

import java.util.*

data class Transacao(
    var idTransacao: Int = 0,
    var descricao: String = "",
    //var data: String = "",
    var idConta: Long = 0L,
    var valor: Float = 0F,
    var natureza: String = "",
    var tipo: String = ""
)
package br.edu.ifsp.scl.controlefinanceiro.model

import java.util.*

data class Transacao(
    val idTransacao: Int,
    val descricao: String,
    val data: String,
    val idConta: Long,
    val valor: Float,
    val natureza: String,
    val tipo: String
)
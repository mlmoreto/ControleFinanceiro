package br.edu.ifsp.scl.controlefinanceiro.dto

/*
* Classe criada para receber os dados retornados do banco e serem exibidos no recycler view dos 3 tipos de extratos
* */

data class TransacaoDto (
  var conta: String = "",
  var descricao: String = "",
  var data: String = "",
  var valor: Float = 0F
)
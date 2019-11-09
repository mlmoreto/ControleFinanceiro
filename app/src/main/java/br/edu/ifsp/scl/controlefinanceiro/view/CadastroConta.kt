package br.edu.ifsp.scl.controlefinanceiro.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import kotlinx.android.synthetic.main.layout_comum.*

class CadastroConta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_conta)

        // Criando Controller e solicitando atualização de lista
        contaController = ContaController(this)

        btnDebitar.setOnClickListener { insereConta() }
    }

    lateinit var contaController: ContaController

    private fun insereConta() {
        val d = Conta("Bradesco", 1000f)
            contaController.insereConta(d)
        }
}
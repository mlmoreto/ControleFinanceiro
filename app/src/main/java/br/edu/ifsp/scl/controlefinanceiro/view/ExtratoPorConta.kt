package br.edu.ifsp.scl.controlefinanceiro.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.controller.ExtratoController
import kotlinx.android.synthetic.main.layout_comum_extrato.*

class ExtratoPorConta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.extrato_por_conta)

        // Criando Controller
        contaController = ContaController(this)
        extratoController = ExtratoController(this)
        var idConta = 0L

        // Busca as contas para preenchimento do spinner
        var listaContas = contaController.buscaTodas()

        // Seta as contas no spinner de Conta
        spinnerExt.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaContas)

        spinnerExt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                idConta = listaContas[p2].idConta
            }
        }

        // Gera o extrato, populando o recycler view
        btnGerarExt.setOnClickListener { geraExtratoPorConta(idConta) }
    }

    lateinit var contaController: ContaController
    lateinit var extratoController: ExtratoController

    private fun geraExtratoPorConta(idConta: Long){
        extratoController.geraExtratoPorConta(idConta)
    }
}
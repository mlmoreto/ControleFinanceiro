package br.edu.ifsp.scl.controlefinanceiro.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.adapter.ExtratoAdapter
import br.edu.ifsp.scl.controlefinanceiro.controller.ExtratoController
import br.edu.ifsp.scl.controlefinanceiro.dto.TransacaoDto
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao
import kotlinx.android.synthetic.main.layout_comum_extrato.*

class ExtratoPorTipo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.extrato_por_tipo)

        // Criando controller
        extratoController = ExtratoController(this)

        // Tipos de transacao:
        var tiposTransacao = getResources().getStringArray(R.array.tiposTransac)
        var tipoTransacao = ""

        // Seta as transacoes no spinner de transacoes
        spinnerExt.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposTransacao)

        spinnerExt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tipoTransacao = tiposTransacao[p2]
            }
        }

        // Gera o extrato, populando o recycler view
        btnGerarExt.setOnClickListener {

            // Inserindo todas as contas na recycler view
            val recyclerView = rvExtrato
            recyclerView.adapter = ExtratoAdapter(geraExtratoPorTipo(tipoTransacao), this)

            // LinearLayout eh o gerenciador:
            val layout = LinearLayoutManager(this)
            recyclerView.setLayoutManager(layout)

        }
    }

    lateinit var extratoController: ExtratoController

    private fun geraExtratoPorTipo(tipoTransacao: String): List<TransacaoDto>{
        return extratoController.geraExtratoPorTipo(tipoTransacao)
    }
}
package br.edu.ifsp.scl.controlefinanceiro.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.adapter.ExtratoAdapter
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.controller.ExtratoController
import br.edu.ifsp.scl.controlefinanceiro.dto.TransacaoDto
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao
import kotlinx.android.synthetic.main.extrato_por_conta.*
import kotlinx.android.synthetic.main.layout_comum_extrato.*
import java.util.*

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
        spinnerExt.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaContas)

        spinnerExt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                idConta = listaContas[p2].idConta
            }
        }

        // Calendar:
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var dataIni = ""
        var dataFim = ""

        // Captura a data ini da consulta
        btnDataIni.setOnClickListener {

            val dpIni = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    view,
                    mYear,
                    mMonth,
                    mDay -> btnDataIni.text = "" + mYear + "-" + (if (mMonth < 9) "0" + (mMonth+1) else (mMonth+1)) + "-" + (if (mDay < 10) "0" + mDay else mDay)}, year, month, day)

            dpIni.show()
        }

        // Captura a data fim
        btnDataFim.setOnClickListener {

            val dpFim = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    view,
                    mYear,
                    mMonth,
                    mDay -> btnDataFim.text = "" + mYear + "-" + (if (mMonth < 9) "0" + (mMonth+1) else (mMonth+1)) + "-" + (if (mDay < 10) "0" + mDay else mDay)}, year, month, day)

            dpFim.show()
        }

        // Gera o extrato, solicitando as datas ini e fim, populando o recycler view
        btnGerarExt.setOnClickListener {

            // Salva as datas:
            dataIni = btnDataIni.text.toString()
            dataFim = btnDataFim.text.toString()

            // Inserindo todas as contas na recycler view
            val recyclerView = rvExtrato
            recyclerView.adapter = ExtratoAdapter(geraExtratoPorConta(idConta, dataIni, dataFim), this)

            // LinearLayout eh o gerenciador:
            val layout = LinearLayoutManager(this)
            recyclerView.setLayoutManager(layout)

        }
    }

    lateinit var contaController: ContaController
    lateinit var extratoController: ExtratoController

    private fun geraExtratoPorConta(idConta: Long, dataIni: String, dataFim: String): List<TransacaoDto>{
        return extratoController.geraExtratoPorConta(idConta, dataIni)
    }
}
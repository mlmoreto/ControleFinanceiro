package br.edu.ifsp.scl.controlefinanceiro.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.controller.TransacaoController
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao
import kotlinx.android.synthetic.main.extrato_por_conta.*
import kotlinx.android.synthetic.main.layout_comum_extrato.*
import kotlinx.android.synthetic.main.transacao.*
import java.text.SimpleDateFormat
import java.util.*

class CadastroTransacaoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transacao)

        // Criando Controller
        contaController = ContaController(this)
        transacaoController = TransacaoController(this)

        // Data:
        // Calendar:
        var c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        c.set(year, month, day)

        var diaSelecionado = 0
        var mesSelecionado = 0
        var anoSelecionado = 0

        // Captura a data ini da consulta
        btnData.setOnClickListener {

            val dpIni = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _,
                                                                                    mYear,
                                                                                    mMonth,
                                                                                    mDay ->
                editTextData.setText("" + mYear + "-" + (if (mMonth < 9) "0" + (mMonth + 1) else (mMonth + 1)) + "-" + (if (mDay < 10) "0" + mDay else mDay))
                diaSelecionado = mDay
                mesSelecionado = mMonth
                anoSelecionado = mYear
            }, year, month, day)

            dpIni.show()
        }


        // Busca as contas para preenchimento do spinner
        var listaContas = contaController.buscaTodas()
        var idConta = 0L

        // Seta as contas no spinner de Conta
        spinnerConta.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaContas)

        spinnerConta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                idConta = listaContas[p2].idConta
            }
        }

        // Tipos de transacao:
        var tiposTransacao = getResources().getStringArray(R.array.tiposTransac)
        var tipoTransacao = ""

        // Seta as transacoes no spinner de transacoes
        spinnerTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposTransacao)

        spinnerTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tipoTransacao = tiposTransacao[p2]
            }
        }

        // Tipos de transacao:
        var periodos = getResources().getStringArray(R.array.periodo)
        var periodo = ""

        // Seta os periodos no spinner de periodos
        spinnerPeriodo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, periodos)

        spinnerPeriodo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                periodo = periodos[p2]
            }
        }

        switchRepetir.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //editTextQtd.setEnabled(true)
                spinnerPeriodo.setVisibility(View.VISIBLE)
                txtQtdVezes.setVisibility(View.VISIBLE)
                editTextQtd.setVisibility(View.VISIBLE)
            } else {
               // editTextQtd.setEnabled(false)
                spinnerPeriodo.setVisibility(View.INVISIBLE)
                txtQtdVezes.setVisibility(View.INVISIBLE)
                editTextQtd.setVisibility(View.INVISIBLE)
            }
        }

        btnCreditar.setOnClickListener { insereTransacao(idConta, periodo, anoSelecionado, mesSelecionado, diaSelecionado, "Crédito", tipoTransacao) }
        btnDebitar.setOnClickListener { insereTransacao(idConta, periodo, anoSelecionado, mesSelecionado, diaSelecionado, "Débito", tipoTransacao) }
    }

    lateinit var contaController: ContaController
    lateinit var transacaoController: TransacaoController

    // Insere a transacao
    private fun insereTransacao(idConta: Long, periodo:String, year: Int, month: Int, day: Int, natureza: String, tipoTransacao: String) {

        val descricao = (findViewById(R.id.editTextDescTrans) as EditText).getText().toString()
        //var data = (findViewById(R.id.editTextData) as EditText).getText().toString()
        var data = ""
        //var dataCalculada: Date
        val quantidade = (findViewById(R.id.editTextQtd) as EditText).getText().toString().toInt()

        val valor = (findViewById(R.id.editTextValor) as EditText).getText().toString()

        var valorTransac: Float

        // Fazer tratativa do valor
        if (natureza == "Débito")
            // Primeiro substitui a virgula pelo ponto para ser armazenado em Float
            valorTransac = valor.replace(',', '.').toFloat() * (-1)
        else
            valorTransac = valor.replace(',', '.').toFloat()

        var id = 0L

        var dia = day
        // Meses são de 0 a 11
        var mes = month + 1
        var ano = year

        // Se selecionou repeticao para varias transacoes
        if (quantidade > 0){

            when(periodo){
                "Diário" ->
                    //Loop de quantidade somando 1 aos dias:
                    for (i in 0..(quantidade-1)){
                        data = ""+ ano + "-" + mes  + "-" + (dia + i)

                        val transacao = Transacao(0, descricao, data, idConta, valorTransac, natureza, tipoTransacao)
                        id = transacaoController.insereTransacao(transacao)
                    }
                "Semanal" ->
                    // Loop de quantidade somando 7 aos dias:
                    for (i in 0..(quantidade-1)){
                        data = ""+ ano + "-" + mes  + "-" + (dia + 7)
                        val transacao = Transacao(0, descricao, data, idConta, valorTransac, natureza, tipoTransacao)
                        id = transacaoController.insereTransacao(transacao)
                    }
                "Mensal" ->
                    // Loop de quantidade somando 1 ao mes:
                    for (i in 0..(quantidade-1)){
                        /*if (mes == 12) {
                            mes = 0;
                        }*/
                        data = "" + ano + "-" + (mes + i) + "-" + dia
                        val transacao = Transacao(0, descricao, data, idConta, valorTransac, natureza, tipoTransacao)
                        id = transacaoController.insereTransacao(transacao)
                    }
                "Anual" ->
                    // Loop de quantidade somando 1 ao ano:
                    for (i in 0..(quantidade-1)){
                        data = ""+ (ano + i)  + "-" + mes + "-" + dia
                        val transacao = Transacao(0, descricao, data, idConta, valorTransac, natureza, tipoTransacao)
                        id = transacaoController.insereTransacao(transacao)
                    }
            }
        }else {
            data = ""+ ano  + "-" + mes + "-" + dia
            val transacao = Transacao(0, descricao, data, idConta, valorTransac, natureza, tipoTransacao)
            id = transacaoController.insereTransacao(transacao)
        }

        if (id != -1L)
            Toast.makeText(getApplicationContext(), "Transacao cadastrada com sucesso!", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(getApplicationContext(), "Problemas ao cadastrar a transacao. Tente novamente!", Toast.LENGTH_LONG).show()

        // Retorna para a Activity principal
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
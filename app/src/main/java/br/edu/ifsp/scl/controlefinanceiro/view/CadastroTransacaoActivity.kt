package br.edu.ifsp.scl.controlefinanceiro.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.controller.TransacaoController
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao
import kotlinx.android.synthetic.main.transacao.*

class CadastroTransacaoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transacao)

        // Criando Controller
        contaController = ContaController(this)
        transacaoController = TransacaoController(this)

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

        btnCreditar.setOnClickListener { insereTransacao(idConta,"Crédito", tipoTransacao) }
        btnDebitar.setOnClickListener { insereTransacao(idConta, "Débito", tipoTransacao) }
    }

    lateinit var contaController: ContaController
    lateinit var transacaoController: TransacaoController

    // Insere a transacao
    private fun insereTransacao(idConta: Long, natureza: String, tipoTransacao: String) {

        val descricao = (findViewById(R.id.editTextDescTrans) as EditText).getText().toString()

        val valor = (findViewById(R.id.editTextValor) as EditText).getText().toString()

        var valorTransac = 0F

        // Fazer tratativa do valor
        if (natureza == "Débito")
            // Primeiro substitui a virgula pelo ponto para ser armazenado em Float
            valorTransac = valor.replace(',', '.').toFloat() * (-1)
        else
            valorTransac = valor.replace(',', '.').toFloat()

        val transacao = Transacao(0, descricao, idConta, valorTransac, natureza, tipoTransacao)

        var id = transacaoController.insereTransacao(transacao)

        if (id != -1L)
            Toast.makeText(getApplicationContext(), "Transacao cadastrada com sucesso!", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(getApplicationContext(), "Problemas ao cadastrar a transacao. Tente novamente!", Toast.LENGTH_LONG).show()

        //setContentView(R.layout.lista_contas)
        //val intent = Intent(this, ListaContaActivity::class.java)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
package br.edu.ifsp.scl.controlefinanceiro.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.controller.TransacaoController
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
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
        var listaTransacoes = contaController.buscaTodas()

        // Seta as contas no spinner de Conta
        spinnerConta.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaTransacoes)

        // Tipos de transacao:
        var tipoTransacao = arrayListOf<String>("Alimentação", "Saúde", "Transporte")

        // Seta as transacoes no spinner de transacoes
        spinnerTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipoTransacao)

        btnCreditar.setOnClickListener { insereTransacao("Credito") }
        btnDebitar.setOnClickListener { insereTransacao("Debito") }

    }

    lateinit var contaController: ContaController
    lateinit var transacaoController: TransacaoController

    // Insere a transacao
    private fun insereTransacao(natureza: String) {

        val descricao = (findViewById(R.id.editTextDescTrans) as EditText).getText().toString()
        // pegar o id da conta
        val valor = (findViewById(R.id.editTextValor) as EditText).getText().toString()
        // pegar o tipo

        // Fazer tratativa do valor

        val transacao = Transacao(0, descricao, 1, valor.toFloat(), natureza, "Alimentacao")
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
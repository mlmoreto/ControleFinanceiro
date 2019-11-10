package br.edu.ifsp.scl.controlefinanceiro.view

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.adapter.ContaAdapter
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import kotlinx.android.synthetic.main.lista_contas.*
import org.w3c.dom.Text

class ListaContaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_contas)

        // Criando Controller
        contaController = ContaController(this)

        // Inserindo todas as contas na recycler view
        val recyclerView = recyclerView
        recyclerView.adapter = ContaAdapter(contas(), this)

        // LinearLayout eh o gerenciador:
        val layout = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layout)

        // Ao clicar no Floating Button, a activity de cadastro de conta é iniciada
        fabAddConta.setOnClickListener { startActivity(Intent(this, CadastroContaActivity::class.java)) }

        // Atualiza o saldo
        var saldoAtualTotal = (findViewById(R.id.txtSaldoTotal) as TextView).getText().toString()
        saldoAtualTotal = calculaSaldoTotal().toString()
    }

    lateinit var contaController: ContaController

    // Faz a busca de todas as contas cadastradas
    private fun contas(): List<Conta>{
        return contaController.buscaTodas()
    }

    private fun calculaSaldoTotal(): Cursor? {
        return contaController.retornarSaldoTotal()
    }

}
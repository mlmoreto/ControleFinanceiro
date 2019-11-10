package br.edu.ifsp.scl.controlefinanceiro.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.controller.ContaController
import br.edu.ifsp.scl.controlefinanceiro.data.ContaDAO
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import kotlinx.android.synthetic.main.layout_comum.*

class CadastroContaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_conta)

        // Criando Controller
        contaController = ContaController(this)

        // Ao clicar no botao Salvar, o metodo de inserir conta eh chamado
        btnSalvarConta.setOnClickListener { insereConta() }
    }

    lateinit var contaController: ContaController

    // Insere a conta, passando como parametro a descricao e saldo informados pelo usuario
    private fun insereConta() {
        val descricao = (findViewById(R.id.editTextDesc) as EditText).getText().toString()
        val saldo = (findViewById(R.id.editTextSaldo) as EditText).getText().toString()

        val conta = Conta(0, descricao, saldo.toFloat())
        contaController.insereConta(conta)

        Toast.makeText(getApplicationContext(), "Conta cadastrada com sucesso!", Toast.LENGTH_LONG).show()

        //setContentView(R.layout.lista_contas)
        val intent = Intent(this, ListaContaActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.btnSalvarConta){
            val dao = ContaDAO(this)

            val descricao = (findViewById(R.id.editTextDesc) as EditText).getText().toString()
            val saldo = (findViewById(R.id.editTextSaldo) as EditText).getText().toString()

            val c = Conta()

            val idConta = dao.incluirConta(c) as Int
            c.idConta = idConta

            //MainActivity.adapter.adicionaContatoAdapter(c)

            Toast.makeText(getApplicationContext(), "Conta cadastrada com sucesso!", Toast.LENGTH_LONG).show()

            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
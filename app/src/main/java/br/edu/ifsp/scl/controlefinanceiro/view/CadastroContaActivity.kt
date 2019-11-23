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

        // Tratativas para não permitir campos em brancos:
        if ((findViewById(R.id.editTextDesc) as EditText).getText().toString() == null || (findViewById(R.id.editTextDesc) as EditText).getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Informe o nome da conta, por favor.", Toast.LENGTH_LONG).show()
        }else {
            if ((findViewById(R.id.editTextSaldo) as EditText).getText().toString() == null || (findViewById(
                    R.id.editTextSaldo
                ) as EditText).getText().toString() == ""
            ) {
                Toast.makeText(
                    getApplicationContext(),
                    "Informe o saldo da conta, por favor.",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                // Captura os dados digitados na tela e insere a conta no banco de dados:
                val descricao = (findViewById(R.id.editTextDesc) as EditText).getText().toString()
                val saldo = (findViewById(R.id.editTextSaldo) as EditText).getText().toString()

                if (saldo.replace(',', '.').toFloat() <= 0){
                    Toast.makeText(
                        getApplicationContext(),
                        "Informe um saldo maior que R$0,00, por favor.",
                        Toast.LENGTH_LONG
                    ).show()
                }else {
                    // Substitui a virgula pelo ponto para ser armazenado como Float
                    val conta = Conta(0, descricao, saldo.replace(',', '.').toFloat())
                    contaController.insereConta(conta)

                    Toast.makeText(
                        getApplicationContext(),
                        "Conta cadastrada com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()

                    //setContentView(R.layout.lista_contas)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.btnSalvarConta){
            val dao = ContaDAO(this)

            val descricao = (findViewById(R.id.editTextDesc) as EditText).getText().toString()
            val saldo = (findViewById(R.id.editTextSaldo) as EditText).getText().toString()

            val c = Conta()

            val idConta = dao.incluirConta(c) as Long
            c.idConta = idConta

            //MainActivity.adapter.adicionaContatoAdapter(c)

            Toast.makeText(getApplicationContext(), "Conta cadastrada com sucesso!", Toast.LENGTH_LONG).show()

            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
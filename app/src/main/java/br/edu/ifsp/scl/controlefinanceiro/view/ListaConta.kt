package br.edu.ifsp.scl.controlefinanceiro.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import kotlinx.android.synthetic.main.lista_contas.*

class ListaConta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_contas)

        fabAddConta.setOnClickListener { startActivity(Intent(this, CadastroConta::class.java)) }
    }


}
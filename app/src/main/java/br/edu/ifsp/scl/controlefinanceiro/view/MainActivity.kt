package br.edu.ifsp.scl.controlefinanceiro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import br.edu.ifsp.scl.controlefinanceiro.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener  {
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCadConta.setOnClickListener {
            startActivity(Intent(this, CadastroContaActivity::class.java))
        }

        btnTransacoes.setOnClickListener {
            startActivity(Intent(this, CadastroTransacaoActivity::class.java))
        }

        btnListaContas.setOnClickListener {
            startActivity(Intent(this, ListaContaActivity::class.java))
        }

        btnExtratos.setOnClickListener {
            startActivity(Intent(this, ExtratoActivity::class.java))
        }
    }


}

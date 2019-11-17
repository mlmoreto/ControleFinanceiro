package br.edu.ifsp.scl.controlefinanceiro.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import kotlinx.android.synthetic.main.extrato.*

class ExtratoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.extrato)

        btnPorConta.setOnClickListener {
            startActivity(Intent(this, ExtratoPorConta::class.java))
        }

        btnPorNatureza.setOnClickListener {
            startActivity(Intent(this, ExtratoPorNatureza::class.java))
        }

        btnPorTipo.setOnClickListener {
            startActivity(Intent(this, ExtratoPorTipo::class.java))
        }
    }
}
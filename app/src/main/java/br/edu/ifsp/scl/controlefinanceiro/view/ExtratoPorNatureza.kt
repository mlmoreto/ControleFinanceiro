package br.edu.ifsp.scl.controlefinanceiro.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.controlefinanceiro.R
import kotlinx.android.synthetic.main.layout_comum_extrato.*

class ExtratoPorNatureza : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.extrato_por_natureza)

        // Tipos de transacao:
        var naturezas = arrayListOf<String>("Débito", "Crédito")
        var natureza = ""

        // Seta as transacoes no spinner de transacoes
        spinnerExt.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, naturezas)

        spinnerExt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                natureza = naturezas[p2]
            }
        }
    }
}
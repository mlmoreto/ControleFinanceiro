package br.edu.ifsp.scl.controlefinanceiro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import kotlinx.android.synthetic.main.conta_item.view.*


class ContaAdapter(private val contas: List<Conta>,
                   private val context: Context) : Adapter<ContaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.conta_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conta = contas[position]
        holder?.let {
            it.descricao.text = conta.descricao
            it.saldo.text = conta.saldo.toString()
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val descricao = itemView.txtDescricao
        val saldo = itemView.txtSaldo

        /*fun bindView(conta: Conta){
            val descricao = itemView.txtDescricao
            val saldo = itemView.txtSaldo

            descricao.text = conta.descricao
            saldo.text = conta.saldo.toString()
        }*/
    }

}

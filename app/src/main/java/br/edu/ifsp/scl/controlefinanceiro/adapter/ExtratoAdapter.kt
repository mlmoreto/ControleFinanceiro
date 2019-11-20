package br.edu.ifsp.scl.controlefinanceiro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.dto.TransacaoDto
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao
import kotlinx.android.synthetic.main.extrato_item.view.*


class ExtratoAdapter(private val transacoes: List<TransacaoDto>,
                     private val context: Context) : Adapter<ExtratoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.extrato_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transacoes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transacao = transacoes[position]
        holder.let {
            it.descricao.text = transacao.descricao
            it.data.text = transacao.data
            it.valor.text = transacao.valor.toString()
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val descricao = itemView.txtDescTransac
        val data = itemView.txtData
        val valor = itemView.txtValorTransac
    }

}

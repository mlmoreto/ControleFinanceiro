package br.edu.ifsp.scl.controlefinanceiro.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.data.SQLiteHelper.Companion.KEY_ID_CONTA
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao

class TransacaoDAO (context: Context){

    internal var database: SQLiteDatabase? = null
    internal var dbHelper: SQLiteHelper? = null
    lateinit var transacao: Transacao

    init {
        this.dbHelper = SQLiteHelper(context)
    }

    fun incluirTransacao(t: Transacao): Long {

        database = dbHelper!!.getWritableDatabase()

        val values = ContentValues()
        values.put(SQLiteHelper.KEY_DESCRICAO, t.descricao)
        values.put(SQLiteHelper.KEY_DATA_TRANSAC, t.data)
        values.put(SQLiteHelper.KEY_ID_CONTA, t.idConta)
        values.put(SQLiteHelper.KEY_VALOR_TRANSAC, t.valor)
        values.put(SQLiteHelper.KEY_NATUREZA, t.natureza)
        values.put(SQLiteHelper.KEY_TIPO_TRANSAC, t.tipo)

        val id = database!!.insert(SQLiteHelper.TABLE_TRANSACAO, null, values)

        database!!.close()
        return id
    }

    fun atualizaSaldoConta(valor: Float, idConta: Long) {
        database = dbHelper!!.getWritableDatabase()

        database!!.execSQL("UPDATE " + SQLiteHelper.TABLE_CONTA + " SET saldo = saldo + " + valor + " WHERE id_conta = " + idConta)
    }

}
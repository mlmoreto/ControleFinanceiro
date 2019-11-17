package br.edu.ifsp.scl.controlefinanceiro.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao

class ExtratoDAO(context: Context) {

    internal var database: SQLiteDatabase? = null
    internal var dbHelper: SQLiteHelper? = null

    val transacao = Transacao()

    init {
        this.dbHelper = SQLiteHelper(context)
    }

    fun geraExtratoPorConta(idConta: Long): List<Transacao> {
        database = dbHelper!!.getReadableDatabase()


        val transacoes = mutableListOf(transacao)

        val cursor: Cursor

        cursor = database!!.query(SQLiteHelper.TABLE_TRANSACAO, null, null,
            null, null, null,
            SQLiteHelper.KEY_DESCRICAO)

        while (cursor.moveToNext()) {
            val t = Transacao()
            t.idConta = cursor.getLong(0)
            t.descricao = cursor.getString(1)
            //t.saldo = cursor.getFloat(2)

            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }
}
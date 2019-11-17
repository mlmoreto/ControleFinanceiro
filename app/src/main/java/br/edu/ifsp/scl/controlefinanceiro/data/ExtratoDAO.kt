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

    fun geraExtratoPorConta(idConta: Long, dataIni: String, dataFim: String): List<Transacao> {
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacao)

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT descricao, valor FROM " + SQLiteHelper.TABLE_TRANSACAO +
                                         " WHERE id_conta = " + idConta +
                                         " AND strftime('%d/%m/%Y', data) >= " + dataIni +
                                         " AND strftime('%d/%m/%Y', data) <= " + dataFim + ";", null)

        while (cursor.moveToNext()) {
            val t = Transacao()
            t.descricao = cursor.getString(0)
            t.valor = cursor.getFloat(1)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }

    fun geraExtratoPorNatureza(natureza: String): List<Transacao>{
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacao)

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT descricao, valor FROM " + SQLiteHelper.TABLE_TRANSACAO + " WHERE natureza = '" + natureza + "';", null)

        while (cursor.moveToNext()) {
            val t = Transacao()
            t.descricao = cursor.getString(0)
            t.valor = cursor.getFloat(1)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }

    fun geraExtratoPorTipo(tipo: String): List<Transacao>{
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacao)

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT descricao, valor FROM " + SQLiteHelper.TABLE_TRANSACAO + " WHERE tipo = '" + tipo + "';", null)

        while (cursor.moveToNext()) {
            val t = Transacao()
            t.descricao = cursor.getString(0)
            t.valor = cursor.getFloat(1)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }
}
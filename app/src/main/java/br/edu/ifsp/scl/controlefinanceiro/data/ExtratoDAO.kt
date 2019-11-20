package br.edu.ifsp.scl.controlefinanceiro.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import br.edu.ifsp.scl.controlefinanceiro.dto.TransacaoDto
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import br.edu.ifsp.scl.controlefinanceiro.model.Transacao

class ExtratoDAO(context: Context) {

    internal var database: SQLiteDatabase? = null
    internal var dbHelper: SQLiteHelper? = null

    val transacao = Transacao()
    val transacaoDto = TransacaoDto()

    init {
        this.dbHelper = SQLiteHelper(context)
    }

    fun geraExtratoPorConta(idConta: Long, dataIni: String): List<TransacaoDto> {
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacaoDto)

        val cursor: Cursor

        var sql = "SELECT descricao, strftime('%d/%m/%Y', data) as data, valor FROM " + SQLiteHelper.TABLE_TRANSACAO +
                        " WHERE id_conta = " + idConta +
                          " AND strftime('%Y/%m/%d', data) >= " + dataIni + " ;"
                          //" AND strftime('%Y/%m/%d', data) <= " + dataFim + " ;"

        cursor = database!!.rawQuery(sql, null)

        while (cursor.moveToNext()) {
            val t = TransacaoDto()
            t.descricao = cursor.getString(0)
            t.data = cursor.getString(1)
            t.valor = cursor.getFloat(2)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }

    fun geraExtratoPorNatureza(natureza: String): List<TransacaoDto>{
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacaoDto)

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT descricao, strftime('%d/%m/%Y', data) as data, valor FROM " + SQLiteHelper.TABLE_TRANSACAO + " WHERE natureza = '" + natureza + "';", null)

        while (cursor.moveToNext()) {
            val t = TransacaoDto()
            t.descricao = cursor.getString(0)
            t.data = cursor.getString(1)
            t.valor = cursor.getFloat(2)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }

    fun geraExtratoPorTipo(tipo: String): List<TransacaoDto>{
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacaoDto)

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT descricao, strftime('%d/%m/%Y', data) as data, valor FROM " + SQLiteHelper.TABLE_TRANSACAO + " WHERE tipo = '" + tipo + "';", null)

        while (cursor.moveToNext()) {
            val t = TransacaoDto()
            t.descricao = cursor.getString(0)
            t.data = cursor.getString(1)
            t.valor = cursor.getFloat(2)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }
}
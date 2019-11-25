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

    lateinit var transacao: Transacao
    val transacaoDto = TransacaoDto()

    init {
        this.dbHelper = SQLiteHelper(context)
    }

    fun geraExtratoPorConta(idConta: Long, data: String): List<TransacaoDto> {
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacaoDto)

        val cursor: Cursor

        var sql = "SELECT descricao, data, valor FROM " + SQLiteHelper.TABLE_TRANSACAO +
                        " WHERE id_conta = " + idConta +
                        " AND data = '" + data + "';"
                          //" AND strftime('%Y/%m/%d', data) >= " + dataIni + " ;"
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

    // Retorna todas as transações pelo filtro natureza, ordenando pela data
    fun geraExtratoPorNatureza(natureza: String): List<TransacaoDto>{
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacaoDto)

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT conta.descricao, " +
                                               " transacao.descricao, " +
                                               //" strftime('%d/%m/%Y', data) as data, " +
                                               " data, " +
                                               " valor " +
                                        " FROM transacao " +
                                        " INNER JOIN conta ON conta.id_conta = transacao.id_conta" +
                                       " WHERE natureza = '" + natureza + "'" +
                                       " ORDER BY data DESC;", null)

        while (cursor.moveToNext()) {
            val t = TransacaoDto()
            t.conta = cursor.getString(0)
            t.descricao = cursor.getString(1)
            t.data = cursor.getString(2)
            t.valor = cursor.getFloat(3)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }

    // Retorna todas as transacoes filtrando pelo tipo, ordenando pela data
    fun geraExtratoPorTipo(tipo: String): List<TransacaoDto>{
        database = dbHelper!!.getReadableDatabase()

        val transacoes = mutableListOf(transacaoDto)

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT conta.descricao, " +
                                                " transacao.descricao, " +
                                                " data, " +
                                                " valor " +
                                           " FROM transacao " +
                                          " INNER JOIN conta ON conta.id_conta = transacao.id_conta" +
                                          " WHERE tipo = '" + tipo + "'" +
                                          " ORDER BY data DESC;", null)

        while (cursor.moveToNext()) {
            val t = TransacaoDto()
            t.conta = cursor.getString(0)
            t.descricao = cursor.getString(1)
            t.data = cursor.getString(2)
            t.valor = cursor.getFloat(3)
            transacoes.add(t)
        }

        cursor.close()
        database!!.close()

        return transacoes
    }
}
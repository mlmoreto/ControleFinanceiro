package br.edu.ifsp.scl.controlefinanceiro.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import br.edu.ifsp.scl.controlefinanceiro.model.Conta


class ContaDAO(context: Context) {

    internal var database: SQLiteDatabase? = null
    internal var dbHelper: SQLiteHelper? = null
    val conta = Conta()

    init {
        this.dbHelper = SQLiteHelper(context)
    }

    fun listaContas(): List<Conta> {
        database = dbHelper!!.getReadableDatabase()


        val contas = mutableListOf(conta)

        val cursor: Cursor

        cursor = database!!.query(SQLiteHelper.TABLE_CONTA, null, null,
            null, null, null,
            SQLiteHelper.KEY_DESCRICAO)

        while (cursor.moveToNext()) {
            val c = Conta()
            c.idConta = cursor.getInt(0)
            c.descricao = cursor.getString(1)
            c.saldo = cursor.getFloat(2)

            contas.add(c)
        }

        cursor.close()
        database!!.close()

        return contas
    }


    fun incluirConta(c: Conta): Long {

        database = dbHelper!!.getWritableDatabase()

        val values = ContentValues()
        values.put(SQLiteHelper.KEY_DESCRICAO, c.descricao)
        values.put(SQLiteHelper.KEY_SALDO, c.saldo)

        val id = database!!.insert(SQLiteHelper.TABLE_CONTA, null, values)

        database!!.close()
        return id

    }

    fun alterarConta(c: Conta) {
        database = dbHelper!!.getWritableDatabase()

        val values = ContentValues()
        values.put(SQLiteHelper.KEY_DESCRICAO, c.descricao)
        values.put(SQLiteHelper.KEY_SALDO, c.saldo)

        database!!.update(SQLiteHelper.TABLE_CONTA, values,
            SQLiteHelper.KEY_ID + "=" + c.idConta, null)

        database!!.close()
    }

    fun excluirConta(c: Conta) {
        database = dbHelper!!.getWritableDatabase()

        database!!.delete(SQLiteHelper.TABLE_CONTA,
            SQLiteHelper.KEY_ID + "=" + c.idConta, null)

        database!!.close()

    }

    fun retornarSaldoTotal(): Cursor? {
        database = dbHelper!!.getReadableDatabase()

        val select = "SELECT SUM(saldo) FROM " + SQLiteHelper.TABLE_CONTA + ";"
        val saldoTotal = database!!.rawQuery(select, emptyArray())

        return saldoTotal
    }

}

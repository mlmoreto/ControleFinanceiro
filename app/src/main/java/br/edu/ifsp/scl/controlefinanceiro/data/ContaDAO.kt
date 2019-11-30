package br.edu.ifsp.scl.controlefinanceiro.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import br.edu.ifsp.scl.controlefinanceiro.model.Conta
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


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

        // Removo o item 0 devido estar inicializando os atributos
        contas.removeAt(0)

        while (cursor.moveToNext()) {
            val c = Conta()
            c.idConta = cursor.getLong(0)
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

    fun atualizaSaldoConta(valor: Float, idConta: Long) {
        database = dbHelper!!.writableDatabase

        // Atualiza o saldo da conta
        database!!.execSQL("UPDATE " + SQLiteHelper.TABLE_CONTA + " SET saldo = saldo + " + valor + " WHERE id_conta = " + idConta)

        // Identifica a data
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Muda a flag para informar que a  ja foi adicionada/subtraida do saldo para que toda vez que as contas forem listadas atualizar somente as transações pendentes
        database!!.execSQL("UPDATE " + SQLiteHelper.TABLE_TRANSACAO +
                                 " SET transac_calculada = 1 " +
                               " WHERE id_conta = " + idConta +
                                 " AND strftime('%Y-%m-%d', data) <= '" + simpleDateFormat.format(Calendar.getInstance().time) + "';")
    }

    fun calculaSaldoAtual(){
        database = dbHelper!!.readableDatabase

        val cursorTransac: Cursor

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        System.out.println(simpleDateFormat.format(Calendar.getInstance().time))

        // Soma todas as transações que ainda não foram atualizadas no valor da conta e que a data da transação seja menor ou igual ao dia de hoje
        // para que não retorne transacoes que ainda serão debitadas/creditadas no saldo da conta
        cursorTransac = database!!.rawQuery("SELECT SUM(valor), id_conta " +
                                                 " FROM " + SQLiteHelper.TABLE_TRANSACAO +
                                                " WHERE strftime('%Y-%m-%d', data) <= '"+ simpleDateFormat.format(Calendar.getInstance().time) +"'" +
                                                  " AND transac_calculada = 0 " +
                                                " GROUP BY id_conta;", null)

        if (cursorTransac.moveToFirst()) {
            atualizaSaldoConta(cursorTransac.getFloat(0), cursorTransac.getLong(1))
        }
    }

    fun retornarSaldoTotal(): Float {
        database = dbHelper!!.readableDatabase

        val cursor: Cursor

        cursor = database!!.rawQuery("SELECT SUM(saldo) AS Total FROM " + SQLiteHelper.TABLE_CONTA + ";", null)

        var saldoTotal = 0F

        if (cursor.moveToFirst()) {
            saldoTotal = cursor.getFloat(0)
        }

        return saldoTotal

    }

}

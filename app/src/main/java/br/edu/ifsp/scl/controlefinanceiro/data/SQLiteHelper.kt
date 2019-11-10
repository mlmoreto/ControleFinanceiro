package br.edu.ifsp.scl.controlefinanceiro.data


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.annotation.Nullable

internal class SQLiteHelper(@Nullable context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    @Override
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    @Override
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {

        private val DATABASE_NAME = "controleFinanceiro.db"

        // Tabela de Conta
        val TABLE_CONTA = "conta"

        val KEY_ID = "id_conta"
        val KEY_DESCRICAO = "descricao"
        val KEY_SALDO = "saldo"

        private val DATABASE_VERSION = 1

        private val CREATE_TABLE = ("CREATE TABLE IF NOT EXISTS " + TABLE_CONTA + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DESCRICAO + " TEXT, "
                + KEY_SALDO + " REAL);")


        // Tabela de Transacao

        // Query de somatoria dos saldos
        val QUERY_SALDO_TOTAL = ("SELECT SUM(saldo) FROM " + TABLE_CONTA + ";")

    }


}

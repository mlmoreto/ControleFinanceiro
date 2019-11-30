package br.edu.ifsp.scl.controlefinanceiro.data


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.annotation.Nullable

internal class SQLiteHelper(@Nullable context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    @Override
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
        db.execSQL(CREATE_TABLE_TRANSAC)
    }

    @Override
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {

        private val DATABASE_NAME = "controleFinanceiro.db"

        /* ***************************************************************
                                 TABELA CONTA
           *************************************************************** */
        val TABLE_CONTA = "conta"

        val KEY_ID = "id_conta"
        val KEY_DESCRICAO = "descricao"
        val KEY_SALDO = "saldo"

        private val DATABASE_VERSION = 1

        private val CREATE_TABLE = ("CREATE TABLE IF NOT EXISTS " + TABLE_CONTA + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DESCRICAO + " TEXT, "
                + KEY_SALDO + " REAL);")


        // Query de somatoria dos saldos
        val QUERY_SALDO_TOTAL = ("SELECT SUM(saldo) FROM " + TABLE_CONTA + ";")



        /* ***************************************************************
                                 TABELA TRANSACAO
           *************************************************************** */
        val TABLE_TRANSACAO = "transacao"

        val KEY_ID_TRANSAC    = "id_transacao"
        val KEY_DESC_TRANSAC  = "descricao"
        val KEY_DATA_TRANSAC  = "data"
        val KEY_ID_CONTA      = "id_conta"
        val KEY_VALOR_TRANSAC = "valor"
        val KEY_NATUREZA      = "natureza" // credito ou debito
        val KEY_TIPO_TRANSAC  = "tipo" // transporte, moradia, alimentacao etc...
        val KEY_CALCULOU_CONTA = "transac_calculada"


        private val CREATE_TABLE_TRANSAC = ("CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACAO + " ("
                + KEY_ID_TRANSAC + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DESC_TRANSAC + " TEXT, "
                + KEY_DATA_TRANSAC + " TEXT, "
                + KEY_ID_CONTA + " INTEGER, "
                + KEY_VALOR_TRANSAC + " REAL, "
                + KEY_NATUREZA + " TEXT, "
                + KEY_TIPO_TRANSAC + " TEXT, "
                + KEY_CALCULOU_CONTA + " INTEGER DEFAULT 0);")
                //" FOREIGN KEY("+ KEY_ID_CONTA +") REFERENCES " + CREATE_TABLE + "("+ KEY_ID +");")
    }


}

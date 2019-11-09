package br.edu.ifsp.scl.controlefinanceiro.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.ifsp.scl.controlefinanceiro.R
import br.edu.ifsp.scl.controlefinanceiro.model.ContaSqlite.Constantes.ATRIBUTO_DESCRICAO
import br.edu.ifsp.scl.controlefinanceiro.model.ContaSqlite.Constantes.ATRIBUTO_SALDO
import br.edu.ifsp.scl.controlefinanceiro.model.ContaSqlite.Constantes.CONTROLE_FINANCEIRO_BD
import br.edu.ifsp.scl.controlefinanceiro.model.ContaSqlite.Constantes.CREATE_TABLE_STM
import br.edu.ifsp.scl.controlefinanceiro.model.ContaSqlite.Constantes.TABELA_CONTA
import java.sql.SQLException

class ContaSqlite(contexto: Context) : ContaDao {

    object Constantes {
        val CONTROLE_FINANCEIRO_BD = "controleFinanceiro"
        val TABELA_CONTA = "conta"
        val ATRIBUTO_DESCRICAO = "descricao"
        val ATRIBUTO_SALDO = "saldo"


        val CREATE_TABLE_STM = "CREATE TABLE IF NOT EXISTS ${TABELA_CONTA}("+
                "${ATRIBUTO_DESCRICAO} TEXT NOT NULL PRIMARY KEY, " +
                "${ATRIBUTO_SALDO} NUMBER NOT NULL);"
    }

    // Referência para o Banco de Dados do aplicativo
    val sqlDb: SQLiteDatabase
    init {
        /* Criando (ou abrindo) e conectando-se ao Banco de Dados a partir do
        Contexto*/
        sqlDb = contexto.openOrCreateDatabase(CONTROLE_FINANCEIRO_BD, MODE_PRIVATE, null)

        // Criando a tabela
        try {
            sqlDb.execSQL(CREATE_TABLE_STM)
        } catch (e: SQLException) {
            Log.e(contexto.getString(R.string.app_name),
                "Erro na criação da tabela!")
        }
    }

    override fun criaConta(conta: Conta) {
        // Mapeamento atributo-valor
        val atributos = ContentValues()
        atributos.put(ATRIBUTO_DESCRICAO, conta.descricao)
        atributos.put(ATRIBUTO_SALDO, conta.saldo)

        // Executando insert
        sqlDb.insert(TABELA_CONTA, null, atributos)
    }

    override fun leConta(descricao: String): Conta {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leContas(): MutableList<Conta> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun alteraContas(conta: Conta) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletaConta(descricao: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
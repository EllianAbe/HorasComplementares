package com.uniso.lpdm.horascomplementares;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "horascomplementares";
    private static final int DB_VERSION = 1;

    // Constantes para o nome da tabela e seus atributos
    public static final String TABELA_ATIVIDADE = "TABELA_ATIVADE";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_NOME = "NOME";
    public static final String COLUNA_TIPO = "TIPO";
    public static final String COLUNA_NUM_HORAS = "NUM_HORAS";
    public static final String COLUNA_STATUS = "STATUS";

    DatabaseHelper(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        atualizarBanco(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        atualizarBanco(db, oldVersion, newVersion);
    }

    public boolean adicionarAtividade(AtividadeComplementar atividadeComplementar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME, atividadeComplementar.getNome());
        cv.put(COLUNA_TIPO, atividadeComplementar.getTipo());
        cv.put(COLUNA_NUM_HORAS, atividadeComplementar.getNumHoras());
        cv.put(COLUNA_STATUS, atividadeComplementar.getStatus());

        long insert = db.insert(TABELA_ATIVIDADE, null, cv);

        return insert == -1;
    }

    public void atualizarTodasAtividades(int novoStatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = String.format("UPDATE %s SET %s = %d WHERE %s = 0",
                TABELA_ATIVIDADE, COLUNA_STATUS, novoStatus, COLUNA_STATUS);

        db.execSQL(sql);

        db.close();
    }

    public List<AtividadeComplementar> selecionarTodos() {
        List<AtividadeComplementar> lista = new ArrayList<>();
        String consulta = "SELECT * FROM " + TABELA_ATIVIDADE;
        SQLiteDatabase db = this.getReadableDatabase();

        // Usando rawQuery pois ele retorna um cursor
        Cursor cursor = db.rawQuery(consulta, null);

        // Se existirem registros na tabela
        if(cursor.moveToFirst()) {
            // Para cada linha na consulta criar um objeto e inseri-lo na lista
            do {
                int atividadeId = cursor.getInt(0);
                String atividadeNome = cursor.getString(1);
                String atividadeTipo = cursor.getString(2);
                int atividadeNumHoras = cursor.getInt(3);
                int atividadeStatus = cursor.getInt(4);

                // Instanciar o objeto
                AtividadeComplementar ac = new AtividadeComplementar(atividadeId, atividadeNome, atividadeTipo, atividadeNumHoras, atividadeStatus);
                lista.add(ac);

            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lista;
    }


    public List<AtividadeComplementar> selecionarPorStatus(int iStatus) {
        List<AtividadeComplementar> lista = new ArrayList<>();
        String consulta = "SELECT * FROM " + TABELA_ATIVIDADE + " WHERE " + COLUNA_STATUS + " = " + iStatus;
        SQLiteDatabase db = this.getReadableDatabase();

        // Usando rawQuery pois ele retorna um cursor
        Cursor cursor = db.rawQuery(consulta, null);

        // Se existirem registros na tabela
        if(cursor.moveToFirst()) {
            // Para cada linha na consulta criar um objeto e inseri-lo na lista
            do {
                int atividadeId = cursor.getInt(0);
                String atividadeNome = cursor.getString(1);
                String atividadeTipo = cursor.getString(2);
                int atividadeNumHoras = cursor.getInt(3);
                int atividadeStatus = cursor.getInt(4);

                // Instanciar o objeto
                AtividadeComplementar ac = new AtividadeComplementar(atividadeId, atividadeNome, atividadeTipo, atividadeNumHoras, atividadeStatus);
                lista.add(ac);

            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lista;
    }

    public List<AtividadeComplementar> selecionarPendentes() {
        return  selecionarPorStatus(0);
    }

    public List<AtividadeComplementar> selecionarAprovadas() {
        return  selecionarPorStatus(10);
    }

    private void atualizarBanco(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql;

        if(oldVersion < 1){
            // CRIANDO TABELA DE ATIVIDADES COMPLEMENTARES
            sql = "CREATE TABLE " + TABELA_ATIVIDADE +
                    " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_NOME + " TEXT, " +
                    COLUNA_TIPO + " TEXT, " +
                    COLUNA_NUM_HORAS + " INTEGER," +
                    COLUNA_STATUS + " INTEGER)";

            db.execSQL(sql);
        }
    }
}

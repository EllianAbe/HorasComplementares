package com.uniso.lpdm.horascomplementares;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "horascomplementares";
    private static final int DB_VERSION = 2;

    DatabaseHelper(Context context){
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

    public static void insertLogin(SQLiteDatabase db, String username, String password,
                                   int coordinator){

        ContentValues login = new ContentValues();
        login.put("username", username);
        login.put("password", password);
        login.put("coordinator", coordinator);
        db.insert("LOGIN", null, login);
    }

    private void atualizarBanco(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql;

        if(oldVersion < 1){
            sql = "CREATE TABLE LOGIN (\n" +
                    "    username TEXT NOT NULL PRIMARY KEY,\n" +
                    "    password TEXT NOT NULL,\n" +
                    "    coordinator BIT,\n" +
                    "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                    ");";

            db.execSQL(sql);

            insertLogin(db, "aluno", "aluno", 0);
            insertLogin(db, "coordenador", "coordenador", 1);
        }
    }
}

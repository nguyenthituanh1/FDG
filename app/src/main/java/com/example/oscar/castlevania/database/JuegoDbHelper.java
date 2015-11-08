package com.example.oscar.castlevania.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Santiago Castro on 24/05/2015.
 */
public class JuegoDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static String DATABASE_NAME="Juego.db";
    public JuegoDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(JuegoDataSource.CREATE_JUEGO_SCRIP);
        db.execSQL(JuegoDataSource.CREATE_USUARIO_SCRIP);
        db.execSQL(JuegoDataSource.INSERT_JUEGO_SCRIP);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

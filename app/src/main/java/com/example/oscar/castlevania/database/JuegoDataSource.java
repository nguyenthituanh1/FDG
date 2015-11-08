package com.example.oscar.castlevania.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;


import com.example.oscar.castlevania.Clases.Usuario;

import java.io.ByteArrayOutputStream;

/**
 * Created by Santiago Castro on 24/05/2015.
 */
public class JuegoDataSource {
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_BLOB = "BLOB";
    public static final String TYPE_INTEGER = "INTEGER";
    private JuegoDbHelper openHelper;
    private SQLiteDatabase database;

    public JuegoDataSource(Context context) {
        openHelper = new JuegoDbHelper(context);
        database = openHelper.getWritableDatabase();

    }

    public class JuegoEntry implements BaseColumns {
        public static final String _TABLE_NAME = "juego";
        public static final String _NOMBRE = "nombre";
        public static final String _ID = "_ID";
    }

    public class UsuarioEntry implements BaseColumns {
        public static final String _TABLE_NAME = "usuario";
        public static final String _NOMBRE = "nombre";
        public static final String _NIVEL1 = "nivel1";
        public static final String _NIVEL2 = "nivel2";
        public static final String _NIVEL3 = "nivel3";
        public static final String _IMAGEN = "imagen";
        public static final String _ESTADO = "estado";
        public static final String _ID = "_ID";
    }

    public static final String CREATE_JUEGO_SCRIP =
            "CREATE TABLE " + JuegoEntry._TABLE_NAME + "(" +
                    JuegoEntry._ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                    JuegoEntry._NOMBRE + " " + TYPE_TEXT + " NOT NULL) ";

    public static final String CREATE_USUARIO_SCRIP =
            "CREATE TABLE " + UsuarioEntry._TABLE_NAME + "(" +
                    UsuarioEntry._ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                    UsuarioEntry._NOMBRE+" "+TYPE_TEXT+" NOT NULL, "+
                    UsuarioEntry._NIVEL1+" "+TYPE_TEXT+" NOT NULL, "+
                    UsuarioEntry._NIVEL2+" "+TYPE_TEXT+" NOT NULL, "+
                    UsuarioEntry._NIVEL3+" "+TYPE_TEXT+" NOT NULL, "+
                    UsuarioEntry._ESTADO+" "+TYPE_TEXT+" NOT NULL, "+
                    UsuarioEntry._IMAGEN + " " + TYPE_BLOB + ") ";

    public static final String INSERT_JUEGO_SCRIP=
            "INSERT INTO "+ JuegoEntry._TABLE_NAME+" VALUES("+
                    "null,"+"\"Castlevania 2D\")";



    public void addEntry( String name, byte[] image,String estado,String nivel1,String nivel2,String nivel3) throws SQLiteException {
        Usuario usuario= getUsuario();
        if (usuario.getNombre().equals("")) {
            ContentValues cv = new ContentValues();
            cv.put(UsuarioEntry._NOMBRE, name);
            cv.put(UsuarioEntry._NIVEL1, nivel1);
            cv.put(UsuarioEntry._NIVEL2, nivel2);
            cv.put(UsuarioEntry._NIVEL3, nivel3);
            cv.put(UsuarioEntry._ESTADO, estado);
            cv.put(UsuarioEntry._IMAGEN, image);
            database.insert(UsuarioEntry._TABLE_NAME, null, cv);
        }
    }
    public Usuario getUsuario()
    {

        Cursor c;
        Usuario usuario= new Usuario();
        try {


         c =database.rawQuery("select * from " + UsuarioEntry._TABLE_NAME, null);

        if (c.moveToFirst()) {

            do
            {
                usuario= new Usuario();
                usuario.setId(c.getInt(0));
                usuario.setNombre(c.getString(1));
                usuario.setNivel1(c.getString(2));
                usuario.setNivel2(c.getString(3));
                usuario.setNivel3(c.getString(4));
                usuario.setEstado(c.getString(5));
                usuario.setBitmap(c.getBlob(6));

            } while(c.moveToNext());
        }
            return usuario;
        }catch (Exception e){
            return usuario;
        }



    }

    public void updateUsuario(Usuario usuario)
    {
        ContentValues cv = new ContentValues();
        cv.put(UsuarioEntry._NOMBRE, usuario.getNombre());
        cv.put(UsuarioEntry._IMAGEN, usuario.getBitmap());
        database.update(UsuarioEntry._TABLE_NAME,cv,"_ID= "+usuario.getId(),null);
    }

    public void updateNivel1(int id)
    {
        ContentValues cv = new ContentValues();
        cv.put(UsuarioEntry._NIVEL1,"Completado");
        database.update(UsuarioEntry._TABLE_NAME,cv,"_ID= "+id,null);
    }


    public void updateNivel2(int id)
    {
        ContentValues cv = new ContentValues();
        cv.put(UsuarioEntry._NIVEL2,"Completado");
        database.update(UsuarioEntry._TABLE_NAME,cv,"_ID= "+id,null);
    }



    public void updateNivel3(int id)
    {
        ContentValues cv = new ContentValues();
        cv.put(UsuarioEntry._NIVEL3,"Completado");
        database.update(UsuarioEntry._TABLE_NAME,cv,"_ID= "+id,null);
    }



    // convert from bitmap to byte array
        public  static  byte[] getBytes(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

        // convert from byte array to bitmap
        public static Bitmap getImage(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }


}
package com.example.oscar.castlevania.Modelo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.widget.ArrayAdapter;

import com.example.oscar.castlevania.R;

import java.util.ArrayList;

/**
 * Created by oscar on 24/05/2015.
 */
public class Fondo {

    int x;
    int y;
    int alto;
    Context context;
    int ancho;
    Bitmap bitmap1;

    ArrayList<Bitmap> bitmap=new ArrayList<Bitmap>();

    public void crearBitmap()
    {

       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo0pantalla1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo1pantalla1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo2pantalla1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo3pantalla1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo4pantalla1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo5pantalla1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo6pantalla1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo7pantalla1), x, y, true));

    }

    public int getAlto()
    {
        return alto;
    }

    public void setAlto(int alto) {

        this.alto = alto;

    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho)
    {
        this.ancho = ancho;
    }


    public Fondo(int x,int y, Context context)
    {
        this.x=x;
        this.y=y;
        this.context=context;
        crearBitmap();
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }


    //@TargetApi(Build.VERSION_CODES.KITKAT)

    public void seleccionar(int posicion)
    {
        bitmap1=bitmap.get(posicion);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap1,0,0,null);
    }
    public void limpiar()
    {
        bitmap.clear();
        bitmap=null;
    }
}

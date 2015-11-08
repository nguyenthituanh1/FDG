package com.example.oscar.castlevania.Modelo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;

import com.example.oscar.castlevania.R;

import java.util.ArrayList;

/**
 * Created by oscar on 24/05/2015.
 */
public class Fondo3 {

    int x;
    int y;
    int alto;
    Context context;
    int ancho;
    Bitmap bitmap1;

    ArrayList<Bitmap> bitmap=new ArrayList<Bitmap>();

    public void crearBitmap()
    {

       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame1), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame2), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame3), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame4), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame5), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame6), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame7), x, y, true));
       bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame8), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame9), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame10), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame11), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame12), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame13), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame14), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame15), x, y, true));
        bitmap.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame16), x, y, true));

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


    public Fondo3(int x, int y, Context context)
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


   // @TargetApi(Build.VERSION_CODES.KITKAT)

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

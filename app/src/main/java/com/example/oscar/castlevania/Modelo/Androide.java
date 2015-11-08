package com.example.oscar.castlevania.Modelo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Androide {

    int x;
    int y;
    int alto;
    int ancho;
    Bitmap bitmap;

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


    public Androide(int x,int y, Bitmap bitmap)
    {
        this.x=x;
        this.y=y;
        this.bitmap=bitmap;
        alto=bitmap.getHeight();
        ancho= bitmap.getWidth();
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap,x - (bitmap.getWidth()/2), y - (bitmap.getHeight()/2),null);
    }
    public void limpiar()
    {
        bitmap.recycle();
        bitmap=null;
    }

}

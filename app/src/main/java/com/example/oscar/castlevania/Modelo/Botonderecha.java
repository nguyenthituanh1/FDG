package com.example.oscar.castlevania.Modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.oscar.castlevania.R;

/**
 * Created by Oscar on 27/05/2015.
 */
public class Botonderecha {

    int x;
    int y;
    int alto;
    int ancho;
    Bitmap bitmap;

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }


    public Botonderecha(int x, int y,Context context)
    {
        this.x = x;
        this.y = y;
        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.botonderecha),100,100, true);
        alto = bitmap.getHeight();
        ancho = bitmap.getWidth();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
    }
    public void limpiar()
    {
        bitmap.recycle();
        bitmap=null;
    }
}
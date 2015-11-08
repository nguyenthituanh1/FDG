package com.example.oscar.castlevania.Modelo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Santiago Castro on 02/06/2015.
 */
public class Fuego {
    int x;
    int y;
    int alto;
    int ancho;
    Bitmap bitmap;
    private boolean estado=false;//si ya choco con alucard

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Fuego(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
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

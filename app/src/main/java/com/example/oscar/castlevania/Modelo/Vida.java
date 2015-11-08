package com.example.oscar.castlevania.Modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.oscar.castlevania.R;

/**
 * Created by Oscar on 28/05/2015.
 */
public class Vida {

    int x;
    int y;
    int alto;
    int ancho;
    Bitmap vida0;
    Bitmap vida1;
    Bitmap vida2;
    Bitmap vida3;

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


    public Vida(int x,int y,Context context)
    {
        this.x=x;
        this.y=y;
        this.vida0=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.vida0), 300, 50, true);
        this.vida1=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.vida1), 300, 50, true);
        this.vida2=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.vida2), 300, 50, true);
        this.vida3=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.vida3), 300, 50, true);

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


    public void draw(Canvas canvas,int vida)
    {
      Bitmap bitmap = null;

        if(vida==0)
        {
           bitmap=vida0;
        }

        if(vida==1)
        {
            bitmap=vida1;
        }

        if(vida==2)
        {
            bitmap = vida2;
        }


        if(vida==3)
        {

            bitmap=vida3;
        }


        alto=bitmap.getHeight();
        ancho= bitmap.getWidth();

        canvas.drawBitmap(bitmap,50, y -150,null);
    }
    public void limpiar()
    {
        vida0.recycle();
        vida1.recycle();
        vida2.recycle();
        vida3.recycle();
        vida0=null;
        vida1=null;
        vida2=null;
        vida3=null;
    }
}

package com.example.oscar.castlevania.Modelo;

import java.util.Random;

/**
 * Created by Santiago Castro on 02/06/2015.
 */
public class NumeroAleatorio {
    private int x;
    private int y;
    private int ancho;
    private int alto;


    public NumeroAleatorio(int x,int y){
        this.ancho=x;
        this.alto=y;
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void generarAleatorio()
    {
        Random rnd = new Random();
        //x=(int)(rnd.nextDouble() * auxx + 100);
        x= (int)(Math.random() * (ancho - 100 + 1) + 25);
       // (int) (Math.random() * (ancho - 100 + 1) + 100);
        //y=(int)(rnd.nextDouble() * (auxy-100) + 100);
        y=50;
    }

    public void generarAleatorio_Panel3()
    {
        Random rnd = new Random();
        //x=(int)(rnd.nextDouble() * auxx + 100);
        x= (int)(Math.random() * (ancho - 270 + 1) + 270);
        // (int) (Math.random() * (ancho - 100 + 1) + 100);
        //y=(int)(rnd.nextDouble() * (auxy-100) + 100);
        y=50;
    }

}

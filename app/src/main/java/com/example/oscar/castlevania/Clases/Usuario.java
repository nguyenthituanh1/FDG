package com.example.oscar.castlevania.Clases;

/**
 * Created by Santiago Castro on 24/05/2015.
 */
public class Usuario {
    private int id;
    private String nombre;
    private String nivel1;
    private String nivel2;
    private String nivel3;
    private String estado;

    private byte[] bitmap;

    public Usuario(int id, String nombre, byte[] bitmap) {
        this.id = id;
        this.nombre = nombre;
        this.bitmap=bitmap;

    }
    public Usuario(){
        this.id = 0;
        this.nombre ="";
        this.nivel1 = "";

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNivel2() {
        return nivel2;
    }

    public void setNivel2(String nivel2) {
        this.nivel2 = nivel2;
    }

    public String getNivel3() {
        return nivel3;
    }

    public void setNivel3(String nivel3) {
        this.nivel3 = nivel3;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel1() {
        return nivel1;
    }

    public void setNivel1(String nivel1) {
        this.nivel1 = nivel1;
    }


}

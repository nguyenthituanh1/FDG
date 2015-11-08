package com.example.oscar.castlevania.Panel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.oscar.castlevania.ActivityNiveles;
import com.example.oscar.castlevania.Hilo.HiloPanelJuego3;
import com.example.oscar.castlevania.Modelo.AlucardDerecha;
import com.example.oscar.castlevania.Modelo.AlucardIzquierda;
import com.example.oscar.castlevania.Modelo.Androide;
import com.example.oscar.castlevania.Modelo.Boladefuego;
import com.example.oscar.castlevania.Modelo.BotonGolpear;
import com.example.oscar.castlevania.Modelo.BotonSaltar;
import com.example.oscar.castlevania.Modelo.Botonderecha;
import com.example.oscar.castlevania.Modelo.Botonizquierda;
import com.example.oscar.castlevania.Modelo.Fondo3;
import com.example.oscar.castlevania.Modelo.Fuego;
import com.example.oscar.castlevania.Modelo.NumeroAleatorio;
import com.example.oscar.castlevania.Modelo.Vida;
import com.example.oscar.castlevania.Modelo.VidaDracula;
import com.example.oscar.castlevania.R;
import com.example.oscar.castlevania.VideoActivity;
import com.example.oscar.castlevania.database.JuegoDataSource;

import java.util.ArrayList;
import java.util.Random;


public class Paneljuego3 extends SurfaceView implements SurfaceHolder.Callback
{


    private int anchopantalla;
    private int altopantalla;

    private HiloPanelJuego3 hilo;
    private int numerodefondo;

    private int numero;//se utiliza para dibujar a dracula;

    private int bandera_brinco=0;

    private AlucardDerecha gifDerecha;
    private Fondo3 fondo;
    private Boladefuego boladefuego;
    private Bitmap bitmap;
    private Botonizquierda botonizquiera;
    private BotonSaltar botonsaltar;
    private Botonderecha botonderecha;
    private AlucardIzquierda alucardIzquierda;
    private boolean bandera_lado=true;
    private Vida vida;
    private VidaDracula vidaDracula;
    private int numero_vida=0;
    private Bitmap gameover;
    private Context context;
    private MediaPlayer mediaPlayer;
    private Androide draculaderecha;
    private Androide draculaizquierda;
    private Androide alucard_golpe_derecha;
    private Androide alucar_golpe_izquiera;
    private boolean   inicio_dracula;
    private BotonGolpear botonGolpear;
    private boolean bandera_golpear;
    private ArrayList<Fuego> listaFuego;
    private NumeroAleatorio numeroAleatorio;
    private int vida_de_dracula;
    private boolean estado_vida;
    private int velocidad_obstaculo_dracula;
    private JuegoDataSource juegoDataSource;

    public void limpiar(){
        this.bitmap.recycle();
        this.alucardIzquierda.limpiar();
        this.boladefuego.limpiar();
        this.botonizquiera.limpiar();
        this.botonderecha.limpiar();
        this.botonsaltar.limpiar();
        this.fondo.limpiar();
        this.gameover.recycle();
        this.gifDerecha.limpiar();
        this.draculaderecha.limpiar();
        this.draculaizquierda.limpiar();
        this.vidaDracula.limpiar();
        this.botonGolpear.limpiar();
        this.listaFuego.clear();
        this.alucar_golpe_izquiera.limpiar();
        this.alucard_golpe_derecha.limpiar();
        this.vida.limpiar();
    }
    public Paneljuego3(Context context, int anchopantalla, int altopantalla)
    {
        super(context);
        this.context=context;
        getHolder().addCallback(this);
        setFocusable(true);
        this.altopantalla=altopantalla;
        this.anchopantalla=anchopantalla;
        bandera_golpear=false;
        estado_vida=false;
        numero=0;
        vida_de_dracula=0;
        velocidad_obstaculo_dracula=10;

        vidaDracula= new VidaDracula(anchopantalla-250,200,context);
        numeroAleatorio=new NumeroAleatorio(anchopantalla-200,altopantalla);
        listaFuego = new ArrayList<Fuego>();
        agregarbolasFuego();

        inicio_dracula=true;
        bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.draculaderecha);
        draculaderecha= new Androide(anchopantalla-100,altopantalla/2+altopantalla/4-75,bitmap);

        bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.draculaizquierda);
        draculaizquierda= new Androide(100,altopantalla/2+altopantalla/4-75,bitmap);

        bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.boladefuego2);
        boladefuego= new Boladefuego(anchopantalla-100,(altopantalla/2+altopantalla/4)-30,bitmap);

        gifDerecha=new AlucardDerecha(context,anchopantalla/2,(altopantalla/2+altopantalla/4)-75);
        alucardIzquierda=new AlucardIzquierda(context,anchopantalla/2,(altopantalla/2+altopantalla/4)-75);

        bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.alucard_golpe_derecha), gifDerecha.getMovieWidth() * 2, gifDerecha.getMovieHeight(), true);
        alucard_golpe_derecha = new Androide(anchopantalla/2,(altopantalla/2+altopantalla/4)-75,bitmap);

        bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.alucard_golpe_izquierda), alucardIzquierda.getMovieWidth() * 2, alucardIzquierda.getMovieHeight(), true);
        alucar_golpe_izquiera= new Androide(anchopantalla/2,(altopantalla/2+altopantalla/4)-75,bitmap);

        bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondocontrol),anchopantalla,altopantalla/4, true);
        fondo=new Fondo3(anchopantalla,altopantalla/2+altopantalla/4,context);
        botonizquiera=new Botonizquierda(200,altopantalla-altopantalla/8,context);
        botonderecha= new Botonderecha(200+botonizquiera.getAncho()+30,altopantalla-altopantalla/8,context);
        botonsaltar=new BotonSaltar(anchopantalla-200,altopantalla-altopantalla/8,context);
        botonGolpear= new BotonGolpear(anchopantalla-75,altopantalla-altopantalla/8,context);
        gameover= BitmapFactory.decodeResource(context.getResources(),R.drawable.gameover);
        numerodefondo=0;
        vida=new Vida(50,200,context);
        juegoDataSource=new JuegoDataSource(context);
        hilo = new HiloPanelJuego3(getHolder(),this);

    }

    public void cargarmusica()
    {
        mediaPlayer= MediaPlayer.create(context,R.raw.cancion_fondo_final);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }


    public void agregarbolasFuego()
    {
        Fuego fuego;
        for(int i=0;i<3;i++)
        {
            numeroAleatorio.generarAleatorio_Panel3();
            fuego= new Fuego(numeroAleatorio.getX(),numeroAleatorio.getY(), BitmapFactory.decodeResource(getResources(),R.drawable.fuego));
            listaFuego.add(fuego);
        }
    }

    public void reiniciar_posicion_obstaculos()
    {

        if (listaFuego.get(0).getY()>=((altopantalla / 2 + altopantalla / 4) - 20))
        {
            for(int i=0;i<3;i++)
            {
                numeroAleatorio.generarAleatorio();
                listaFuego.get(i).setY(numeroAleatorio.getY());
                listaFuego.get(i).setX(numeroAleatorio.getX());
                listaFuego.get(i).setEstado(false);
            }
        }
    }

    public void video_final()
    {
        if(vida_de_dracula==3)
        {
            hilo.setRunning(false);
            mediaPlayer.stop();
            juegoDataSource.updateNivel3(1);
            limpiar();
           ((Activity)(context)).finish();
            Intent intent = new Intent(context, VideoActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        }
    }
    public void dibujar_bolasfuego(Canvas canvas)
    {

        for(int i=0;i<3;i++)
        {
            listaFuego.get(i).setY(listaFuego.get(0).getY() + 20);
            listaFuego.get(i).draw(canvas);
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        hilo.setRunning(true);
        hilo.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry=true;
        while(retry){
            try{
                hilo.join();
                retry=false;
            } catch (InterruptedException e){

            }
        }
    }

    public void dibujar_dracula(Canvas canvas)
    {
        Random rnd=new Random();
        boolean bandera=false;

        if(inicio_dracula==true)
        {
          numero=(int) (rnd.nextDouble() * 2 + 1);
          inicio_dracula=false;
          bandera=true;
        }

        else
        {
               if(numero==1)
               {
                 if(boladefuego.getX()<0)
                 {
                     numero=(int) (rnd.nextDouble() * 2 + 1);
                     bandera=true;
                 }
               }

               if(numero==2)
              {
                 if(boladefuego.getX()>anchopantalla)
                 {
                     numero=(int) (rnd.nextDouble() * 2 + 1);
                     bandera=true;
                 }

              }

         }

        if(numero==1)
        {
            draculaderecha.draw(canvas);
        }
        if(numero==2)
        {
            draculaizquierda.draw(canvas);
        }

        if(bandera==true)
        {
            if(numero==1)
            {
                boladefuego.setX(anchopantalla-100);
                boladefuego.setEstado(false);
                estado_vida=false;
            }

            else
            {
                boladefuego.setX(150);
                boladefuego.setEstado(false);
                estado_vida=false;
            }

        }
    }

    public void aumentar_bola_dracula()
    {
        if(numero==1)
        {boladefuego.setX(boladefuego.getX() - velocidad_obstaculo_dracula);}

        if(numero==2)
        {boladefuego.setX(boladefuego.getX() + velocidad_obstaculo_dracula);}
    }

    public void dibujar_alucard_derecha(Canvas canvas)
    {
        if (bandera_lado == true)
        {
            if (bandera_brinco == 0)
            {
                for(int i=0;i<3;i++)
                {

                    if (listaFuego.get(i).getY() + (listaFuego.get(i).getBitmap().getHeight() / 2) - 10 >= gifDerecha.getPosicionY() - gifDerecha.getMovieHeight() / 2) {
                        if (listaFuego.get(i).getX() + ((listaFuego.get(i).getBitmap().getWidth() / 2)) >= gifDerecha.getPosicionX() - gifDerecha.getMovieWidth() / 2 + 10
                                && listaFuego.get(i).getX() - ((listaFuego.get(i).getBitmap().getWidth() / 2)) <= gifDerecha.getPosicionX() + gifDerecha.getMovieWidth() / 2 - 60) {

                            if (listaFuego.get(i).isEstado() == false)
                            {
                                MediaPlayer impacto=MediaPlayer.create(context,R.raw.bodyimpact);
                                impacto.start();
                                numero_vida++;
                                listaFuego.get(i).setEstado(true);
                            }
                        }
                    }
                }

                if (gifDerecha.getPosicionY() + gifDerecha.getMovieHeight() / 2 >= boladefuego.getY() - boladefuego.getAlto() / 2) {
                    if (gifDerecha.getPosicionX() + (gifDerecha.getMovieWidth() / 2) - 60 >= boladefuego.getX() - boladefuego.getAncho() / 2 && gifDerecha.getPosicionX() - (gifDerecha.getMovieWidth() / 2)+10 <= boladefuego.getX() + boladefuego.getAncho() / 2)
                    {
                        if (boladefuego.isEstado() == false)
                        {
                            MediaPlayer impacto=MediaPlayer.create(context,R.raw.bodyimpact);
                            impacto.start();
                            numero_vida++;
                            boladefuego.setEstado(true);
                        }
                    }

                }

                if(bandera_golpear==true)
                {
                    MediaPlayer espada=MediaPlayer.create(context,R.raw.espada);
                    espada.start();
                    alucard_golpe_derecha.draw(canvas);
                    bandera_golpear=false;
                    verificar_vida_dracula();

                }
                else {

                    gifDerecha.setPosicionY((altopantalla / 2 + altopantalla / 4) - 75);
                    gifDerecha.draw(canvas);
                }

            }

            if (bandera_brinco == 1) {

                if((gifDerecha.getPosicionX() + gifDerecha.getWidth() / 2 + boladefuego.getAncho() + 50)>anchopantalla-200)
                {
                    gifDerecha.setPosicionX(anchopantalla-200);

                }
                else{

                    gifDerecha.setPosicionX(gifDerecha.getPosicionX() + gifDerecha.getWidth() / 2 + boladefuego.getAncho() + 50);
                }

                gifDerecha.setPosicionY(gifDerecha.getPosicionY() - (gifDerecha.getHeight() / 2 + 100));
                alucardIzquierda.setPosicionX(gifDerecha.getPosicionX());
                alucard_golpe_derecha.setX(gifDerecha.getPosicionX());
                gifDerecha.draw(canvas);
                bandera_brinco = 0;

            }
        }


    }


    public void dibujar_alucar_izquierda(Canvas canvas)
    {
        if(bandera_lado==false)
        {
            if (bandera_brinco == 0)
            {
                for(int i=0;i<3;i++)
                {


                    if(listaFuego.get(i).getY()+ (listaFuego.get(i).getBitmap().getHeight()/2)-10>=alucardIzquierda.getPosicionY()- alucardIzquierda.getMovieHeight()/2)
                    {
                        if (listaFuego.get(i).getX() + ((listaFuego.get(i).getBitmap().getWidth()/2)) >= alucardIzquierda.getPosicionX()-alucardIzquierda.getMovieWidth()/2-30
                                && listaFuego.get(i).getX() - ((listaFuego.get(i).getBitmap().getWidth()/2))<= alucardIzquierda.getPosicionX()+alucardIzquierda.getMovieWidth()/2-70){

                            if (listaFuego.get(i).isEstado() == false)
                            {
                                MediaPlayer impacto=MediaPlayer.create(context,R.raw.bodyimpact);
                                impacto.start();
                                 numero_vida++;
                                listaFuego.get(i).setEstado(true);
                            }
                        }
                    }
                }

                if (alucardIzquierda.getPosicionY() + alucardIzquierda.getMovieHeight() / 2>= boladefuego.getY() - boladefuego.getAlto() / 2) {
                    if (alucardIzquierda.getPosicionX()+(alucardIzquierda.getMovieWidth() / 2)-70 >= boladefuego.getX() - boladefuego.getAncho() / 2 &&
                            alucardIzquierda.getPosicionX() - (alucardIzquierda.getMovieWidth() / 2) -30 <= boladefuego.getX() + boladefuego.getAncho() / 2)
                    {

                        if (boladefuego.isEstado() == false)
                        {
                            MediaPlayer impacto=MediaPlayer.create(context,R.raw.bodyimpact);
                            impacto.start();
                            numero_vida++;
                            boladefuego.setEstado(true);
                        }
                    }

                }


                if(bandera_golpear==true)
                {
                    MediaPlayer espada=MediaPlayer.create(context,R.raw.espada);
                    espada.start();

                    alucar_golpe_izquiera.setX(alucardIzquierda.getPosicionX()-75);
                    alucar_golpe_izquiera.draw(canvas);
                    bandera_golpear=false;
                    verificar_vida_dracula();
                }

                else
                {
                    alucardIzquierda.setPosicionY((altopantalla / 2 + altopantalla / 4) - 75);
                    alucardIzquierda.draw(canvas);
                }
            }

            if (bandera_brinco == 1) {

                alucardIzquierda.setPosicionY(alucardIzquierda.getPosicionY() - (alucardIzquierda.getHeight() / 2 + 100));

                if((alucardIzquierda.getPosicionX() - alucardIzquierda.getWidth() / 2 - boladefuego.getAncho()-50)<270)
                {
                    alucardIzquierda.setPosicionX(270);

                }
                else
                {

                    alucardIzquierda.setPosicionX(alucardIzquierda.getPosicionX() - alucardIzquierda.getWidth() / 2 - boladefuego.getAncho() - 50);

                }

                gifDerecha.setPosicionX(alucardIzquierda.getPosicionX());
                alucar_golpe_izquiera.setX(alucardIzquierda.getPosicionX());

                alucardIzquierda.draw(canvas);
                bandera_brinco = 0;
            }
        }
    }


    public void cambiar_el_fondo()
    {
        numerodefondo++;

        if (numerodefondo == 15)
        {
            numerodefondo = 0;
        }

    }

    public void verificar_vida_dracula()
    {

        if(numero==1)
        {
            if(alucard_golpe_derecha.getX()>draculaderecha.getX()-draculaderecha.getAncho())
            {
                if(estado_vida==false)
                {
                    MediaPlayer golpedracula=MediaPlayer.create(context,R.raw.dracula);
                    golpedracula.start();
                    vida_de_dracula++;
                    estado_vida=true;
                    velocidad_obstaculo_dracula=velocidad_obstaculo_dracula+10;
                }
            }
        }

        else{
            if(alucar_golpe_izquiera.getX()<draculaizquierda.getX()+draculaizquierda.getAncho())
            {
                if(estado_vida==false)
                {
                    MediaPlayer golpedracula=MediaPlayer.create(context,R.raw.dracula);
                    golpedracula.start();
                    vida_de_dracula++;
                    estado_vida=true;
                    velocidad_obstaculo_dracula=velocidad_obstaculo_dracula+10;
                }
            }

        }

    }

    public void render(Canvas canvas) throws InterruptedException {

        if(canvas != null)
        {

            if (numero_vida >= 3)
            {
                vida.draw(canvas, 3);
                canvas.drawBitmap(gameover, ((anchopantalla / 2) - (bitmap.getHeight())), altopantalla / 2 - bitmap.getHeight(), null);
                mediaPlayer.stop();

                MediaPlayer gameover=MediaPlayer.create(context,R.raw.gameover);
                gameover.start();
                hilo.setRunning(false);


                gameover.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        limpiar();

                        ((Activity)(context)).finish();
                        Intent intent = new Intent(context, ActivityNiveles.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);

                    }

                });
            }


            else
            {

                fondo.seleccionar(numerodefondo);
                fondo.draw(canvas);

                reiniciar_posicion_obstaculos();
                dibujar_bolasfuego(canvas);
                vida.draw(canvas, numero_vida);
                vidaDracula.draw(canvas,vida_de_dracula);
                dibujar_dracula(canvas);
                canvas.drawBitmap(bitmap, 0, (altopantalla / 2 + altopantalla / 4), null);
                botonizquiera.draw(canvas);
                botonderecha.draw(canvas);
                botonsaltar.draw(canvas);
                botonGolpear.draw(canvas);
                aumentar_bola_dracula();
                boladefuego.draw(canvas);
                dibujar_alucard_derecha(canvas);
                dibujar_alucar_izquierda(canvas);
                cambiar_el_fondo();
                video_final();

            }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {


        if((int)event.getY()>=botonderecha.getY()-botonderecha.getAlto()/2 && (int)event.getY()<=botonderecha.getY()+botonderecha.getAlto()/2)
        {
            if(gifDerecha.getPosicionX()<anchopantalla-200)
            {
                if ((int) event.getX() >= botonderecha.getX()-botonderecha.getAncho()/2 && (int) event.getX() <= botonderecha.getX() + botonderecha.getAncho()/2)
                {
                    gifDerecha.setPosicionX(gifDerecha.getPosicionX() + 10);
                    alucardIzquierda.setPosicionX(gifDerecha.getPosicionX());
                    alucar_golpe_izquiera.setX(gifDerecha.getPosicionX());
                    alucard_golpe_derecha.setX(gifDerecha.getPosicionX());
                    bandera_lado = true;
                }
            }
        }

        if((int)event.getY()>=botonizquiera.getY()-botonizquiera.getAncho()/2 && (int)event.getY()<=botonizquiera.getY()+botonizquiera.getAncho()/2)
        {
            if(alucardIzquierda.getPosicionX()>270)
            {
                if ((int) event.getX() >= botonizquiera.getX() - botonizquiera.getAncho()/2 && (int) event.getX() <= botonizquiera.getX() + botonizquiera.getAncho()/2) {

                    alucardIzquierda.setPosicionX(alucardIzquierda.getPosicionX() - 10);
                    gifDerecha.setPosicionX(alucardIzquierda.getPosicionX());
                    alucar_golpe_izquiera.setX(alucardIzquierda.getPosicionX());
                    alucard_golpe_derecha.setX(alucardIzquierda.getPosicionX());
                    bandera_lado = false;
                }
            }

        }

        if((int)event.getY()>=botonsaltar.getY()-botonsaltar.getAncho()/2 && (int)event.getY()<=botonsaltar.getY()+botonsaltar.getAncho()/2)
        {
            if((int)event.getX()>=botonsaltar.getX()-botonsaltar.getAncho()/2 && (int)event.getX()<=botonsaltar.getX()+botonsaltar.getAncho()/2)
            {
                if(gifDerecha.getPosicionY()==(altopantalla/2+altopantalla/4)-75 && alucardIzquierda.getPosicionY()==(altopantalla/2+altopantalla/4)-75)
                {
                    bandera_brinco = 1;
                }

            }

        }

        if((int)event.getY()>=botonGolpear.getY()-botonGolpear.getAncho()/2 && (int)event.getY()<=botonGolpear.getY()+botonGolpear.getAncho()/2)
        {
            if((int)event.getX()>=botonGolpear.getX()-botonGolpear.getAncho()/2&& (int)event.getX()<=botonGolpear.getX()+botonGolpear.getAncho()/2)
            {
                bandera_golpear=true;
            }

        }

        return true;
    }

}

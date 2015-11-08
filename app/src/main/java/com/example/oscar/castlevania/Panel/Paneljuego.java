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
import com.example.oscar.castlevania.Hilo.Hiloprincipal;
import com.example.oscar.castlevania.Modelo.AlucardIzquierda;
import com.example.oscar.castlevania.Modelo.Boladefuego;
import com.example.oscar.castlevania.Modelo.BotonSaltar;
import com.example.oscar.castlevania.Modelo.Botonderecha;
import com.example.oscar.castlevania.Modelo.Botonizquierda;
import com.example.oscar.castlevania.Modelo.Fondo;
import com.example.oscar.castlevania.Modelo.AlucardDerecha;
import com.example.oscar.castlevania.Modelo.GifIzquierda;
import com.example.oscar.castlevania.Modelo.Vida;
import com.example.oscar.castlevania.R;
import com.example.oscar.castlevania.database.JuegoDataSource;


public class Paneljuego extends SurfaceView implements SurfaceHolder.Callback
{


    private int anchopantalla;
    private int altopantalla;
    private Hiloprincipal hilo;
    private int numerodefondo;

    private GifIzquierda gifIzquierda;
    private int bandera_brinco=0;
    private AlucardDerecha gifDerecha;
    private Fondo fondo;
    private Boladefuego boladefuego;
    private Bitmap bitmap;
     private Botonizquierda botonizquiera;
    private BotonSaltar botonsaltar;
    private Botonderecha botonderecha;
    private AlucardIzquierda alucardIzquierda;
    private boolean bandera_lado=true;
    private Vida vida;
    private int numero_vida=0;
    private Bitmap gameover;
    private Bitmap winner;
    private Context context;
    private MediaPlayer mediaPlayer;
    private int contador_de_obstaculos;
    private int velocidad_obstaculos;
    private boolean bandera_obstaculo;
    private JuegoDataSource juegoDataSource;


    public Hiloprincipal getHilo()
    {
        return hilo;
    }

    public void setHilo(Hiloprincipal hilo)
    {
        this.hilo = hilo;
    }

    public Paneljuego(Context context,int anchopantalla,int altopantalla)
    {
        super(context);
        this.context=context;
        getHolder().addCallback(this);
        setFocusable(true);
        bandera_obstaculo=false;
        contador_de_obstaculos=0;
        velocidad_obstaculos=10;
        this.altopantalla=altopantalla;
        this.anchopantalla=anchopantalla;

        bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.boladefuego);
        gifDerecha=new AlucardDerecha(context,75,(altopantalla/2+altopantalla/4)-75);
        alucardIzquierda=new AlucardIzquierda(context,75,(altopantalla/2+altopantalla/4)-75);
        gifIzquierda=new GifIzquierda(context,anchopantalla,(altopantalla/2+altopantalla/4)-75);
        boladefuego= new Boladefuego(anchopantalla-((int)gifIzquierda.getMovieWidth()+30),(altopantalla/2+altopantalla/4)-20,bitmap);
        bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondocontrol),anchopantalla,altopantalla/4, true);
        fondo=new Fondo(anchopantalla,altopantalla/2+altopantalla/4,context);
        botonizquiera=new Botonizquierda(200,altopantalla-altopantalla/8,context);
        botonderecha= new Botonderecha(200+botonizquiera.getAncho()+30,altopantalla-altopantalla/8,context);
        botonsaltar=new BotonSaltar(anchopantalla-200,altopantalla-altopantalla/8,context);
        gameover= BitmapFactory.decodeResource(context.getResources(),R.drawable.gameover);
        winner=BitmapFactory.decodeResource(context.getResources(),R.drawable.winner);
        numerodefondo=0;
        juegoDataSource=new JuegoDataSource(context);
        vida=new Vida(50,200,context);
        hilo = new Hiloprincipal(getHolder(),this);

    }
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
        this.gifIzquierda.limpiar();
        this.vida.limpiar();
        this.winner.recycle();


    }
    public void cargarmusica()
    {
        mediaPlayer= MediaPlayer.create(context,R.raw.sonidodefondo);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void pausarmusica()
    {
        mediaPlayer.stop();
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
            } catch (Exception e)
            {

            }
        }
    }

    public void dibujar_alucard_derecha(Canvas canvas)
    {
        if (bandera_lado == true)
        {

            if (bandera_brinco == 0)
            {

                if (gifDerecha.getPosicionY() + gifDerecha.getMovieHeight() / 2>= boladefuego.getY() - boladefuego.getAlto() / 2) {
                    if (gifDerecha.getPosicionX() + (gifDerecha.getMovieWidth() / 2) - 60 >= boladefuego.getX() - boladefuego.getAncho() / 2 && gifDerecha.getPosicionX() - (gifDerecha.getMovieWidth() / 2) <= boladefuego.getX() + boladefuego.getAncho() / 2) {

                        if (boladefuego.isEstado() == false) {
                            MediaPlayer impacto=MediaPlayer.create(context,R.raw.bodyimpact);
                            impacto.start();
                            numero_vida++;
                            boladefuego.setEstado(true);
                        }
                    }

                }

                gifDerecha.setPosicionY((altopantalla / 2 + altopantalla / 4) - 75);
                gifDerecha.draw(canvas);

            }

            if (bandera_brinco == 1) {

                if (gifDerecha.getPosicionX() + gifDerecha.getWidth() / 2 + 100>anchopantalla-200) {

                    gifDerecha.setPosicionX(anchopantalla-200);
                }
                else{
                    gifDerecha.setPosicionX(gifDerecha.getPosicionX() + gifDerecha.getWidth() / 2 + 100);
                }
                gifDerecha.setPosicionY(gifDerecha.getPosicionY() - (gifDerecha.getHeight() / 2 + 100));
                alucardIzquierda.setPosicionX(gifDerecha.getPosicionX());
                gifDerecha.draw(canvas);
                bandera_brinco = 0;

            }
        }

    }

    public void dibujar_alucard_izquierda(Canvas canvas)
    {

        if(bandera_lado==false)
        {

            if (bandera_brinco == 0)
            {

                if (alucardIzquierda.getPosicionY() + alucardIzquierda.getMovieHeight() / 2 >= boladefuego.getY() - boladefuego.getAlto() / 2) {
                    if (alucardIzquierda.getPosicionX() >= boladefuego.getX() - boladefuego.getAncho() / 2 &&
                            alucardIzquierda.getPosicionX() - (alucardIzquierda.getMovieWidth() / 2) - 50 <= boladefuego.getX() + boladefuego.getAncho() / 2) {
                        if (boladefuego.isEstado() == false)
                        {
                            MediaPlayer impacto=MediaPlayer.create(context,R.raw.bodyimpact);
                            impacto.start();
                            numero_vida++;
                            boladefuego.setEstado(true);
                        }
                    }

                }

                alucardIzquierda.setPosicionY((altopantalla / 2 + altopantalla / 4) - 75);
                alucardIzquierda.draw(canvas);
            }

            if (bandera_brinco == 1) {

                if (alucardIzquierda.getPosicionX() - alucardIzquierda.getWidth() / 2 - 50<75) {

                    alucardIzquierda.setPosicionX(75);
                }
                else{

                    alucardIzquierda.setPosicionX(alucardIzquierda.getPosicionX() - alucardIzquierda.getWidth() / 2 - 50);
                }
                alucardIzquierda.setPosicionY(alucardIzquierda.getPosicionY() - (alucardIzquierda.getHeight() / 2 + 100));
                gifDerecha.setPosicionX(alucardIzquierda.getPosicionX());
                alucardIzquierda.draw(canvas);
                bandera_brinco = 0;

            }

        }

    }

    public void validar_obstaculo(Canvas canvas)
    {
        boladefuego.setX(boladefuego.getX() - velocidad_obstaculos);

         if (boladefuego.getX() < 0)
        {
            boladefuego.setEstado(false);
            boladefuego.setX(anchopantalla - ((int) gifIzquierda.getMovieWidth() + 30));
            contador_de_obstaculos++;
            bandera_obstaculo=false;
        }


        if(contador_de_obstaculos%5==0 && contador_de_obstaculos>=5 && bandera_obstaculo==false)
        {
            velocidad_obstaculos=velocidad_obstaculos+10;
            bandera_obstaculo=true;
        }

        if(contador_de_obstaculos==15)
        {
            mediaPlayer.stop();
            canvas.drawBitmap(winner, ((anchopantalla / 2)-winner.getWidth()/2), altopantalla / 2 - bitmap.getHeight(), null);

            hilo.setRunning(false);
            MediaPlayer win= MediaPlayer.create(context,R.raw.win);
            win.start();
            juegoDataSource.updateNivel1(1);
            win.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {

                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    limpiar();
                    ((Activity)(context)).finish();
                    Intent intent=new Intent(context, ActivityNiveles.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);

                }
            });



        }

    }


    public void cambiar_fondo()
    {
        numerodefondo++;

        if (numerodefondo == 7)
        {
            numerodefondo = 0;
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


                gameover.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer mp)
                    {
                        limpiar();
                        ((Activity)(context)).finish();
                        Intent intent = new Intent(context, ActivityNiveles.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });
             }


           else{

                  fondo.seleccionar(numerodefondo);
                  fondo.draw(canvas);
                  vida.draw(canvas, numero_vida);
                  canvas.drawBitmap(bitmap, 0, (altopantalla / 2 + altopantalla / 4), null);
                  botonizquiera.draw(canvas);
                  botonderecha.draw(canvas);
                  botonsaltar.draw(canvas);
                  boladefuego.draw(canvas);
                  gifIzquierda.draw(canvas);
                  dibujar_alucard_derecha(canvas);
                  dibujar_alucard_izquierda(canvas);
                  validar_obstaculo(canvas);
                  cambiar_fondo();

              }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {

        if((int)event.getY()>=botonderecha.getY()-botonderecha.getAlto()/2 && (int)event.getY()<=botonderecha.getY()+botonderecha.getAlto()/2)
        {
            if(gifDerecha.getPosicionX()<anchopantalla-200)
            {
                if ((int) event.getX() >= botonderecha.getX() - botonderecha.getAncho()/2 && (int) event.getX() <= botonderecha.getX() + botonderecha.getAncho()/2) {
                    gifDerecha.setPosicionX(gifDerecha.getPosicionX() + 10);
                    alucardIzquierda.setPosicionX(gifDerecha.getPosicionX());
                    bandera_lado = true;
                }
            }
        }

        if((int)event.getY()>=botonizquiera.getY()-botonizquiera.getAncho()/2 && (int)event.getY()<=botonizquiera.getY()+botonizquiera.getAncho()/2)
        {
            if(alucardIzquierda.getPosicionX()>75)
            {
                if ((int) event.getX() >= botonizquiera.getX() - botonizquiera.getAncho()/2 && (int) event.getX() <= botonizquiera.getX() + botonizquiera.getAncho()/2) {

                    alucardIzquierda.setPosicionX(alucardIzquierda.getPosicionX() - 10);
                    gifDerecha.setPosicionX(alucardIzquierda.getPosicionX());
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

        return true;
    }

}

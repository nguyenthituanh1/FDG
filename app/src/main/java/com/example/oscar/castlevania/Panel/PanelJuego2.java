package com.example.oscar.castlevania.Panel;

/**
 * Created by Santiago Castro on 02/06/2015.
 */
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
import com.example.oscar.castlevania.Hilo.HiloPanelJuego2;
import com.example.oscar.castlevania.Modelo.AlucardIzquierda;
import com.example.oscar.castlevania.Modelo.Boladefuego;
import com.example.oscar.castlevania.Modelo.BotonSaltar;
import com.example.oscar.castlevania.Modelo.Botonderecha;
import com.example.oscar.castlevania.Modelo.Botonizquierda;
import com.example.oscar.castlevania.Modelo.Fondo;
import com.example.oscar.castlevania.Modelo.AlucardDerecha;
import com.example.oscar.castlevania.Modelo.FondoNive2;
import com.example.oscar.castlevania.Modelo.Fuego;
import com.example.oscar.castlevania.Modelo.GifIzquierda;
import com.example.oscar.castlevania.Modelo.NumeroAleatorio;
import com.example.oscar.castlevania.Modelo.Vida;
import com.example.oscar.castlevania.R;
import com.example.oscar.castlevania.database.JuegoDataSource;

import java.util.ArrayList;

public class PanelJuego2 extends SurfaceView implements SurfaceHolder.Callback {
    private int anchopantalla;
    private int altopantalla;
    private HiloPanelJuego2 hilo;
    private int numerodefondo;
    private GifIzquierda gifIzquierda;
    private int bandera_brinco=0;
    private AlucardDerecha gifDerecha;
    private FondoNive2 fondo;
    private Bitmap bitmap;
    private Botonizquierda botonizquiera;
    private BotonSaltar botonsaltar;
    private Botonderecha botonderecha;
    private AlucardIzquierda alucardIzquierda;
    private boolean bandera_lado=true;
    private Vida vida;
    private int numero_vida=0;
    private Bitmap gameover;
    private NumeroAleatorio numeroAleatorio;
    private Fuego fuego;
    private ArrayList<Fuego> listaFuego = new ArrayList<Fuego>();
    private Context context;
    private MediaPlayer mediaPlayer;
    private int velocidad_obstaculos;
    private int contador_obstaculos;
    private boolean bandera_obstaculo;
    private Bitmap winner;
    private JuegoDataSource juegoDataSource;




    public PanelJuego2(Context context, int anchopantalla, int altopantalla)
    {
        super(context);
        this.context=context;
        velocidad_obstaculos=10;
        bandera_obstaculo=false;
        contador_obstaculos=0;
        getHolder().addCallback(this);
        setFocusable(true);
        this.altopantalla=altopantalla;
        this.anchopantalla=anchopantalla;
        numeroAleatorio= new NumeroAleatorio(anchopantalla,altopantalla);
        AgregarFuego();
        gifDerecha=new AlucardDerecha(context,75,(altopantalla/2+altopantalla/4)-75);
        winner=BitmapFactory.decodeResource(context.getResources(),R.drawable.winner);
        alucardIzquierda=new AlucardIzquierda(context,75,(altopantalla/2+altopantalla/4)-75);
        gifIzquierda=new GifIzquierda(context,anchopantalla,(altopantalla/2+altopantalla/4)-75);
        bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondocontrol),anchopantalla,altopantalla/4, true);
        fondo=new FondoNive2(anchopantalla,altopantalla/2+altopantalla/4,context);
        botonizquiera=new Botonizquierda(200,altopantalla-altopantalla/8,context);
        botonderecha= new Botonderecha(200+botonizquiera.getAncho()+30,altopantalla-altopantalla/8,context);
        botonsaltar=new BotonSaltar(anchopantalla-200,altopantalla-altopantalla/8,context);
        gameover= BitmapFactory.decodeResource(context.getResources(),R.drawable.gameover);
        numerodefondo=0;
        vida=new Vida(50,200,context);
        juegoDataSource=new JuegoDataSource(context);
        hilo = new HiloPanelJuego2(getHolder(),this);

    }
    public void limpiar(){
        this.bitmap.recycle();
        this.alucardIzquierda.limpiar();
        this.fuego.limpiar();
        this.botonizquiera.limpiar();
        this.botonderecha.limpiar();
        this.botonsaltar.limpiar();
        this.fondo.limpiar();
        this.gameover.recycle();
        this.gifDerecha.limpiar();
        this.gifIzquierda.limpiar();
        this.vida.limpiar();
        this.winner.recycle();
        this.listaFuego.clear();
    }

    public void cargarmusica()
    {
        mediaPlayer= MediaPlayer.create(context,R.raw.sonidodefondo);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        hilo.setRunning(true);
        try {
            hilo.start();
        }
        catch (Exception e)
        {


        }
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



    public void render(Canvas canvas) throws InterruptedException {

        if(canvas != null) {
            if (numero_vida >= 3)
            {
                vida.draw(canvas, 3);
                canvas.drawBitmap(gameover, ((anchopantalla / 2) - (bitmap.getHeight())), altopantalla / 2 - bitmap.getHeight(), null);
                mediaPlayer.stop();
                MediaPlayer gameover = MediaPlayer.create(context, R.raw.gameover);
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
            else
            {

                fondo.seleccionar(numerodefondo);
                fondo.draw(canvas);
                vida.draw(canvas, numero_vida);
                canvas.drawBitmap(bitmap, 0, (altopantalla / 2 + altopantalla / 4), null);
                botonizquiera.draw(canvas);
                botonderecha.draw(canvas);
                botonsaltar.draw(canvas);
                gifIzquierda.draw(canvas);
                RevisarGifDerecha(canvas);
                RevisarGifIzquierda(canvas);
                validar_obstaculo();
                velocidad_obstaculos();
                dibujar_obstaculos(canvas);
                cambiarfondo();
                jugador_ganador(canvas);

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
                    alucardIzquierda.setPosicionX(gifDerecha.getPosicionX() + 10);
                    gifDerecha.setPosicionX(gifDerecha.getPosicionX() + 10);
                    bandera_lado = true;
                }
            }
        }

        if((int)event.getY()>=botonizquiera.getY()-botonizquiera.getAncho()/2 && (int)event.getY()<=botonizquiera.getY()+botonizquiera.getAncho()/2)
        {
            if(alucardIzquierda.getPosicionX()>75)
            {
                if ((int) event.getX() >= botonizquiera.getX() - botonizquiera.getAncho()/2 && (int) event.getX() <= botonizquiera.getX() + botonizquiera.getAncho()/2) {

                    gifDerecha.setPosicionX(gifDerecha.getPosicionX()-10);
                    alucardIzquierda.setPosicionX(alucardIzquierda.getPosicionX() - 10);
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

    public void cambiarfondo()
    {
        numerodefondo++;

        if (numerodefondo == 7)
        {
            numerodefondo = 0;
        }

    }

    public void validar_obstaculo()
    {

        if (listaFuego.get(2).getY()>=((altopantalla / 2 + altopantalla / 4) - 20))
        {
            numeroAleatorio.generarAleatorio();
            for(int i=0;i<5;i++)
            {
                numeroAleatorio.generarAleatorio();
                listaFuego.get(i).setY(numeroAleatorio.getY());
                listaFuego.get(i).setX(numeroAleatorio.getX());
                listaFuego.get(i).setEstado(false);
            }
            bandera_obstaculo=false;
            contador_obstaculos++;
        }

    }

    public void jugador_ganador(Canvas canvas)
    {

        if(contador_obstaculos==20)
        {
            juegoDataSource.updateNivel2(1);
            mediaPlayer.stop();
            canvas.drawBitmap(winner, ((anchopantalla / 2)-winner.getWidth()/2), altopantalla / 2 - bitmap.getHeight(), null);
            hilo.setRunning(false);
            MediaPlayer win= MediaPlayer.create(context,R.raw.win);
            win.start();
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

    public void velocidad_obstaculos()
    {
        if(bandera_obstaculo==false && contador_obstaculos>=5 && contador_obstaculos%5==0)
        {
            velocidad_obstaculos=velocidad_obstaculos+5;
            bandera_obstaculo=true;
        }

        for(int i=0;i<5;i++)
        {
            listaFuego.get(i).setY(listaFuego.get(i).getY() + velocidad_obstaculos);
        }
    }

    public void dibujar_obstaculos(Canvas canvas)
    {
        for (int i = 0; i < 5; i++)
        {
            listaFuego.get(i).draw(canvas);
        }
    }



    public void AgregarFuego()
    {
        for(int i=0;i<5;i++){
            numeroAleatorio.generarAleatorio();
            fuego= new Fuego(numeroAleatorio.getX(),numeroAleatorio.getY(), BitmapFactory.decodeResource(getResources(),R.drawable.fuego));
            listaFuego.add(fuego);
        }

    }

    public void RevisarGifDerecha(Canvas canvas){
        if (bandera_lado == true) {

            if (bandera_brinco == 0) {

                for(int i=0;i<5;i++) {

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
                gifDerecha.setPosicionY((altopantalla / 2 + altopantalla / 4) - 75);
                gifDerecha.draw(canvas);

            }

            if (bandera_brinco == 1)
            {

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
    public void RevisarGifIzquierda(Canvas canvas) {

        if (bandera_lado == false) {
            if (bandera_brinco == 0) {

                for(int i=0;i<5;i++){


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

}

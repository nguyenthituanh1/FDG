package com.example.oscar.castlevania.Hilo;

/**
 * Created by Santiago Castro on 02/06/2015.
 */
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.oscar.castlevania.Panel.Paneljuego;
import com.example.oscar.castlevania.Panel.PanelJuego2;

/**
 * Created by oscar on 23/05/2015.
 */
public class HiloPanelJuego2 extends Thread{

    private SurfaceHolder surfaceHolder;
    private PanelJuego2 paneljuego;

    private boolean running;

    public HiloPanelJuego2(SurfaceHolder surfaceHolder, PanelJuego2 paneljuego){
        super();
        this.surfaceHolder=surfaceHolder;
        this.paneljuego=paneljuego;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void pauseThread() throws InterruptedException
    {
        running = false;
    }

    public void resumeThread()
    {
        running = true;
    }
    public void run(){

        long  startTime;
        long sleepTime;
        Canvas canvas;

        while(running){
            canvas=null;

            startTime = System.currentTimeMillis();
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.paneljuego.render(canvas);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            sleepTime = (1000/10)-(System.currentTimeMillis() - startTime);

            try {

                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            }
            catch (Exception e) {}

        }

    }


}

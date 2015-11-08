package com.example.oscar.castlevania;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.oscar.castlevania.Hilo.Hiloprincipal;
import com.example.oscar.castlevania.Panel.PanelJuego2;
import com.example.oscar.castlevania.Panel.Paneljuego;

import com.example.oscar.castlevania.database.JuegoDataSource;


public class Juego extends Activity {
    private Display display;
    private Point size;
    private MediaPlayer mediaPlayer;
    private Paneljuego paneljuego;


    private int altopantalla;
    private int anchopantalla;
    private JuegoDataSource juegoDataSource;

    Hiloprincipal hilo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_juego);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        obtener_Pantalla();
        juegoDataSource= new JuegoDataSource(this);
        try {
            paneljuego = new Paneljuego(this, anchopantalla, altopantalla);
            setContentView(paneljuego);
        }catch (Exception e){}
    }

    public void obtener_Pantalla()
    {
        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        anchopantalla = size.x;
        altopantalla= size.y;
    }
    @Override
    public void onDestroy() {
        paneljuego=null;
        super.onDestroy();
    }
    @Override
     protected void onResume()
    {
        super.onResume();
        System.out.println("resume");
        paneljuego.cargarmusica();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juego, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

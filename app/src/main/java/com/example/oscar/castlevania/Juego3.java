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

import com.example.oscar.castlevania.Panel.PanelJuego2;
import com.example.oscar.castlevania.Panel.Paneljuego3;
import com.example.oscar.castlevania.database.JuegoDataSource;


public class Juego3 extends Activity {

    private Display display;
    private Point size;
    private MediaPlayer mediaPlayer;
    private Paneljuego3 panelJuego;

    private int altopantalla;
    private int anchopantalla;
    private JuegoDataSource juegoDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        obtener_Pantalla();
        juegoDataSource= new JuegoDataSource(this);
        try {
            panelJuego = new Paneljuego3(this, anchopantalla, altopantalla);
            setContentView(panelJuego);
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
        panelJuego=null;
        super.onDestroy();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        panelJuego.cargarmusica();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juego3, menu);
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

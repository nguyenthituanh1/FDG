package com.example.oscar.castlevania;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oscar.castlevania.Clases.Usuario;
import com.example.oscar.castlevania.database.JuegoDataSource;

import java.io.ByteArrayOutputStream;


public class Estadisticas extends Activity {

   JuegoDataSource juegoDataSource;
    Usuario usuario= new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_estadisticas);
        juegoDataSource = new JuegoDataSource(this);
        usuario=juegoDataSource.getUsuario();

        if(usuario.getNombre().equals(""))
        {
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.usuario);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, out);
            byte[] buffer=out.toByteArray();
            juegoDataSource.addEntry("Usuario",buffer,"1","Imcompleto","Incompleto","Incompleto");
            usuario=juegoDataSource.getUsuario();
        }
        llenarInfo();
    }

    public void llenarInfo(){
        TextView textView= (TextView) findViewById(R.id.textView2);
        textView.setText(usuario.getNombre());

        ImageView imageView= (ImageView) findViewById(R.id.viewImage1);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(usuario.getBitmap(), 0, usuario.getBitmap().length));

        TextView textView1= (TextView) findViewById(R.id.textView3);
        textView1.setText("Nivel 1: "+usuario.getNivel1());

        TextView textView2= (TextView) findViewById(R.id.textView4);
        textView2.setText("Nivel 2: "+usuario.getNivel2());

        TextView textView3= (TextView) findViewById(R.id.textView5);
        textView3.setText("Nivel 3: "+usuario.getNivel3());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estadisticas, menu);
        return true;
    }

    @Override
    protected void onStop()
    {
        super.onStop();

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

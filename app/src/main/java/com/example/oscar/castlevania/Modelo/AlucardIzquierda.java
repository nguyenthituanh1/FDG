package com.example.oscar.castlevania.Modelo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;

import com.example.oscar.castlevania.R;

import java.io.InputStream;

/**
 * Created by oscar on 23/05/2015.
 */
public class AlucardIzquierda extends View
{

    private InputStream gifInputStream;
    private Movie gifMovie;
    private int movieWidth, movieHeight;
    private long movieDuration;
    private long mMovieStart;
    private int x;
    private int y;



    public void setPosicionY(int y) {
        this.y = y;
    }
    public int getPosicionY(){return y;}

    public void setPosicionX(int x) {
        this.x = x;
    }
    public int getPosicionX() {
        return this.x;
    }



    public AlucardIzquierda(Context context, int x, int y) {
        super(context);
        this.x=x;
        this.y=y;
        init(context);

    }


    private void init(Context context)
    {
        setFocusable(true);
        gifInputStream = context.getResources().openRawResource(R.drawable.alucardizquierda);
        gifMovie = Movie.decodeStream(gifInputStream);
        movieDuration = gifMovie.duration();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        setMeasuredDimension(movieWidth, movieHeight);
    }

    public int getMovieWidth()
    {
        return gifMovie.width();
    }

    public int getMovieHeight()
    {
        return gifMovie.height();
    }

    public long getMovieDuration()
    {
        return movieDuration;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        long now = android.os.SystemClock.uptimeMillis();
        if (mMovieStart == 0) {   // first time
            mMovieStart = now;
        }

        if (gifMovie != null) {

            int dur = gifMovie.duration();
            if (dur == 0) {
                dur = 1000;
            }

            int relTime = (int)((now - mMovieStart) % dur);

            gifMovie.setTime(relTime);

            gifMovie.draw(canvas,x-((int)(gifMovie.width())),y-((int)(gifMovie.height())/2));

            invalidate();

        }

    }
    public void limpiar()
    {
        gifMovie=null;
    }
}

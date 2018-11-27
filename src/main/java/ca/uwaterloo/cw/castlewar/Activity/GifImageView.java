package ca.uwaterloo.cw.castlewar.Activity;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.net.Uri;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by WangY on 2018-02-28.
 */

public class GifImageView extends View {
    private InputStream myInputStream;
    private Movie myMovie;
    private int myWidth, myHeight;
    private long myStart;
    private Context myContext;

    public GifImageView(Context context) {
        super(context);
        this.myContext = context;
    }

    public GifImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GifImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.myContext = context;
        if (attrs.getAttributeName(1).equals("background")) {
            int id = Integer.parseInt(attrs.getAttributeValue(1).substring(1));
            setGifImageResource(id);
        }
    }


    private void init() {
        setFocusable(true);
        myMovie = Movie.decodeStream(myInputStream);
        myWidth = myMovie.width();
        myHeight = myMovie.height();

        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(myWidth, myHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        long now = SystemClock.uptimeMillis();

        if (myStart == 0) {
            myStart = now;
        }

        if (myMovie != null) {

            int duration = myMovie.duration();
            if (duration == 0) {
                duration = 1000;
            }

            int realTime = (int) ((now - myStart) % duration);

            myMovie.setTime(realTime);

            myMovie.draw(canvas, 0, 0);
            invalidate();
        }
    }

    public void setGifImageResource(int id) {
        myInputStream = myContext.getResources().openRawResource(id);
        init();
    }


}

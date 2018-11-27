package ca.uwaterloo.cw.castlewar.Base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import ca.uwaterloo.cw.castlewar.Activity.MainActivity;
import ca.uwaterloo.cw.castlewar.Effect.Buff;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Unit.Ability;
import ca.uwaterloo.cw.castlewar.Unit.Castle;
import ca.uwaterloo.cw.castlewar.Item.Item;
import ca.uwaterloo.cw.castlewar.Game.Level;
import ca.uwaterloo.cw.castlewar.Item.Potion;
import ca.uwaterloo.cw.castlewar.Game.Terrain;
import ca.uwaterloo.cw.castlewar.R;

/**
 * Created by harri on 2018/2/14.
 */

/*
    Here are basic mappings from id to gameobject
    and utility functions
*/


import android.app.Application;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;

import ca.uwaterloo.cw.castlewar.Unit.Chaotic;
import ca.uwaterloo.cw.castlewar.Unit.Lawful;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/2/14.
 */

/*
    this class basically contains
    1, configurations of this game
    2, utility functions,
    3, application context
    4, user data
*/

public class System extends Application {
    // self instance
    private static Application application;
    private static MainActivity activity; // kept for facebook

    // get reference of threadpool
    public static final ExecutorService gameThreads = Executors.newFixedThreadPool(3);
    public static final ExecutorService oneTimeThread = Executors.newFixedThreadPool(5);

    // output control
    public final static boolean gameFps = false;
    public final static boolean gameFlow = false;

    // size of the device screen
    private static int screenWidth;
    private static int screenHeight;
    private static int groundLine;
    private static Handler handler;

    public static void initialize(WindowManager windowManager, Handler h, MainActivity a) {
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        activity = a;
        screenWidth = point.x;
        screenHeight = point.y;
        groundLine = (int) (screenHeight * 0.8);
        handler = h;
    }

    public static Drawable getRandomTitleBackground(){
        Random random = new Random();
        int id = random.nextInt(4);
        switch(id){
            case 0: return scaleDrawable(R.drawable.background_desert, null, groundLine, 2);
            case 1: return scaleDrawable(R.drawable.background_desert_border, null, groundLine, 2);
            case 2: return scaleDrawable(R.drawable.background_desert_road, null, groundLine,2);
            case 3: return scaleDrawable(R.drawable.background_ruin, null, groundLine,2);
        }
        return null;
    }

    public static Bitmap getRandomGameBackground(int backgroundWidth){
        Random random = new Random();
        int id = random.nextInt(4);
        switch(id){
            case 0: return scaleBitmap(R.drawable.background_mountain, backgroundWidth, null,2);
            case 1: return scaleBitmap(R.drawable.background_near_lake, backgroundWidth, null,2);
            case 2: return scaleBitmap(R.drawable.background_nice_lake, backgroundWidth, null,2);
            case 3: return scaleBitmap(R.drawable.background_night_forest, backgroundWidth, null,2);
        }
        return null;
    }

    public static Drawable getRandomCombatBackground(int width){
        Random random = new Random();
        int id = random.nextInt(4);
        switch(id){
            case 0: return scaleDrawable(R.drawable.combat_castle, width, null,2);
            case 1: return scaleDrawable(R.drawable.combat_crystal, width, null,2);
            case 2: return scaleDrawable(R.drawable.combat_ice, width, null,2);
            case 3: return scaleDrawable(R.drawable.combat_lava, width, null,2);
        }
        return null;
    }

    public static Bitmap flipHorizontally(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap flipHorizontally(int resource, Integer width, Integer height, int downsize){
        Bitmap result = scaleBitmap(resource, width, height, downsize);
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(result, 0, 0, result.getWidth(), result.getHeight(), matrix, true);
    }

    public static Drawable scaleDrawable(int resource, Integer width, Integer height, int downsize){
        return new BitmapDrawable(resources(),scaleBitmap(resource, width, height, downsize));
    }

    public static Bitmap scaleBitmap(int resource, Integer width, Integer height, int downsize){
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = downsize;
        if (width == null && height == null) {
            return BitmapFactory.decodeResource(resources(), resource, option);
        } else if (width == null){
            Bitmap original = BitmapFactory.decodeResource(resources(), resource, option);
            float ratio = (float) height / (float) original.getHeight();
            return Bitmap.createScaledBitmap(original, (int) (original.getWidth() * ratio), height, true);
        } else if (height == null){
            Bitmap original = BitmapFactory.decodeResource(resources(), resource, option);
            float ratio = (float) width / (float)original.getWidth();
            return Bitmap.createScaledBitmap(original, width,  (int) (original.getHeight() * ratio), true);
        }
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources(), resource, option), width, height, true);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, Integer width, Integer height, int downsize) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = downsize;
        if (width == null && height == null) {
            return bitmap;
        } else if (width == null){
            float ratio = (float) height / (float) bitmap.getHeight();
            return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * ratio), height, true);
        } else if (height == null){
            float ratio = (float) width / (float)bitmap.getWidth();
            return Bitmap.createScaledBitmap(bitmap, width,  (int) (bitmap.getHeight() * ratio), true);
        }
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    public static MediaPlayer createMedia(int resource, boolean loop) {
        MediaPlayer mediaPlayer = MediaPlayer.create(activity, resource);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        mediaPlayer.setLooping(loop);
        return mediaPlayer;
    }

    public static int getGroundLine()
    {
        return groundLine;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static Context context()
    {
        return application.getApplicationContext();
    }

    public static Resources resources(){
        return application.getApplicationContext().getResources();
    }

    public static void runOnUi(Runnable r) {
        handler.post(r);
    }

    public static MainActivity getMainActivity() {
        return activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
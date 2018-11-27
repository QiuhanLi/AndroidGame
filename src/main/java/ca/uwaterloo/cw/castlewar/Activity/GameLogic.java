/*
package ca.uwaterloo.cw.castlewar.Activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ImageView;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ca.uwaterloo.cw.castlewar.Model.Ally;
import ca.uwaterloo.cw.castlewar.Model.Enemy;
import ca.uwaterloo.cw.castlewar.Model.Level;
import ca.uwaterloo.cw.castlewar.Model.SystemData;

*/
/**
 * Created by harrison33 on 2018/2/19.
*/

/*


public class GameLogic{


    // thread to update logic
    private class UpdateData extends Thread
    {
        private long realSleepTime;

        private void updateData()
        {

        }

        @Override
        public void run() {
            while (inGame)
            {
                realSleepTime = SystemClock.uptimeMillis() + DATA_SLEEP_TIME;
                updateData();
                realSleepTime -= SystemClock.uptimeMillis();
                if (realSleepTime > 0)
                {
                    try {
                        sleep(realSleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // thread to draw into canvas
    // and tell ui thread to update
    private class UpdateScreen extends Thread
    {
        private long realSleepTime;

        private void updateScreen()
        {
            final Bitmap screen = Bitmap.createBitmap(backgroundWidth, backgroundHeight, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(screen);
            canvas.drawBitmap(level.getTerrain().getImage(), 0, 0, paint);
            canvas.drawBitmap(SystemData.getAllyBitmap(SystemData.AllyId.SWORDMAN.id()),0, 0, paint);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(screen);
                    // tell screen thread ui is updated
                    synchronized (uILock)
                    {
                        uILock.finished = true;
                        uILock.notify();
                    }
                }
            });
        }

        @Override
        public void run() {
            while (inGame) {

                realSleepTime = SystemClock.uptimeMillis() + screenSleepTime;
                updateScreen();
                // wait until ui thread finish its work
                try {
                    synchronized (uILock)
                    {
                        while (!uILock.finished) uILock.wait();
                        uILock.finished = false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                realSleepTime -= SystemClock.uptimeMillis();
                if (realSleepTime > 0)
                {
                    // run ahead
                    // let's run faster
                    framePerSecond += 1;
                    if (framePerSecond >= MAX_FPS) framePerSecond = MAX_FPS;
                    screenSleepTime = MILISECOND / (long) framePerSecond;

                    try {
                        sleep(realSleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (realSleepTime < 0) {
                    // run below
                    // let's slow down
                    framePerSecond -= 1;
                    if (framePerSecond <= MIN_FPS) framePerSecond = MIN_FPS;
                    screenSleepTime = MILISECOND / (long) framePerSecond;
                }

                System.out.println("FPS: " + framePerSecond);
            }
        }
    }

    // Game object
    private ArrayList<Lawful> allies;
    private ArrayList<Chaotic> enemies;
    private Level level;

    // game control
    private UpdateData dataThread;
    private UpdateScreen screenThread;
    private final long MILISECOND = 1000;
    private final float DATA_PER_SECOND = 24;
    private final long MAX_FPS = 60;
    private final long MIN_FPS = 5;
    private float framePerSecond;
    private long screenSleepTime;
    private final long DATA_SLEEP_TIME = MILISECOND / (long) DATA_PER_SECOND;
    private boolean inGame;
    private UILock uILock = new UILock();

    // screen control
    private final int backgroundWidth;
    private final int backgroundHeight;
    private final Handler handler;
    private ImageView imageView;
    private Canvas canvas;
    private Paint paint;

    public GameLogic(Handler handler, ImageView imageView, Level level) {
        this.allies = new ArrayList<>(25);
        this.enemies = new ArrayList<>(25);
        this.dataThread = null;
        this.screenThread = null;
        this.level = level;
        this.framePerSecond = 30;
        this.paint = new Paint();
        this.inGame = false;
        this.imageView = imageView;
        this.handler = handler;
        this.backgroundWidth = level.getTerrain().getImage().getWidth();
        this.backgroundHeight = SystemData.getScreenHeight();
        this.screenSleepTime = MILISECOND / (long) framePerSecond;
    }

    public void onResume()
    {
        inGame = true;
        dataThread = new UpdateData();
        screenThread = new UpdateScreen();
        dataThread.start();
        screenThread.start();
    }

    public void onPause()
    {
        // wake up all threads
        // inform them to finish work
        inGame = false;
        uILock.finished = true;
        synchronized (uILock)
        {
            uILock.notifyAll();
        }
        if (dataThread != null)
        {
            try {
                dataThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (screenThread != null)
        {
            try {
                screenThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

 */
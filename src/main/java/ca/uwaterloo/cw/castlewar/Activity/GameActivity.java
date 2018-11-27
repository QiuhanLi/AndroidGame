package ca.uwaterloo.cw.castlewar.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.Random;

import ca.uwaterloo.cw.castlewar.Base.System;

import ca.uwaterloo.cw.castlewar.Game.Terrain;
import ca.uwaterloo.cw.castlewar.Game.Level;
import ca.uwaterloo.cw.castlewar.Game.GameManager;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.R;


public class GameActivity extends AppCompatActivity {
    private GameManager gameManager = null;
    private MediaPlayer background;
    private static GameActivity gameActivity;
    private boolean skip = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameActivity = this;
        this.skip = getIntent().getBooleanExtra("skip", false);

        if (this.skip) return;

        setContentView(R.layout.activity_singleplayer);
        background = MediaPlayer.create(this,R.raw.level_selection);
        background.setLooping(true);
        background.setVolume(0.5f, 0.5f);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (this.skip) {
            Random random = new Random();
            setContentView(R.layout.game_screen);
            ((ImageView) findViewById(R.id.GameLoading)).setImageBitmap(System.scaleBitmap(R.drawable.game_loading, 500, null, 1));
            final int player1 = random.nextInt(2);
            final int player2 = player1 == 0 ? 1 : 0;
            final int terrain = random.nextInt(3);
            System.oneTimeThread.execute(new Runnable() {
                @Override
                public void run() {
                    gameManager = new GameManager(GameActivity.this, Terrain.createTerrain(Id.Terrain.values()[terrain]), Id.Castle.values()[player1], Id.Castle.values()[player2]);
                    gameManager.onFirstStart();
                }
            });
            return;
        }

        ImageView imageView = findViewById(R.id.LevelBackground);
        imageView.setImageBitmap(System.scaleBitmap(R.drawable.plane_yellow, System.getScreenWidth(), System.getScreenHeight(),8));

        // Get the RecyclerView instance
        RecyclerView levelsRecyclerView = findViewById(R.id.LevelsRecyclerView);

        // Set the LayoutManager to be vertical (default)
        levelsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Set the adapter which will fill the data on the RecyclerView items
        levelsRecyclerView.setAdapter(new LevelsRecyclerViewAdapter(this));


    }

    public void startLevel(final Level level) {
        background.stop();
        background.release();
        background = null;
        setContentView(R.layout.game_screen);
        ((ImageView) findViewById(R.id.GameLoading)).setImageBitmap(System.scaleBitmap(R.drawable.game_loading, 500, null, 1));
        System.oneTimeThread.execute(new Runnable() {
            @Override
            public void run() {
                gameManager = new GameManager(GameActivity.this, level);
                gameManager.onFirstStart();
            }
        });
    }
    public void onResume() {
        super.onResume();
        if (background != null) {
            background.start();
        }
        if (gameManager != null)
            gameManager.onResume();
    }

    public void onPause() {
        super.onPause();
        if (background != null) {
            background.pause();
        }
        if(gameManager != null)
            gameManager.onPause();
    }

    public static GameActivity instance() {
        return gameActivity;
    }
}

package ca.uwaterloo.cw.castlewar.Activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import ca.uwaterloo.cw.castlewar.Base.Recorder;
import ca.uwaterloo.cw.castlewar.Base.System;

import ca.uwaterloo.cw.castlewar.R;

public class MainActivity extends AppCompatActivity{
    private MediaProjectionManager projectionManager;
    private final int REQUEST_MEDIA_PROJECTION = 1;
    private MediaPlayer background;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.initialize(getWindowManager(), handler, this);

        findViewById(R.id.SinglePlayer).setBackground(System.scaleDrawable(R.drawable.blue_button,null,null,1));
        findViewById(R.id.MultiPlayer).setBackground(System.scaleDrawable(R.drawable.blue_button,null,null,1));
        findViewById(R.id.Inventory).setBackground(System.scaleDrawable(R.drawable.blue_button,null,null,1));


        // setup record
        projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        startActivityForResult(projectionManager.createScreenCaptureIntent(),REQUEST_MEDIA_PROJECTION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageView title = findViewById(R.id.GameTitleImage);
        title.setBackground(System.getRandomTitleBackground());
    }

    @Override
    protected void onPause() {
        super.onPause();
        background.stop();
        background.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        background = System.createMedia(R.raw.theme, true);
        background.start();
    }

    public void enterSinglePlayer(View view) {
        System.createMedia( R.raw.book, false).start();
        // Create an intent to show a new screen passing data to it
        Intent userIntent = new Intent(this, GameActivity.class);
        userIntent.putExtra("skip", false);
        // Show new screen
        startActivity(userIntent);
    }

    public void enterMultiPlayer(View view) {
        System.createMedia( R.raw.book, false).start();
        // Create an intent to show a new screen passing data to it
        Intent userIntent = new Intent(this, GameActivity.class);
        userIntent.putExtra("skip", true);
        // Show new screen
        startActivity(userIntent);
    }


    public void enterInventory(View view) {
        System.createMedia( R.raw.book, false).start();
        // Create an intent to show a new screen passing data to it
        Intent userIntent = new Intent(this, InventoryActivity.class);

        // Show new screen
        startActivity(userIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MEDIA_PROJECTION) {
            if (resultCode != RESULT_OK) {
                Toast.makeText(this,
                        "Screen Cast Permission Denied", Toast.LENGTH_SHORT).show();
                return;
            }
            Recorder.initialize(projectionManager.getMediaProjection(resultCode, data), getWindowManager());
        } else if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Shared!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

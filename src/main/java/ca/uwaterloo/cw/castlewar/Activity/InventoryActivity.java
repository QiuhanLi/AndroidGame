
package ca.uwaterloo.cw.castlewar.Activity;

/**
 * Created by Sparks on 2018-02-23.
 */

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import ca.uwaterloo.cw.castlewar.Effect.Buff;
import ca.uwaterloo.cw.castlewar.Effect.Effect;
import ca.uwaterloo.cw.castlewar.Item.Potion;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Item.Item;
import ca.uwaterloo.cw.castlewar.Base.System;
import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Unit.Chaotic;
import ca.uwaterloo.cw.castlewar.Unit.Lawful;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getCacheDir;


public class InventoryActivity extends AppCompatActivity {
    private final ArrayList<Item> potion = Potion.getAllPotion();
    private final ArrayList<Unit> lawful = Lawful.getAllLawful();
    private final ArrayList<Unit> chaotic = Chaotic.getAllChaotic();
    private final ArrayList<Effect> buff = Buff.getAllBuff();
    private MediaPlayer background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        // Initialize items in the inventory and add them to the ArrayList

        ViewPager viewPager = findViewById(R.id.inventoryPage);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        findViewById(R.id.inventoryWrapper).setBackground(System.scaleDrawable(R.drawable.screen_board, System.getScreenWidth(), null, 2));
    }


    @Override
    protected void onStart() {
        super.onStart();

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
        background = System.createMedia(R.raw.inventory, true);
        background.start();
    }

    private class PageAdapter extends FragmentStatePagerAdapter {
        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new PageFragement.LawfulPage();
                case 1: return new PageFragement.ChaoticPage();
                case 2: return new PageFragement.ItemPage();
                case 3: return new PageFragement.EffectPage();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
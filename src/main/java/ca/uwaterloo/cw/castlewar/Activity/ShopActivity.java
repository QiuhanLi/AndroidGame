/*
        package ca.uwaterloo.cw.castlewar.Activity;


 Created by Sparks on 2018-02-23.


        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import ca.uwaterloo.cw.castlewar.Item.Potion;
        import ca.uwaterloo.cw.castlewar.Structure.Id;
        import ca.uwaterloo.cw.castlewar.Item.Item;
        import ca.uwaterloo.cw.castlewar.Base.System;
        import ca.uwaterloo.cw.castlewar.Base.User;
        import ca.uwaterloo.cw.castlewar.R;

        import java.util.ArrayList;
        import java.util.List;


public class ShopActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private List<Item> shopItems = new ArrayList<>();
    private long myCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        // Initialize items in the shop and add them to the ArrayList
        shopItems = Potion.getAllPotion();

        // Construct User Profile to get amount of coins
        myCoins = User.getCOIN().getNum();

        // Private reference to the progress bar
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.imageView4).setBackground(System.scaleDrawable(R.drawable.background_near_lake, null, System.getScreenHeight(), 2));
        findViewById(R.id.coinImageView).setBackground(System.scaleDrawable(R.drawable.gold_coin, 200, 200, 2));
    }


    @Override
    protected void onStart() {
        super.onStart();

        // Show the status before the start
        progressBar.setVisibility(View.VISIBLE);

        // Get the RecyclerView instance
        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.shopItemsRecyclerView);

        // Get the TextView
        TextView myTextView = findViewById(R.id.coinNum);

        long money = User.getCOIN().getNum();
        myTextView.setText(Long.toString(money));

        // use a linear layout manager
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(myLayoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter myAdapter = new ShopItemsRecyclerViewAdapter(shopItems, myTextView);
        myRecyclerView.setAdapter(myAdapter);

        // Hide the progress bar when all items are presented
        progressBar.setVisibility(View.INVISIBLE);

    }
}
*/
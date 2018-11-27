package ca.uwaterloo.cw.castlewar.Activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Game.Level;
import ca.uwaterloo.cw.castlewar.Base.System;
import ca.uwaterloo.cw.castlewar.R;

public class LevelsRecyclerViewAdapter extends RecyclerView.Adapter<LevelsRecyclerViewAdapter.ViewHolder>{
    private final GameActivity gameActivity;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView levelTextView;
        public TextView terrainTextView;
        public TextView DifficultyTextView;
        public TextView rewardsTextView;
        public ImageButton startButton;
        public CardView backGround;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    // Construct the ViewAdapter
    public LevelsRecyclerViewAdapter(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    // Create new views
    @Override
    public LevelsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        ConstraintLayout levelsView = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.levels, parent, false);

        // Get references to view components
        TextView levelTextView = levelsView.findViewById(R.id.itemName);
        TextView terrainTextView = levelsView.findViewById(R.id.Terrain);
        TextView DifficultyTextView = levelsView.findViewById(R.id.Difficulty);
        TextView rewardsTextView = levelsView.findViewById(R.id.Rewards);
        ImageButton startButton = levelsView.findViewById(R.id.GoButton);
        CardView background = levelsView.findViewById(R.id.LevelBackground);

        // Create a new ViewHolder instance and assign references to it
        LevelsRecyclerViewAdapter.ViewHolder viewHolder = new LevelsRecyclerViewAdapter.ViewHolder(levelsView);
        viewHolder.levelTextView = levelTextView;
        viewHolder.terrainTextView = terrainTextView;
        viewHolder.DifficultyTextView = DifficultyTextView;
        viewHolder.rewardsTextView = rewardsTextView;
        viewHolder.startButton = startButton;
        viewHolder.backGround = background;

        return viewHolder;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final int freshPosition = holder.getAdapterPosition();
        final Level level = Level.createLevel(Id.Level.values()[freshPosition]);

        // Assign data to view components
        holder.levelTextView.setText(level.getName());
        holder.terrainTextView.setText(level.getDisplayableTerrain());
        holder.DifficultyTextView.setText(level.getDisplayableAlly());
        holder.rewardsTextView.setText(level.getDisplayableEnemy());
        holder.startButton.setBackground(System.scaleDrawable(R.drawable.button_right, 200, 200,4));
        holder.backGround.setBackground(System.scaleDrawable(R.drawable.blue_button, System.getScreenWidth(), System.getScreenHeight() / 4,1));
        holder.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.createMedia(R.raw.book, false).start();
                gameActivity.startLevel(level);
            }
        });
    }

    // Return the size of myShopItems
    @Override
    public int getItemCount() {
        return Id.Level.values().length;
    }
}

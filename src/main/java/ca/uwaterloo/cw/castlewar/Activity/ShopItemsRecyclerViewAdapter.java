/*
        package ca.uwaterloo.cw.castlewar.Activity;

        import android.content.Context;
        import android.support.constraint.ConstraintLayout;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import ca.uwaterloo.cw.castlewar.Item.Item;
        import ca.uwaterloo.cw.castlewar.Base.System;
        import ca.uwaterloo.cw.castlewar.Base.User;
        import ca.uwaterloo.cw.castlewar.R;


        import java.util.List;


 * Created by Sparks on 2018-02-23.


public class ShopItemsRecyclerViewAdapter extends RecyclerView.Adapter<ShopItemsRecyclerViewAdapter.ViewHolder>{
    private List<Item> myShopItems;
    private TextView myTextView;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemNameTextView;
        public Button itemButton;
        public ImageView itemPictureImageView;
        public TextView itemDescriptionTextView;

        // Construct the ViewHolder
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    // Construct the ViewAdapter
    public ShopItemsRecyclerViewAdapter(List<Item> shopItems, TextView textView) {
        myShopItems = shopItems;
        myTextView = textView;
    }

    // Create new views
    @Override
    public ShopItemsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        ConstraintLayout shopItemView = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);

        // Get references to view components
        TextView itemNameView = shopItemView.findViewById(R.id.itemName);
        TextView itemDescriptionTextView = shopItemView.findViewById(R.id.itemDescription);
        ImageView itemPictureImageView = shopItemView.findViewById(R.id.itemPicture);
        Button itemButton = shopItemView.findViewById(R.id.itemButton);
        // Create a new ViewHolder instance and assign references to it
        ShopItemsRecyclerViewAdapter.ViewHolder viewHolder = new ShopItemsRecyclerViewAdapter.ViewHolder(shopItemView);
        viewHolder.itemNameTextView = itemNameView;
        viewHolder.itemDescriptionTextView = itemDescriptionTextView;
        viewHolder.itemPictureImageView = itemPictureImageView;
        viewHolder.itemButton = itemButton;
        return viewHolder;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        // Get the pair to have its data displayed at position in the list
        final Item item = myShopItems.get(position);
        final Context context = System.context();
        // Assign data to view components
        holder.itemNameTextView.setText(item.getName());
        holder.itemDescriptionTextView.setText(item.getDescription());
        holder.itemPictureImageView.setBackground(System.scaleDrawable(R.drawable.item_background, 200,200,1));
        holder.itemButton.setBackground(System.scaleDrawable(R.drawable.yellow_button, 200,200,2));
        holder.itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.upgrade()){
                    Toast.makeText(context,"Item upgraded", Toast.LENGTH_SHORT).show();
                    long money = User.getCOIN().getNum();
                    myTextView.setText(Long.toString(money));
                } else {
                    Toast.makeText(context,"Not Enough Money" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Load Picture to view components
        holder.itemPictureImageView.setImageBitmap(item.getPortrait());

    }


    // Return the size of myShopItems
    @Override
    public int getItemCount() {
        return myShopItems.size();
    }
}
*/
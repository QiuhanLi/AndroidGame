
        package ca.uwaterloo.cw.castlewar.Activity;

        import android.support.constraint.ConstraintLayout;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import ca.uwaterloo.cw.castlewar.Effect.Buff;
        import ca.uwaterloo.cw.castlewar.Effect.Effect;
        import ca.uwaterloo.cw.castlewar.Item.Item;
        import ca.uwaterloo.cw.castlewar.Item.Potion;
        import ca.uwaterloo.cw.castlewar.R;
        import ca.uwaterloo.cw.castlewar.Structure.Id;
        import ca.uwaterloo.cw.castlewar.Unit.Chaotic;
        import ca.uwaterloo.cw.castlewar.Unit.Lawful;
        import ca.uwaterloo.cw.castlewar.Unit.Unit;


        import java.util.ArrayList;

        /**
 * Created by Sparks on 2018-02-23.
 */

public class InventoryRecyclerView {
    public static class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
        private ArrayList<Item> items;

        // Provide a reference to the views for each data item
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView itemName;
            public ImageView itemPortrait;
            public TextView itemDescription;

            // Construct the ViewHolder
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        // Construct the ViewAdapter
        public ItemAdapter() {
            this.items = Potion.getAllPotion();
            for (Item item : items) {
                item.getSprite().setConfig(500,500,1);
            }
        }

        // Create new views
        @Override
        public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view
            ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);

            // Get references to view components
            TextView itemName = view.findViewById(R.id.itemName);
            TextView itemDescription = view.findViewById(R.id.itemDescription);
            ImageView itemPortrait = view.findViewById(R.id.itemPortrait);
            // Create a new ViewHolder instance and assign references to it
            ItemAdapter.ViewHolder viewHolder = new ItemAdapter.ViewHolder(view);
            viewHolder.itemName = itemName;
            viewHolder.itemDescription = itemDescription;
            viewHolder.itemPortrait = itemPortrait;
            return viewHolder;
        }

        // Replace the contents of a view
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            // Get the pair to have its data displayed at position in the list
            Item item = this.items.get(position);

            // Assign data to view components
            String string;
            holder.itemName.setText(item.getName());
            holder.itemDescription.setText(item.getDescription());
            holder.itemPortrait.setImageBitmap(item.getPortrait());
        }


        // Return the size of myInventoryItems
        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    public static class EffectAdapter extends RecyclerView.Adapter<EffectAdapter.ViewHolder>{
        private ArrayList<Effect> effects;

        // Provide a reference to the views for each data effect
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data effect is just a string in this case
            public TextView effectName;
            public ImageView effectPortrait;
            public TextView effectDescription;

            // Construct the ViewHolder
            public ViewHolder(View effectView) {
                super(effectView);
            }
        }

        // Construct the ViewAdapter
        public EffectAdapter() {
            this.effects = Buff.getAllBuff();
            for (Effect effect : effects) {
                effect.getSprite().setConfig(100,100,1);
            }
        }

        // Create new views
        @Override
        public EffectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view
            ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_effect, parent, false);

            // Get references to view components
            TextView effectName = view.findViewById(R.id.effectName);
            TextView effectDescription = view.findViewById(R.id.effectDescription);
            ImageView effectPortrait = view.findViewById(R.id.effectPortrait);
            // Create a new ViewHolder instance and assign references to it
            EffectAdapter.ViewHolder viewHolder = new EffectAdapter.ViewHolder(view);
            viewHolder.effectName = effectName;
            viewHolder.effectDescription = effectDescription;
            viewHolder.effectPortrait = effectPortrait;
            return viewHolder;
        }

        // Replace the contents of a view
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            // Get the pair to have its data displayed at position in the list
            Effect effect = this.effects.get(position);

            // Assign data to view components
            holder.effectName.setText(effect.getName());
            holder.effectDescription.setText(effect.getDescription());
            holder.effectPortrait.setImageBitmap(effect.getPortrait());
        }


        // Return the size of effect
        @Override
        public int getItemCount() {
            return effects.size();
        }
    }

    public static class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder>{
        private ArrayList<Unit> units;

        // Provide a reference to the views for each data unit
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data unit is just a string in this case
            public TextView unitName;
            public TextView unitDescription;
            public ImageView unitCombat;

            // Construct the ViewHolder
            public ViewHolder(View unitView) {
                super(unitView);
            }
        }

        // Construct the ViewAdapter
        public UnitAdapter(Id.Castle castle) {
            if (castle == Id.Castle.HOLY_CASTLE) {
                this.units = Lawful.getAllLawful();
            } else {
                this.units = Chaotic.getAllChaotic();
            }
        }

        // Create new views
        @Override
        public UnitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view
            ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_unit, parent, false);

            // Get references to view components
            TextView unitName = view.findViewById(R.id.unitName);
            TextView unitDescription = view.findViewById(R.id.unitDescription);
            ImageView unitCombat = view.findViewById(R.id.unitCombat);
            // Create a new ViewHolder instance and assign references to it
            UnitAdapter.ViewHolder viewHolder = new UnitAdapter.ViewHolder(view);
            viewHolder.unitName = unitName;
            viewHolder.unitDescription = unitDescription;
            viewHolder.unitCombat = unitCombat;
            return viewHolder;
        }

        // Replace the contents of a view
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            // Get the pair to have its data displayed at position in the list
            Unit unit = this.units.get(position);

            // Assign data to view components
            holder.unitName.setText(unit.getName());
            holder.unitDescription.setText(unit.getDescription());
            holder.unitCombat.setImageBitmap(unit.getSprite().getCombat(Id.Direction.RIGHT));
        }


        // Return the size of unit
        @Override
        public int getItemCount() {
            return units.size();
        }
    }
}


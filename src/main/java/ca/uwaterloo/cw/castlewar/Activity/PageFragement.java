package ca.uwaterloo.cw.castlewar.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.uwaterloo.cw.castlewar.Base.System;
import ca.uwaterloo.cw.castlewar.Effect.Buff;
import ca.uwaterloo.cw.castlewar.Effect.Effect;
import ca.uwaterloo.cw.castlewar.Item.Item;
import ca.uwaterloo.cw.castlewar.Item.Potion;
import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Unit.Castle;
import ca.uwaterloo.cw.castlewar.Unit.Chaotic;
import ca.uwaterloo.cw.castlewar.Unit.Lawful;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

abstract public class PageFragement {


    public static class ItemPage extends  Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Get the RecyclerView instance
            RecyclerView recyclerView = new RecyclerView(System.getMainActivity());

            // use a linear layout manager
            RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(myLayoutManager);

            // specify an adapter (see also next example)
            RecyclerView.Adapter adapter = new InventoryRecyclerView.ItemAdapter();
            recyclerView.setAdapter(adapter);

            return recyclerView;
        }
    }

    public static class LawfulPage extends  Fragment{
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Get the RecyclerView instance
            RecyclerView recyclerView = new RecyclerView(System.getMainActivity());

            // use a linear layout manager
            RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(myLayoutManager);

            // specify an adapter (see also next example)
            RecyclerView.Adapter adapter = new InventoryRecyclerView.UnitAdapter(Id.Castle.HOLY_CASTLE);
            recyclerView.setAdapter(adapter);

            return recyclerView;
        }
    }

    public static class ChaoticPage extends  Fragment{
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Get the RecyclerView instance
            RecyclerView recyclerView = new RecyclerView(System.getMainActivity());

            // use a linear layout manager
            RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(myLayoutManager);

            // specify an adapter (see also next example)
            RecyclerView.Adapter adapter = new InventoryRecyclerView.UnitAdapter(Id.Castle.EVIL_CASTLE);
            recyclerView.setAdapter(adapter);

            return recyclerView;
        }
    }

    public static class EffectPage extends  Fragment{
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Get the RecyclerView instance
            RecyclerView recyclerView = new RecyclerView(System.getMainActivity());

            // use a linear layout manager
            RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(myLayoutManager);

            // specify an adapter (see also next example)
            RecyclerView.Adapter adapter = new InventoryRecyclerView.EffectAdapter();
            recyclerView.setAdapter(adapter);

            return recyclerView;
        }
    }
}

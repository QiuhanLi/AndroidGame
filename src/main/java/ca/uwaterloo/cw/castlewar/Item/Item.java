package ca.uwaterloo.cw.castlewar.Item;

import java.util.ArrayList;

import ca.uwaterloo.cw.castlewar.Base.GameObject;
import ca.uwaterloo.cw.castlewar.Base.Status;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/2/15.
 */

abstract public class Item extends GameObject {

    public Item(int id,String name, String description, int resource, Status status) {
        super(id, name, description, resource, status);
    }


    public void clone(Item item) {
        this.getSprite().clone(item.getSprite());
    }

    abstract public void use(ArrayList<Unit> units);
}

package ca.uwaterloo.cw.castlewar.Unit;

import java.util.ArrayList;

import ca.uwaterloo.cw.castlewar.Base.GameObject;
import ca.uwaterloo.cw.castlewar.Structure.Id;

/**
 * Created by harri on 2018/3/24.
 */

abstract public class Ability extends GameObject {
    private int level;
    private Id.CombatStage triggerStage = Id.CombatStage.START;

    public Ability(int id, String name,String description, int resource) {
        super(id, name,description, resource);
        this.level = 1;
    }

    public static ArrayList<Ability> getAllAbility() {
        ArrayList<Ability> abilities = new ArrayList<>();

        return abilities;
    }

    abstract void apply(Unit unit);

    public Id.CombatStage getTriggerStage() {
        return triggerStage;
    }

    public static class Block extends  Ability {
        public Block(int id, String name, String description, int resource) {
            super(id, name, description, resource);
        }

        @Override
        void apply(Unit unit) {

        }
    }

}

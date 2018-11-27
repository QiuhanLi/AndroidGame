package ca.uwaterloo.cw.castlewar.Item;


import java.util.ArrayList;

import ca.uwaterloo.cw.castlewar.Base.Sprite;
import ca.uwaterloo.cw.castlewar.Base.Status;
import ca.uwaterloo.cw.castlewar.Effect.Buff;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/2/14.
 */

abstract public class Potion extends Item {

    public Potion(int id, String name,String description, int resource, Status status) {
        super(id, name, description, resource, status);
    }

    public static ArrayList<Item> getAllPotion() {
        ArrayList<Item> potion = new ArrayList<>();
        potion.add(new HpPotion());
        potion.add(new AttackPotion());
        potion.add(new DefensePotion());
        potion.add(new SpeedPotion());
        return potion;
    }

    public static class HpPotion extends Potion {
        private int restore = 30;
        public HpPotion() {
            super(Id.Item.HP_POTION.ordinal(), "Health Potion",
                    "A fantastic potion that can heal wounds.\n" +
                            "Restore 30 Hit Point for units.\n" +
                            "1 Cost",
                    R.drawable.potion_hp, new Status(1));
        }

        @Override
        public void use(ArrayList<Unit> units) {
            for (Unit unit : units) {
                unit.modifyBaseHealth(restore);
            }
        }
    }

    public static class AttackPotion extends Potion
    {
        public AttackPotion()
        {
            super(Id.Item.ATTACK_POTION.ordinal(), "Attack Potion",
                    "Enrage! Be brave!\n" +
                            "Add [Brave] Buff to units.\n" +
                            "1 Cost",
                    R.drawable.potion_attack, new Status(1));
        }

        @Override
        public void use(ArrayList<Unit> units) {
            Sprite sprite = new Buff.Brave().getSprite();
            sprite.getPortrait();
            for (Unit unit : units) {
                Buff.Brave brave = new Buff.Brave();
                brave.getSprite().clone(sprite);
                unit.receiveEffect(brave);
            }
        }
    }

    public static class DefensePotion extends Potion
    {
        public DefensePotion() {
            super(Id.Item.DEFENSE_POTION.ordinal(), "Defense Potion",
                    "No pain. No Fear.\n" +
                            "Add [Tough] Buff to units\n" +
                            "1 Cost",
                    R.drawable.potion_defense, new Status(1));
        }

        @Override
        public void use(ArrayList<Unit> units) {
            Sprite sprite = new Buff.Tough().getSprite();
            sprite.getPortrait();
            for (Unit unit : units) {
                Buff.Tough tough = new Buff.Tough();
                tough.getSprite().clone(sprite);
                unit.receiveEffect(tough);
            }
        }
    }

    public static class SpeedPotion extends Potion
    {
        public SpeedPotion() {
            super(Id.Item.SPEED_POTION.ordinal(), "Speed Potion",
                    "Light like a feather...well, just a moment.\n" +
                            "Add [Swift] Buff to units.\n" +
                            "1 Cost",
                    R.drawable.potion_speed, new Status(1));
        }

        @Override
        public void use(ArrayList<Unit> units) {
            Sprite sprite = new Buff.Swift().getSprite();
            sprite.getPortrait();
            for (Unit unit : units) {
                Buff.Swift swift = new Buff.Swift();
                swift.getSprite().clone(sprite);
                unit.receiveEffect(swift);
            }
        }
    }
}

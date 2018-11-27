
package ca.uwaterloo.cw.castlewar.Unit;
import ca.uwaterloo.cw.castlewar.Base.Status;
import ca.uwaterloo.cw.castlewar.Structure.Id;

import java.util.ArrayList;
import java.util.Arrays;

import ca.uwaterloo.cw.castlewar.R;

/**
 * Created by harri on 2018/2/14.
 */

abstract public class Lawful extends Unit {
    public Lawful(int id, String name, String description, int resource, Status status, Integer move ,Integer combat, Id.Direction initialDirection, Id.Attack attack) {
        super(id, name,description, resource,status, move, combat, initialDirection, attack);
    }

    public static ArrayList<Unit> getAllLawful() {
        ArrayList<Unit> lawful = new ArrayList<>();
        lawful.add(new SwordMan());
        lawful.add(new Archer());
        lawful.add(new Icemage());
        lawful.add(new Prince());
        lawful.add(new Goddess());
        return lawful;
    }

    public static class Archer extends Lawful {
        public Archer() {
            super(Id.Lawful.ARCHER.ordinal(), "Archer", "Deadly Bow and Eagle Eyes.\n" +
                    "50 Hit Point\n" +
                    "30 Attack\n" +
                    "10 Defense\n" +
                    "10 Agility\n" +
                    "1 Move Range\n" +
                    "1 - 2 Attack Range\n" +
                    "1 Cost",
                    R.drawable.archer_portrait, new Status(50, 50, 30, 10, 10, 1, 1, 2, 1),
                    R.drawable.archer_move, R.drawable.archer_combat, Id.Direction.RIGHT, Id.Attack.ARROW);
        }
    }

    public static class Icemage extends Lawful {
        public Icemage() {
            super(Id.Lawful.ICEMAGE.ordinal(), "Ice Mage","Chill. Freeze. Die.\n" +
                            "30 Hit Point\n" +
                            "40 Attack\n" +
                            "0 Defense\n" +
                            "0 Agility\n" +
                            "1 Move Range\n" +
                            "0 - 2 Attack Range\n" +
                            "1 Cost",
                    R.drawable.icemage_portrait, new Status(30, 30, 40, 0, 0, 1, 0, 2, 1),
                    R.drawable.icemage_move, R.drawable.icemage_combat, Id.Direction.RIGHT, Id.Attack.ICE_FALL);
        }
    }

    public static class SwordMan extends Lawful {
        public SwordMan() {
            super(Id.Lawful.SWORDMAN.ordinal(), "Sword Man", "Stand by me.\n" +
                            "100 Hit Point\n" +
                            "20 Attack\n" +
                            "25 Defense\n" +
                            "5 Agility\n" +
                            "1 Move Range\n" +
                            "0 - 1 Attack Range\n" +
                            "1 Cost",
                    R.drawable.swordman_portrait, new Status(100, 100, 20, 25, 5, 1, 0, 1, 1),
                    R.drawable.swordman_move, R.drawable.swordman_combat, Id.Direction.LEFT, Id.Attack.THUNDER_SLASH);
        }
    }

    public static class Prince extends Lawful
    {
        public Prince() {
            super(Id.Lawful.PRINCE.ordinal(), "Prince", "Protect my people. Protect our faith.\n" +
                            "80 Hit Point\n" +
                            "40 Attack\n" +
                            "20 Defense\n" +
                            "20 Agility\n" +
                            "2 Move Range\n" +
                            "0 - 1 Attack Range\n" +
                            "2 Cost",
                    R.drawable.prince_portrait, new Status(80, 80, 40, 20, 20, 2, 0, 1, 2),
                    R.drawable.prince_move, R.drawable.prince_combat, Id.Direction.LEFT, Id.Attack.GREAT_SLASH);
        }
    }

    public static class Goddess extends Lawful
    {
        public Goddess() {
            super(Id.Lawful.GODDESS.ordinal(), "Goddess", "May light be with you.\n" +
                            "50 Hit Point\n" +
                            "50 Attack\n" +
                            "10 Defense\n" +
                            "10 Agility\n" +
                            "1 Move Range\n" +
                            "0 - 5 Attack Range\n" +
                            "2 Cost",
                    R.drawable.goddess_portrait, new Status(50, 50, 50, 10, 10, 1, 0, 5, 2),
                    R.drawable.goddess_move, R.drawable.goddess_combat, Id.Direction.LEFT, Id.Attack.MAGIC_SLASH);
        }
    }
}
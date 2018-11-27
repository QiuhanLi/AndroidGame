package ca.uwaterloo.cw.castlewar.Unit;

import java.util.ArrayList;
import java.util.Arrays;

import ca.uwaterloo.cw.castlewar.Base.Status;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.R;

/**
 * Created by harri on 2018/2/14.
 */

abstract public class Chaotic extends Unit {
    public Chaotic(int id, String name, String description, int resource, Status status,Integer move ,Integer combat, Id.Direction initialDirection, Id.Attack attack)
    {
        super(id, name, description, resource,status, move, combat, initialDirection, attack);
    }

    public static ArrayList<Unit> getAllChaotic() {
        ArrayList<Unit> chaotic = new ArrayList<>();
        chaotic.add(new Bat());
        chaotic.add(new Ghost());
        chaotic.add(new Imp());
        chaotic.add(new Minotaur());
        chaotic.add(new Darklord());

        return chaotic;
    }

    public static class Bat extends Chaotic {
        public Bat() {
            super(Id.Chaotic.BAT.ordinal(), "Bat", "Squeak...Squeak...\n"  +
                            "30 Hit Point\n" +
                            "35 Attack\n" +
                            "5 Defense\n" +
                            "30 Agility\n" +
                            "2 Move Range\n" +
                            "0 - 1 Attack Range\n" +
                            "1 Cost", R.drawable.bat_portrait,
                    new Status(30, 30, 35, 5, 30, 2, 0, 1, 1),
                    R.drawable.bat_move, R.drawable.bat_combat, Id.Direction.RIGHT, Id.Attack.CLAW);
        }
    }

    public static class Imp extends Chaotic {
        public Imp() {
            super(Id.Chaotic.IMP.ordinal(), "Imp", "Trick. Trick. Death Trick.\n"  +
                            "50 Hit Point\n" +
                            "30 Attack\n" +
                            "15 Defense\n" +
                            "15 Agility\n" +
                            "2 Move Range\n" +
                            "0 - 1 Attack Range\n" +
                            "1 Cost", R.drawable.imp_portrait,
                    new Status(50, 50, 30, 15, 15, 2, 0, 1, 1),
                    R.drawable.imp_move, R.drawable.imp_combat, Id.Direction.LEFT, Id.Attack.DARK_BALL);
        }
    }

    public static class Ghost extends Chaotic {
        public Ghost() {
            super(Id.Chaotic.GHOST.ordinal(), "Ghost", "No one ever touches me.\n"  +
                            "10 Hit Point\n" +
                            "20 Attack\n" +
                            "40 Defense\n" +
                            "0 Agility\n" +
                            "1 Move Range\n" +
                            "0 - 1 Attack Range\n" +
                            "1 Cost",
                    R.drawable.ghost_portrait, new Status(10, 10, 20, 40, 0, 1, 0, 1, 1),
                    R.drawable.ghost_move, R.drawable.ghost_combat, Id.Direction.RIGHT, Id.Attack.DARK_RUNE);
        }
    }

    public static class Minotaur extends Chaotic {
        public Minotaur() {
            super(Id.Chaotic.MINOTAUR.ordinal(), "Minotaur", "Moo...Moo...Mighty!\n"  +
                            "150 Hit Point\n" +
                            "35 Attack\n" +
                            "0 Defense\n" +
                            "0 Agility\n" +
                            "1 Move Range\n" +
                            "0 - 1 Attack Range\n" +
                            "2 Cost",
                    R.drawable.minotaur_portrait, new Status(150, 150, 35, 0, 0, 1, 0, 1, 2),
                    R.drawable.minotaur_move, R.drawable.minotaur_combat, Id.Direction.RIGHT, Id.Attack.SLASH_FIRE);
        }
    }

    public static class Darklord extends Chaotic {
        public Darklord() {
            super(Id.Chaotic.DARKLORD.ordinal(), "Dark Lord", "Stare into the abyss, I'm waiting there.\n"  +
                            "100 Hit Point\n" +
                            "50 Attack\n" +
                            "20 Defense\n" +
                            "15 Agility\n" +
                            "1 Move Range\n" +
                            "0 - 2 Attack Range\n" +
                            "3 Cost", R.drawable.darklord_portrait,
                    new Status(100, 100, 40, 20, 15, 1, 0, 2, 3),
                    R.drawable.darklord_move, R.drawable.darklord_combat, Id.Direction.RIGHT, Id.Attack.DARK_SLASH);
        }
    }
}
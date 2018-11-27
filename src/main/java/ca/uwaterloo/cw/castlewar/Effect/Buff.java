package ca.uwaterloo.cw.castlewar.Effect;


import java.util.ArrayList;

import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/2/14.
 */

abstract public class Buff extends Effect {

    public Buff(int id, String name,String description, int resource, int turn, int stack, int maxTurn, int maxStack) {
        super(id, name,description, resource, turn, stack, maxTurn, maxStack);
    }

    public static ArrayList<Effect> getAllBuff() {
        ArrayList<Effect> buffs = new ArrayList<>();
        buffs.add(new Brave());
        buffs.add(new Tough());
        buffs.add(new Swift());
        return buffs;
    }

    @Override
    public void apply(Unit unit) {

    }


    public static class Brave extends Buff {
        private static final Integer value = 5;
        private static final Integer initial_turn = 2;
        private static final Integer initial_stack = 1;
        private static final Integer max_turn = MAX_TURN;
        private static final Integer max_stack = 6;
        public Brave() {
            super(Id.Buff.BRAVE.ordinal(), "Brave", "Attack increased by " + value.toString() + " per stack.\n" +
                    "Last "+ initial_turn.toString() + " turns.\n" +
                    "Can stack up to "+ max_stack + " times.",R.drawable.buff_brave, initial_turn, initial_stack, max_turn, max_stack);
        }

        @Override
        public void reapply(Unit unit) {
            unit.getModifiedStatus().setAttack(unit.getModifiedStatus().getAttack() + value  * getStack());
        }
    }

    public static class Tough extends Buff
    {
        private static final Integer value = 3;
        private static final Integer initial_turn = 3;
        private static final Integer initial_stack = 1;
        private static final Integer max_turn = MAX_TURN;
        private static final Integer max_stack = 10;
        public Tough()
        {
            super(Id.Buff.TOUGH.ordinal(), "Tough","Defense increased by " + value.toString() + " per stack.\n" +
                    "Last "+ initial_turn.toString() + " turns.\n" +
                    "Can stack up to "+ max_stack + " times.",R.drawable.buff_tough, initial_turn, initial_stack, max_turn, max_stack);
        }

        @Override
        public void reapply(Unit unit) {
            unit.getModifiedStatus().setDefense(unit.getModifiedStatus().getDefense() + value  * getStack());
        }
    }

    public static class Swift extends Buff {
        private static final Integer value = 5;
        private static final Integer initial_turn = 1;
        private static final Integer initial_stack = 1;
        private static final Integer max_turn = 1;
        private static final Integer max_stack = 1;
        public Swift()
        {
            super(Id.Buff.SWIFT.ordinal(), "Swift","Speed increased by " + value.toString() + " per stack.\n" +
                    "Last "+ initial_turn.toString() + " turns.\n" +
                    "Cannot extend turns.\n" +
                    "Cannot stack.",R.drawable.buff_swift, initial_turn, initial_stack, max_turn, max_stack);
        }

        @Override
        public void reapply(Unit unit) {
            unit.getModifiedStatus().setSpeed(unit.getModifiedStatus().getSpeed() + value  * getStack());
        }
    }
}

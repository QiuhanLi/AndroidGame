package ca.uwaterloo.cw.castlewar.Effect;

import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/3/24.
 */

abstract public class Debuff extends Effect {
    public Debuff(int id, String name,String description, int resource,  int turn, int stack, int maxTurn, int maxStack) {
        super(id, name,description, resource, turn, stack, maxTurn, maxStack);
    }

    @Override
    public void apply(Unit unit) {

    }


    public static class Coward extends Debuff {
        private static final Integer value = 3;
        private static final Integer initial_turn = 2;
        private static final Integer initial_stack = 1;
        private static final Integer max_turn = MAX_TURN;
        private static final Integer max_stack = 3;
        public Coward() {
            super(Id.Debuff.COWARD.ordinal(), "Coward", "Attack decreased by " + value.toString() + " per stack. " +
                    "Last "+ initial_turn.toString() + " turns. " +
                    "Can stack up to "+ max_stack + " times.",R.drawable.cross, initial_turn, initial_stack, max_turn, max_stack);
        }
        @Override
        public void reapply(Unit unit) {
            unit.getModifiedStatus().setAttack(unit.getModifiedStatus().getAttack() - value  * getStack());
        }
    }

    public static class Weak extends Debuff {
        private static final Integer value = 10;
        private static final Integer initial_turn = 1;
        private static final Integer initial_stack = 1;
        private static final Integer max_turn = MAX_TURN;
        private static final Integer max_stack = 2;

        public Weak() {
            super(Id.Debuff.WEAK.ordinal(), "Weak", "Defense decreased by " + value.toString() + " per stack. " +
                    "Last " + initial_turn.toString() + " turns. " +
                    "Can stack up to " + max_stack + " times.", R.drawable.cross, initial_turn, initial_stack, max_turn, max_stack);
        }

        @Override
        public void reapply(Unit unit) {
            unit.getModifiedStatus().setDefense(unit.getModifiedStatus().getDefense() - value * getStack());
        }
    }
}

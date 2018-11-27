package ca.uwaterloo.cw.castlewar.Effect;

/**
 * Created by harri on 2018/3/24.
 */

abstract public class Dot extends Effect {
    public Dot(int id, String name, String description, int resource, int turn, int stack, int maxTurn, int maxStack) {
        super(id, name, description, resource, turn, stack, maxTurn, maxStack);
    }
}

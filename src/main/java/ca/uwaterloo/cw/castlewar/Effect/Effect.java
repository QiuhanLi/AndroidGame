package ca.uwaterloo.cw.castlewar.Effect;

import ca.uwaterloo.cw.castlewar.Base.GameObject;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/3/24.
 */

abstract public class Effect extends GameObject {
    private int turn;
    private int stack;
    private int maxTurn;
    private int maxStack;
    public static final int MAX_TURN = 99;
    public static final int MAX_STACK = 99;

    public Effect(int id, String name,String description, int resource, int turn, int stack, int maxTurn, int maxStack) {
        super(id, name,description, resource);
        this.turn = turn;
        this.stack = stack;
        this.maxTurn = maxTurn;
        this.maxStack = maxStack;
        this.getSprite().setConfig(80,80,1);
    }

    // called for actual side effects like poison
    abstract public void apply(Unit unit);

    // called for status changes like brave
    abstract public void reapply(Unit unit);

    public void overApply(Effect effect) {
        // simply extend the time
        this.setTurn(this.turn + effect.turn);
        this.setStack(this.stack + effect.stack);
    }

    public void setTurn(int turnLeft) {
        this.turn = turnLeft;
        if (this.turn > this.maxTurn) this.turn = this.maxTurn;
    }

    public int getTurn() {
        return turn;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
        if (this.stack > this.maxStack) this.stack = this.maxStack;
    }

    public void reduceTurn() {
        this.turn--;
    }
}

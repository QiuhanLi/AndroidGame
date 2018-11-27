package ca.uwaterloo.cw.castlewar.Base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by harri on 2018/3/24.
 */

public class Status {
    private AtomicInteger hp = new AtomicInteger();
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private int move;
    private int minRange;
    private int maxRange;
    private int cost;

    public Status(int hp, int maxHp, int attack, int defense, int speed, int move, int minRange, int maxRange, int cost) {
        this.hp = new AtomicInteger(hp);
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.move = move;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.cost = cost;
    }

    public Status(int cost) {
        this.cost = cost;
    }

    public Status(Status status){
        this.hp = new AtomicInteger(status.hp.get());
        this.maxHp = status.maxHp;
        this.attack = status.attack;
        this.defense = status.defense;
        this.speed = status.speed;
        this.move = status.move;
        this.minRange = status.minRange;
        this.maxRange = status.maxRange;
        this.cost = status.cost;
    }

    public int getHp() {
        return hp.get();
    }

    public void setHp(int hp) {
        this.hp.set(hp);
        if (this.hp.get() > this.maxHp) this.hp.set(maxHp);
        else if (this.hp.get() < 0) this.hp.set(0);
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

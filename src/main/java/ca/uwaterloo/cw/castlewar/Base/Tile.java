package ca.uwaterloo.cw.castlewar.Base;

import ca.uwaterloo.cw.castlewar.Game.Terrain;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/3/17.
 */

public class Tile {
    public static final int SIZE = 100;
    private Unit unit;
    private Terrain.BattleField battleField;
    private int id;
    private int x;
    private int y;

    public Tile(int id, int x, int y, Terrain.BattleField battleField) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.battleField = battleField;
        this.unit = null;
    }

    public Terrain.BattleField getParent() {
        return battleField;
    }

    public int getParentId()
    {
        return battleField.getId();
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAvailable()
    {
        if (unit == null) return true;
        else return false;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }
}
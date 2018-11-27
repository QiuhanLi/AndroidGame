package ca.uwaterloo.cw.castlewar.Game;

import android.widget.TextView;

import ca.uwaterloo.cw.castlewar.Base.GameObject;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Base.System;
import ca.uwaterloo.cw.castlewar.Unit.Castle;
import ca.uwaterloo.cw.castlewar.Base.Tile;
import ca.uwaterloo.cw.castlewar.R;

/**
 * Created by harri on 2018/2/16.
 */

public class Terrain extends GameObject {
    private BattleField[] battleFields;
    private int battleFieldLength;
    private int battleFieldNum;
    private int battleFieldsWidth;
    private int castleLength;

    public static Terrain createTerrain(Id.Terrain terrain) {
        switch (terrain) {
            case SHORT_FIELD: return new ShortField();
            case MEDIUM_FIELD: return new MediumField();
            case LONG_FIELD: return new LongField();
            default: return null;
        }
    }

    // NOTE!!! battleFieldNum must be greater than 1
    public Terrain(int id, String name, String description, int resource, int battleFieldLength, int battleFieldNum) {
        super(id, name,description, resource);
        this.battleFieldNum = battleFieldNum;
        this.battleFieldLength = battleFieldLength;
        this.castleLength = Castle.SIZE;
        int extraLength = castleLength + battleFieldLength;
        this.battleFieldsWidth = battleFieldLength * battleFieldNum + 2 * castleLength;
        this.battleFields = new BattleField[battleFieldNum];
        for (int i = 0; i < battleFieldNum; ++i)
        {
            if (i == 0 || i == battleFieldNum - 1) battleFields[i] = new BattleField(i, extraLength, this);
            else battleFields[i] = new BattleField(i, battleFieldLength, this);
        }
        this.getSprite().setConfig(battleFieldsWidth, null, 2);
        this.getSprite().setY(System.getGroundLine() - 50);
    }

    public BattleField[] getBattleFields() {
        return battleFields;
    }

    public int getBattleFieldLength() {
        return battleFieldLength;
    }

    public int getBattleFieldNum() {
        return battleFieldNum;
    }

    public int getBattleFieldsWidth() {
        return battleFieldsWidth;
    }

    public BattleField[] getReversedBattlefield()
    {
        BattleField[] reverse = new BattleField[battleFieldNum];
        for (int i = battleFieldNum - 1; i >= 0; --i)
            reverse[i] = battleFields[battleFieldNum - i - 1];
        return reverse;
    }

    public static class ShortField extends Terrain {
        public ShortField() {
            super(Id.Terrain.SHORT_FIELD.ordinal(), "Short Field", "Three Sections", R.drawable.forest_ground, 500, 3);
        }
    }

    public static class MediumField extends Terrain {
        public MediumField() {
            super(Id.Terrain.MEDIUM_FIELD.ordinal(), "Medium Field", "Four Sections", R.drawable.forest_ground, 500, 4);
        }
    }

    public static class LongField extends Terrain {
        public LongField() {
            super(Id.Terrain.LONG_FIELD.ordinal(), "Long Field", "Five Sections", R.drawable.forest_ground, 500, 5);
        }
    }

    public class BattleField {
        private int id;
        private int length;
        private Tile[] tiles;
        private int tileNum;

        public BattleField(int id, int length, Terrain terrain) {
            this.id = id;
            this.length = length;
            this.tileNum = length / Tile.SIZE;
            this.tiles = new Tile[tileNum];
            for (int i = 0; i < length / Tile.SIZE; ++i)
            {
                if (id == 0) this.tiles[i] = new Tile(i,  i * Tile.SIZE, System.getGroundLine() - Tile.SIZE, this);
                else if (id == terrain.battleFieldNum - 1) this.tiles[i] = new Tile(i, id * (length - Castle.SIZE) + Castle.SIZE + i * Tile.SIZE, System.getGroundLine() - Tile.SIZE, this);
                else this.tiles[i] = new Tile(i, id * length + Castle.SIZE + i * Tile.SIZE, System.getGroundLine() - Tile.SIZE, this);
            }
        }

        public Tile[] getReversedTiles()
        {
            Tile[] reverse = new Tile[tiles.length];
            for (int i = tiles.length - 1; i >= 0; --i)
                reverse[i] = tiles[tiles.length - i - 1];
            return reverse;
        }

        public Tile findFirstAvailableTile(Id.Player player)
        {
            for (Tile tile : player == Id.Player.ONE ? tiles : getReversedTiles()){
                if (tile.isAvailable()){
                    return tile;
                }
            }
            return null;
        }

        public int getAvailableTileNum()
        {
            int num = 0;
            for (Tile tile : tiles) {
                if (tile.isAvailable()) {
                    num++;
                }
            }
            return num;
        }

        public int getId() {
            return id;
        }

        public int getLength() {
            return length;
        }

        public Tile[] getTiles() {
            return tiles;
        }
    }
}
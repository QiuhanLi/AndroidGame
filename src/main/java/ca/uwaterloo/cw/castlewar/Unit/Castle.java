package ca.uwaterloo.cw.castlewar.Unit;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import ca.uwaterloo.cw.castlewar.Base.Sprite;
import ca.uwaterloo.cw.castlewar.Base.Status;
import ca.uwaterloo.cw.castlewar.Base.Tile;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Base.System;
import ca.uwaterloo.cw.castlewar.R;

/**
 * Created by harri on 2018/2/14.
 */

public class Castle extends Unit {
    public static final int SIZE = 300;

    public Castle(int id, String name, String description, int resource, Status status) {
        super(id, name, description, resource, status,null,null, Id.Direction.RIGHT, Id.Attack.CLAW);
        this.getSprite().disableUnit();
        this.getSprite().addResources(null, null, resource);
        this.getSprite().setConfig(SIZE, null, 2);
        this.getSprite().getPortrait();
        this.getSprite().setY(System.getGroundLine() - getSprite().getPortrait().getHeight() + 20);
    }

    public static Castle createCastle(Id.Castle castle) {
        switch(castle) {
            case HOLY_CASTLE: return new HolyCastle();
            case EVIL_CASTLE: return new EvilCastle();
            default: return null;
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        Sprite sprite = getSprite();
        canvas.drawBitmap(sprite.getPortrait(), sprite.getX(), sprite.getY(), paint);

        // draw hp above chars
        Paint paintHp = new Paint();
        int healthWidth = 280;
        int interval = 10;
        int healthHeight = 30;
        int indent = 10;
        int left = sprite.getX() + indent;
        int right = left + healthWidth;
        int top = sprite.getY() - healthHeight - interval;
        int bottom = top + healthHeight;
        paintHp.setColor(Color.RED);
        canvas.drawRect(left, top, right, bottom, paintHp);
        paintHp.setColor(Color.GREEN);
        int currentHealthWidth = (int) (((float) getModifiedStatus().getHp() / (float)getModifiedStatus().getMaxHp()) * (float) healthWidth);
        right = left + currentHealthWidth;
        canvas.drawRect(left, top, right, bottom, paintHp);
    }

    public static class HolyCastle extends Castle {
        public HolyCastle() {
            super(Id.Castle.HOLY_CASTLE.ordinal(),"Holy Castle", "A holy and peaceful castle", R.drawable.holy_castle,
                    new Status(400, 400, 0, 30, 0,0 ,-1,-1,0));
        }
    }

    public static class EvilCastle extends Castle {
        public EvilCastle() {
            super(Id.Castle.EVIL_CASTLE.ordinal(),"Evil Castle", "An evil and fearful castle", R.drawable.evil_castle,
                    new Status(400, 400, 0, 10, 0,0 ,-1,-1,0));
        }
    }
}

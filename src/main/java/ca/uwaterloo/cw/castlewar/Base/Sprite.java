package ca.uwaterloo.cw.castlewar.Base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import ca.uwaterloo.cw.castlewar.Game.Combat;
import ca.uwaterloo.cw.castlewar.Game.Terrain;
import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Structure.Atomic;
import ca.uwaterloo.cw.castlewar.Structure.Id;

/**
 * Created by harri on 2018/3/2downsize.
 */

public class Sprite {
    private Atomic.Id<Id.Direction> indexFlow;
    private Atomic.Id<Id.Direction> direction;
    private Id.Direction initialDirection;
    private Integer width;
    private Integer height;
    private int downsize;
    private AtomicInteger x;
    private AtomicInteger y;
    private int portraitResource;
    private int moveResource;
    private int combatResource;
    private Bitmap portrait;
    private HashMap<Id.Direction, Bitmap> combat;
    private AtomicInteger moveIndex;
    private HashMap<Id.Direction, ArrayList<Bitmap>> move;
    private boolean isUnit;

    public Sprite(int portraitResource) {
        this.x = new AtomicInteger(0);
        this.y = new AtomicInteger(0);
        this.portraitResource = portraitResource;
        this.width = 100;
        this.height = 100;
        this.downsize = 1;
        this.isUnit = false;
        this.initialDirection = Id.Direction.RIGHT;
        this.indexFlow = new Atomic.Id<>(Id.Direction.RIGHT);
        this.direction = new Atomic.Id<>(Id.Direction.RIGHT);
    }

    public void clone(Sprite source){
        this.x.set(source.x.get());
        this.y.set(source.y.get());
        this.portrait = source.portrait;
        this.combat = source.combat;
        this.move = source.move;
        this.moveIndex = new AtomicInteger(0);
    }

    // set how big the bitmap should be
    // set what times should the original source be shrunk
    // negative number means no change
    public void setConfig(Integer width, Integer height, int downsize) {
        if (downsize > 0) this.downsize = downsize;
        if (width == null || width > 0) this.width = width;
        if (height == null || height > 0) this.height = height;
    }

    public void addResources(Integer portraitResource, Integer moveResource, Integer combatResource) {
        if (portraitResource != null) this.portraitResource = portraitResource;
        if (moveResource != null) this.moveResource = moveResource;
        if (combatResource != null) this.combatResource = combatResource;
    }

    // ALL initialization methods should NOT be called often
    // only once when initializing a new game
    // when generating new units, use copy moving image instead
    public void initializeAll(){
        initializePortrait();
        initializeMove();
        initializeCombat();
    }

    private void initializeCombat() {
        this.setConfig(500, 500, 1);
        this.combat = new HashMap<>();

        this.combat.put(initialDirection, System.scaleBitmap(this.combatResource, this.width, this.height, this.downsize));
        this.combat.put(initialDirection.getOpponent(), System.flipHorizontally(this.combat.get(initialDirection)));
    }

    private void initializeMove() {
        this.move = new HashMap<>();
        this.move.put(Id.Direction.LEFT, new ArrayList<Bitmap>());
        this.move.put(Id.Direction.RIGHT, new ArrayList<Bitmap>());
        this.setConfig(Tile.SIZE, Tile.SIZE, 1);
        int row = 8;
        int column = 12;
        Bitmap original = System.scaleBitmap(moveResource, null, null, 1);
        int width = original.getWidth() / column;
        int height = original.getHeight() / row;

        this.move.get(Id.Direction.LEFT).add(System.scaleBitmap(Bitmap.createBitmap(original, 0 * width, 1 * height, width, height), this.width, this.height, this.downsize));
        this.move.get(Id.Direction.LEFT).add(System.scaleBitmap(Bitmap.createBitmap(original, 1 * width, 1 * height,  width, height), this.width, this.height, this.downsize));
        this.move.get(Id.Direction.LEFT).add(System.scaleBitmap(Bitmap.createBitmap(original, 2 * width, 1 * height, width, height), this.width, this.height, this.downsize));

        this.move.get(Id.Direction.RIGHT).add(System.scaleBitmap(Bitmap.createBitmap(original, 0 * width, 2 * height, width, height), this.width, this.height, this.downsize));
        this.move.get(Id.Direction.RIGHT).add(System.scaleBitmap(Bitmap.createBitmap(original, 1 * width, 2 * height,  width, height), this.width, this.height, this.downsize));
        this.move.get(Id.Direction.RIGHT).add(System.scaleBitmap(Bitmap.createBitmap(original, 2 * width, 2  * height, width, height), this.width, this.height, this.downsize));
    }

    // extract current move bitmap
    public Bitmap getBitmap() {
        if (this.move == null) return getPortrait();
        return move.get(direction.get()).get(this.moveIndex.get());
    }

    private void initializePortrait(){
        if (isUnit) {
            int row = 2;
            int column = 4;
            setConfig(400, null, 1);
            Bitmap original = System.scaleBitmap(this.portraitResource, this.width, this.height, 1);
            int width = original.getWidth() / column;
            int height = original.getHeight() / row;
            this.portrait = Bitmap.createBitmap(original, 0, 0, width, height);
        } else {
            this.portrait = System.scaleBitmap(this.portraitResource, this.width,  this.height, this.downsize);
        }
    }

    public Bitmap getPortrait() {
        if (portrait == null) initializePortrait();
        return portrait;
    }

    public Bitmap getCombat(Id.Direction direction) {
        if (combat == null) initializeCombat();
        return combat.get(direction);
    }

    public void freeAll() {
        if (combat != null) {
            this.combat.get(Id.Direction.LEFT).recycle();
            this.combat.get(Id.Direction.RIGHT).recycle();
            this.combat = null;
        }

        if (move != null) {
            for (Bitmap bitmap : move.get(Id.Direction.LEFT)) {
                bitmap.recycle();
            }
            for (Bitmap bitmap : move.get(Id.Direction.RIGHT)) {
                bitmap.recycle();
            }
            this.move = null;
        }

        if (this.portrait != null) {
            this.portrait.recycle();
            this.portrait = null;
        }
    }

    public int getY() {
        return y.get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public int getX() {
        return this.x.get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void switchBitmap() {
        int currentIndex = this.moveIndex.get();
        int size = this.move.get(this.direction.get()).size();

        if (size <= 1) return;
        if (currentIndex == 0)
            indexFlow.set(Id.Direction.RIGHT);
        else if (currentIndex == size - 1)
            indexFlow.set(Id.Direction.LEFT);

        if (indexFlow.get() == Id.Direction.LEFT)
            currentIndex -= 1;
        else
            currentIndex += 1;

        this.moveIndex.set(currentIndex);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(portrait, x.get(), y.get(), paint);
    }

    public void setInitialDirection(Id.Direction initialDirection) {
        this.initialDirection = initialDirection;
    }

    public void enableUnit() {
        this.isUnit = true;
    }

    public void disableUnit() {
        this.isUnit = false;
    }

    public void setDirection(Id.Direction direction) {
        this.direction.set(direction);
    }

    public Id.Direction getDirection() {
        return this.direction.get();
    }

    public static class Target extends Sprite {
        private AtomicBoolean visible;
        private Tile moveTile;
        private int moveSpeed;

        public Target() {
            super(R.drawable.target);
            setY(System.getGroundLine() - getPortrait().getHeight());
            this.moveSpeed = 30;
            this.visible = new AtomicBoolean(false);
        }

        public void move() {
            if (moveTile == null) return;
            if (moveTile.getX() > getX()) {
                // finish move
                if (getX() + moveSpeed > moveTile.getX()) setX(moveTile.getX());
                else setX(getX() + moveSpeed);
            } else if (moveTile.getX() < getX()) {
                // finish move
                if (getX() - moveSpeed < moveTile.getX()) setX(moveTile.getX());
                else setX(getX() - moveSpeed);
            } else {
                // if not just initialized
                // finish move
                moveTile = null;
            }
        }
        public Tile getMoveTile() {
            return moveTile;
        }

        public void setMoveTile(Tile moveTile) {
            this.moveTile = moveTile;
        }

        public boolean isVisible() {
            return visible.get();
        }

        public void setVisible(boolean visible) {
            this.visible.set(visible);
        }
    }

}

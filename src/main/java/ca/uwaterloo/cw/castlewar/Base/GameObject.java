package ca.uwaterloo.cw.castlewar.Base;

import java.lang.String;

/**
 * Created by harri on 2018/2/14.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by harri on 2018/2/14.
 */

abstract public class GameObject {
    private int id;
    private String name;
    private String description;
    private Sprite sprite;
    private Status baseStatus;
    private Status modifiedStatus;

    public GameObject(int id, String name, String description, int resource) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.sprite = new Sprite(resource);
    }

    public GameObject(int id, String name, String description, int resource, Status status) {
        this(id, name, description, resource);
        this.baseStatus = status;
        this.modifiedStatus = new Status(status);
        this.getSprite().setConfig(100, 100, 1);
    }

    public String getDescription()
    {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Status getBaseStatus(){
        return baseStatus;
    }

    public Bitmap getPortrait() {
        return this.sprite.getPortrait();
    }

    public void draw (Canvas canvas, Paint paint) {
        canvas.drawBitmap(sprite.getPortrait(), sprite.getX(), sprite.getY(), paint);
    }

    public Status getModifiedStatus() {
        return modifiedStatus;
    }

    public void setModifiedStatus(Status status) {
        this.modifiedStatus = status;
    }
}
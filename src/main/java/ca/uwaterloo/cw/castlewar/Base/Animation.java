package ca.uwaterloo.cw.castlewar.Base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import ca.uwaterloo.cw.castlewar.Game.GameManager;
import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Structure.CombatView;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/3/25.
 */

public class Animation extends ValueAnimator {
    private static final int BASE_VALUE_PER_SECOND = 15;
    private static final int BASE_FRAME_PER_SECOND = 20;
    private static AtomicInteger VALUE_PER_SECOND = new AtomicInteger(BASE_VALUE_PER_SECOND);  // this is speed of text
    private static AtomicInteger FRAME_PER_SECOND = new AtomicInteger(BASE_FRAME_PER_SECOND); // speed of animation
    private static final long MILISECOND = 1000;
    private AtomicBoolean hasStart = new AtomicBoolean(false);
    private static final HashMap<Id.CombatRole, ArrayList<Bitmap>> animations = new HashMap<>();
    private static int attackerResource;
    private static int defenderResource;
    private static boolean isSkip = false;

    public Animation() {
        super();
        this.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                GameManager.instance().removeAnimation(Animation.this);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                GameManager.instance().addAnimation(Animation.this);
                Animation.this.hasStart.set(true);
            }
        });
    }

    public static void initializeAnimations(Id.Attack attackerType, Id.Attack defenderType) {
        animations.put(Id.CombatRole.ATTACKER, new ArrayList<Bitmap>());
        animations.put(Id.CombatRole.DEFENDER, new ArrayList<Bitmap>());
        initializeAnimation(attackerType, Id.CombatRole.ATTACKER);
        initializeAnimation(defenderType, Id.CombatRole.DEFENDER);
    }

    private static void initializeAnimation(Id.Attack attack, Id.CombatRole role) {
        switch (attack) {
            case DARK_RUNE: readAnimation(R.drawable.effect_dark_rune, R.raw.dark_rune,5, 5,1, role); break;
            case DARK_SLASH: readAnimation(R.drawable.effect_dark_slash, R.raw.dark_slash, 3, 5, 4, role); break;
            case DARK_BALL: readAnimation(R.drawable.effect_dark_ball, R.raw.dark_ball,2, 5, 0, role); break;
            case ICE_FALL: readAnimation(R.drawable.effect_ice_fall, R.raw.ice_blast, 4, 5, 4, role); break;
            case CLAW: readAnimation(R.drawable.effect_claw, R.raw.claw, 4, 5, 3, role); break;
            case THUNDER_SLASH: readAnimation(R.drawable.effect_slash_thunder, R.raw.thunder_slash, 2, 5, 4, role); break;
            case SLASH_FIRE: readAnimation(R.drawable.effect_slash_fire, R.raw.fire_slash,3, 5, 0, role); break;
            case ARROW: readAnimation(R.drawable.effect_arrow, R.raw.arrow, 3, 5, 2, role); break;
            case GREAT_SLASH: readAnimation(R.drawable.effect_great_slash, R.raw.great_slash, 3,5,3,role); break;
            case MAGIC_SLASH: readAnimation(R.drawable.effect_magic_slash, R.raw.magic_slash, 5,5,3,role); break;
        }
    }

    private static void readAnimation(int resource, int music, int row, int column, int leftover, Id.CombatRole role) {
        Bitmap original = System.scaleBitmap(resource, null, null, 1);
        int width = original.getWidth() / column;
        int height = original.getHeight() / (leftover == 0 ? row : row + 1);

        for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                animations.get(role).add(Bitmap.createBitmap(original, j * width, i * height, width, height));
            }
        }

        for (int i = 0; i < leftover; ++i) {
            animations.get(role).add(Bitmap.createBitmap(original, i * width, row * height, width, height));
        }

        if (role == Id.CombatRole.ATTACKER) attackerResource = music;
        else defenderResource = music;
    }

    public static void attackEffect(final Id.CombatRole role, final ImageView view) {
        if (isSkip) return;
        // play animation
        final ArrayList<Bitmap> effects = animations.get(role);
        final Animation animation = new Animation();
        int frame = effects.size();
        animation.setIntValues(0, frame - 1);
        long duration = frame * MILISECOND / FRAME_PER_SECOND.get();
        animation.setDuration(duration);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int position = (int) valueAnimator.getAnimatedValue();
                view.setImageBitmap(effects.get(position));
            }
        });
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setImageBitmap(null);
                if (role == Id.CombatRole.ATTACKER) System.createMedia(attackerResource, false).start();
                else System.createMedia(defenderResource, false).start();
            }
        });
        System.runOnUi(new Runnable() {
            @Override
            public void run() {
                animation.start();
            }
        });
        animation.waitForStart();
    }

    public static void healthEffect(int before, final int after, final CombatView combatView) {
        if (isSkip) return;
        final Animation animation = new Animation();
        animation.setIntValues(before, after);
        Integer delta = Math.abs(before - after);
        long duration = delta * MILISECOND / VALUE_PER_SECOND.get();
        animation.setDuration(duration);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int hp = (int) valueAnimator.getAnimatedValue();
                combatView.getHp().setText(Integer.toString(hp));
                combatView.getHealthBar().setProgress(hp);
            }
        });
        Animation.waitForAll();
        System.runOnUi(new Runnable() {
            @Override
            public void run() {
                animation.start();
            }
        });
        animation.waitForStart();
    }

    public void waitForStart() {
        while (!hasStart.get()) {
            if (!GameManager.instance().isGameActive()) return;
            // busy-waiting
            // bad!!!
        }
    }

    public static void waitForAll() {
        while (!GameManager.instance().isAnimationEmpty()) {
            if (!GameManager.instance().isGameActive()) return;
            // busy-waiting
            // bad!!!
        }
    }

    public static void setSpeed(Id.Speed gameSpeed) {
        switch(gameSpeed) {
            case NORMAL: {
                FRAME_PER_SECOND.set(BASE_FRAME_PER_SECOND);
                VALUE_PER_SECOND.set(BASE_VALUE_PER_SECOND);
                isSkip = false;
                break;
            }
            case DOUBLE: {
                FRAME_PER_SECOND.set(BASE_FRAME_PER_SECOND * 2);
                VALUE_PER_SECOND.set(BASE_VALUE_PER_SECOND * 2);
                isSkip = false;
                break;
            }
            case TRIPLE: {
                FRAME_PER_SECOND.set(BASE_FRAME_PER_SECOND * 2);
                VALUE_PER_SECOND.set(BASE_VALUE_PER_SECOND * 2);
                isSkip = true;
                break;
            }
        }
    }

    public static class AnimateColor {
        private static AtomicInteger[] rgb;
        private static int index;
        private static boolean isIncreasing = true;
        private static final int changeSpeed = 15;

        static  {
            index = 1;
            rgb = new AtomicInteger[3];
            rgb[0] = new AtomicInteger(255);
            rgb[1] = new AtomicInteger(0);
            rgb[2] = new AtomicInteger(0);
        }

        public static void animate() {
            if (isIncreasing) {
                int value = rgb[index].get() + changeSpeed;
                if (value > 255) {
                    rgb[index].set(255);
                    index--;
                    if (index < 0) index = 2;
                    isIncreasing = false;
                } else{
                    rgb[index].set(value);
                }
            } else {
                int value = rgb[index].get() - changeSpeed;
                if (value < 0) {
                    rgb[index].set(0);
                    index--;
                    if (index < 0) index = 2;
                    isIncreasing = true;
                } else {
                    rgb[index].set(value);
                }
            }
        }


        public static int getAnimateColor() {
            return Color.argb(255, rgb[0].get(),rgb[1].get(),rgb[2].get());
        }
    }
}

package ca.uwaterloo.cw.castlewar.Structure;

import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by harri on 2018/3/25.
 */

public class CombatView {
    private TextView hp;
    private TextView maxHp;
    private ImageView effects;
    private ProgressBar healthBar;
    private ImageView unit;
    private ImageView animation;
    private TextView title;

    public CombatView(TextView hp, TextView maxHp, ImageView effects, ProgressBar healthBar, ImageView unit, ImageView animation, TextView title) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.effects = effects;
        this.healthBar = healthBar;
        this.unit = unit;
        this.animation = animation;
        this.title = title;
    }

    public TextView getHp() {
        return hp;
    }

    public TextView getMaxHp() {
        return maxHp;
    }

    public ImageView getEffects() {
        return effects;
    }

    public ProgressBar getHealthBar() {
        return healthBar;
    }

    public ImageView getUnit() {
        return unit;
    }

    public ImageView getAnimation() {
        return animation;
    }

    public TextView getTitle() {
        return title;
    }
}

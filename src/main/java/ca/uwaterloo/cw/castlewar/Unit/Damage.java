package ca.uwaterloo.cw.castlewar.Unit;

/**
 * Created by harri on 2018/3/25.
 */

// only Ability can modify this
public class Damage {
    private float attack;
    private float attackMultiplier;
    private float defense;
    private float defenseMultiplier;
    private float pircing;
    private float resistance;

    public Damage() {
        this.attack = 0;
        this.attackMultiplier = 1;
        this.defense = 0;
        this.defenseMultiplier = 1;
        this.resistance = 1;
        this.pircing = 0;
    }

    // formula is (attack - defense) * resistance
    public int calculate() {
        float damage = (attack * attackMultiplier - defense * defenseMultiplier) * resistance;
        if (damage <= 0) damage = 0;
        damage += (int) pircing * resistance;
        if (damage <= 0) damage = 1;
        return (int) damage;
    }

    public void modifyPircing(float offset) {
        this.pircing += offset;
    }

    public void modifyAttack(float offset) {
        this.attack += offset;
    }

    public void modifyAttackMultiplier(float offset) {
        this.attackMultiplier *= offset;
    }

    public void modifydefenseMultiplier(float offset) {
        this.defenseMultiplier *= offset;
    }

    public void modifyDefense(float offset) {
        this.defense += offset;
    }
    public void modifyResistance(float offset) {
        this.resistance *= offset;
    }

    public void resetAttack() {
        this.attack = 0;
    }
    public void resetDefense() {
        this.defense = 0;
    }
    public void resetAttackMultiplier() {
        this.attackMultiplier = 1;
    }
    public void resetDefenseMultiplier() {
        this.defenseMultiplier = 1;
    }
    public void resetPircing() {
        this.pircing = 0;
    }
    public void resetResistance() {
        this.resistance = 1;
    }
}

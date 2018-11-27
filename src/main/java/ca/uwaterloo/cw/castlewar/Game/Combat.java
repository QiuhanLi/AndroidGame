package ca.uwaterloo.cw.castlewar.Game;

import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import ca.uwaterloo.cw.castlewar.Base.Animation;
import ca.uwaterloo.cw.castlewar.Base.System;
import ca.uwaterloo.cw.castlewar.R;
import ca.uwaterloo.cw.castlewar.Structure.Atomic;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Unit.Unit;

/**
 * Created by harri on 2018/3/25.
 */


// combat environment
public class Combat {
    // combat environment
    private final int MAX_TURN = 5;
    private int currentTurn;
    private HashMap<Id.CombatRole, Unit> units = new HashMap<>(2);
    private ArrayList<Id.CombatRole> turns = new ArrayList<>(MAX_TURN);
    private int distance;
    private Id.CombatStage combatStage;
    private Unit activeUnit;
    private Unit passiveUnit;
    private MediaPlayer background;
    private AtomicBoolean isFinish = new AtomicBoolean(false);

    public Combat(Unit attacker, Unit defender, Id.Speed gameSpeed) {
        this.background = MediaPlayer.create(System.getMainActivity(), R.raw.combat);
        this.background.setLooping(true);
        if (gameSpeed != Id.Speed.TRIPLE) this.background.start();
        this.currentTurn = 0;
        this.units.put(Id.CombatRole.ATTACKER, attacker);
        this.units.put(Id.CombatRole.DEFENDER, defender);
        this.distance = Math.abs(attacker.getCurrentTile().getParentId() - defender.getCurrentTile().getParentId());
        this.combatStage = Id.CombatStage.INITIAL;
        Animation.initializeAnimations(attacker.getAttack(), defender.getAttack());

        // initialize turns
        for (int i = 0; i < MAX_TURN - 1; ++i) {
            turns.add(i % 2 == 0 ? Id.CombatRole.ATTACKER : Id.CombatRole.DEFENDER);
        }
        if (attacker.getModifiedStatus().getSpeed() > defender.getModifiedStatus().getSpeed() + 5) {
            turns.add(Id.CombatRole.ATTACKER);
        } else if (attacker.getModifiedStatus().getSpeed() + 5 < defender.getModifiedStatus().getSpeed()) {
            turns.add(Id.CombatRole.DEFENDER);
        }

        // cancel defender's turn if defender cannot fight back
        // Note: Castle'turns will be canceled because castle cannot reach any unit with -1 range
        if (defender.getModifiedStatus().getMaxRange() < distance ||
                defender.getModifiedStatus().getMinRange() > distance) {
            Iterator<Id.CombatRole> iterator = turns.iterator();
            while (iterator.hasNext()){
                Id.CombatRole holder = iterator.next();
                if (holder == Id.CombatRole.DEFENDER){
                    iterator.remove();
                }
            }
        }
    }

    // return result
    public void fight() {
        switch(combatStage){
            case INITIAL:
                // set up before combat
                if (currentTurn >= turns.size()) {
                    end();
                    return;
                }

                // decide whose turn
                activeUnit = units.get(turns.get(currentTurn));
                passiveUnit = activeUnit.getOpponent();
                activeUnit.setRoundRole(Id.RoundRole.ACTIVE);
                passiveUnit.setRoundRole(Id.RoundRole.PASSIVE);

                combatStage = Id.CombatStage.START;
                break;
            case START:
                activeUnit.startTurn();
                passiveUnit.startTurn();
                combatStage = Id.CombatStage.FIGHT;
                break;
            case FIGHT:
                activeUnit.attack();

                passiveUnit.defend();

                passiveUnit.takeDamage();
                combatStage = Id.CombatStage.END;
                break;
            case END:
                activeUnit.endTurn();
                passiveUnit.endTurn();
                combatStage = Id.CombatStage.FINAL;
                break;
            case FINAL:
                this.currentTurn++;
                combatStage = Id.CombatStage.INITIAL;
                break;
        }
        // check death or turn number
        if (activeUnit.isDead() || passiveUnit.isDead())  {
            end();
            return;
        }
    }

    private void end() {
        this.units.get(Id.CombatRole.ATTACKER).setCombatView(null);
        this.units.get(Id.CombatRole.DEFENDER).setCombatView(null);
        this.isFinish.set(true);
    }

    public void stopMusic() {
        if (background != null) {
            this.background.stop();
            this.background.release();
            this.background = null;
        }
    }

    public void pauseMusic() {
        if (background != null) {
            this.background.pause();
        }
    }

    public void resumeMusic() {
        if (background != null) {
            this.background.start();
        }
    }

    public boolean isFinish() {
        return isFinish.get();
    }

    public void setFinish(boolean finish) {
        this.isFinish.set(finish);
    }
}

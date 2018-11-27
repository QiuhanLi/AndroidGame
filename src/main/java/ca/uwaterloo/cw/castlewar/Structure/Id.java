package ca.uwaterloo.cw.castlewar.Structure;

/**
 * Created by harri on 2018/2/24.
 */

abstract public class Id {
    public enum Lawful {
        // Lawful Units
        SWORDMAN, ARCHER, ICEMAGE, PRINCE, GODDESS

    }

    public enum Chaotic {
        // Chaotic Units
        MINOTAUR, BAT, GHOST, IMP, DARKLORD
    }

    public enum Castle {
        // Castle Units
        HOLY_CASTLE, EVIL_CASTLE,
    }

    public enum Item {
        // Potion item
        HP_POTION, ATTACK_POTION, DEFENSE_POTION, SPEED_POTION,
    }

    public enum Buff {
        BRAVE, TOUGH, SWIFT,
    }

    public enum Debuff {
        COWARD, WEAK,
    }

    public enum Level {
        ONE_ONE, ONE_TWO, ONE_THREE, ONE_FOUR, ONE_FIVE, ONE_SIX,
    }

    public enum Terrain {
        SHORT_FIELD, MEDIUM_FIELD, LONG_FIELD
    }

    public enum GameState {
        PREPARE, MOVING, COMBAT
    }

    public enum Thread {
        DATA, SCREEN, ANIME
    }

    public enum CombatRole {
        ATTACKER, DEFENDER;
    }

    public enum RoundRole {
        ACTIVE, PASSIVE
    }

    public enum CombatStage {
        INITIAL, START, FIGHT, END, FINAL
    }

    public enum Direction {
        LEFT, RIGHT;

        public Id.Direction getOpponent() {
            if (this == LEFT) {
                return RIGHT;
            } else {
                return LEFT;
            }
        }
    }

    public enum Player {
        ONE, TWO;

        public Id.Player getOpponent() {
            if (this == ONE) {
                return TWO;
            } else {
                return ONE;
            }
        }
    }

    public enum Attack {
        SLASH_FIRE, CLAW, DARK_RUNE, DARK_SLASH, DARK_BALL, ARROW, ICE_FALL, THUNDER_SLASH, GREAT_SLASH, MAGIC_SLASH
    }

    public enum Speed {
        NORMAL, DOUBLE, TRIPLE
    }
}

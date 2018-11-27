/*

package ca.uwaterloo.cw.castlewar.Base;

import java.util.ArrayList;
import java.util.HashMap;

import ca.uwaterloo.cw.castlewar.Item.Coin;
import ca.uwaterloo.cw.castlewar.Item.Item;
import ca.uwaterloo.cw.castlewar.Item.Potion;
import ca.uwaterloo.cw.castlewar.Structure.Id;
import ca.uwaterloo.cw.castlewar.Unit.Ability;
import ca.uwaterloo.cw.castlewar.Unit.Chaotic;
import ca.uwaterloo.cw.castlewar.Unit.Lawful;
import ca.uwaterloo.cw.castlewar.Unit.Unit;


 * Created by harrison33 on 2018/2/3.


public class User {
    // temporarily store levels
    private static ArrayList<Unit> lawfuls;
    private static ArrayList<Unit> chaotics;
    private static ArrayList<Item> potions;
    private static ArrayList<Ability> abilities;
    private static final Coin COIN = new Coin();

    public static void initialize() {
        lawfuls = Lawful.getAllLawful();
        chaotics = Chaotic.getAllChaotic();
        potions = Potion.getAllPotion();
        abilities = Ability.getAllAbility();
    }

    public static ArrayList<Unit> currentLawfuls() {
        return new ArrayList<>(lawfuls);
    }

    public static ArrayList<Unit> currentChaotics() {
        return new ArrayList<>(chaotics);
    }

    public static ArrayList<Item> currentPotions() {
        return new ArrayList<>(potions);
    }

    public static ArrayList<Ability> currentAbilities() {
        return new ArrayList<>(abilities);
    }

    public static Coin getCOIN() {
        return COIN;
    }
}
*/
package game.items.weaponry;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.UserAttributes;

import java.util.Random;

/**
 * Abstract class representing items that can be used as a weapon.
 * <p>
 * This class provides the basic functionality for weapon items, including managing damage, hit rate, and
 * the ability to check and toggle the weapon's portability based on the actor's strength.
 * </p>
 */
public abstract class WeaponItem extends Item implements Weapon {

    // Default damage multiplier for weapons
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    // The base damage dealt by this weapon
    private int damage;

    // The probability of hitting the target (as a percentage)
    private int hitRate;

    // The verb describing the weapon's attack action (e.g., "hits", "zaps")
    private final String verb;

    // Multiplier for the weapon's damage
    private float damageMultiplier;

    /**
     * Constructor for a weapon item.
     *
     * @param name        Name of the item.
     * @param displayChar Character to use for display when the item is on the ground.
     * @param damage      Amount of damage this weapon does.
     * @param verb        Verb to use for this weapon (e.g., "hits", "zaps").
     * @param hitRate     The probability (in percentage) of hitting the target.
     */

    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    /**
     * Performs an attack with this weapon.
     * <p>
     * The attack has a chance to miss based on the weapon's hit rate. If the attack is successful, the
     * target receives damage calculated by multiplying the base damage by the damage multiplier.
     * </p>
     *
     * @param attacker The actor performing the attack.
     * @param target   The actor being attacked.
     * @param map      The map on which the attack takes place.
     * @return A description of the attack result.
     */

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        // Determine if the attack hits based on the hit rate
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        // Calculate and apply the damage to the target
        target.hurt(Math.round(damage * damageMultiplier));
        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

    /**
     * Checks the weapon's strength and toggles its portability if the strength requirement is met.
     * <p>
     * This method is used to ensure that only actors with sufficient strength can carry the weapon.
     * If the strength requirement is met and the weapon is not already portable, it will be made portable.
     * </p>
     *
     * @param currentLocation The location where the check is performed.
     * @param strengthNeeded  The strength required to make the weapon portable.
     */

    public void checkStrength(Location currentLocation, int strengthNeeded) {
        // Check if there is an actor at the current location
        if (currentLocation.containsAnActor()) {
            Actor actor = currentLocation.getActor();

            // Check if the actor has the required strength attribute and if the weapon is not portable
            if (actor.hasAttribute(UserAttributes.STRENGTH) &&
                    actor.getAttribute(UserAttributes.STRENGTH) >= strengthNeeded && !this.portable) {
                // Toggle the weapon's portability
                this.togglePortability();
            }
        }
    }
}

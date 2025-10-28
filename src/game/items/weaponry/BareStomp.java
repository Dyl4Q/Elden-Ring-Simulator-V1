package game.items.weaponry;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utilities.Status;
import game.terrain.FlamingTerrain;

import java.util.Random;

/**
 * A class representing a Bare Stomp weapon, which is an intrinsic weapon that allows an actor to stomp
 * on a target, with a chance to cause an explosive shockwave in surrounding areas.
 * <p>
 * The Bare Stomp weapon can deal damage to a target and has a 10% chance of triggering an explosion effect,
 * which damages adjacent actors and potentially transforms the terrain.
 * </p>
 *
 * Created by:
 * @author [Your Name]
 * Modified by:
 *
 */

public class BareStomp extends IntrinsicWeapon {

    // Constant for the damage caused by the explosion effect
    private static final int DMG_EXPLODE = 50;

    /**
     * Constructor for the Bare Stomp weapon.
     * Initializes the Bare Stomp with a base damage and hit rate.
     */

    public BareStomp() {
        super(100, "stomps", 5);
    }

    /**
     * Performs the attack with the Bare Stomp weapon.
     * <p>
     * This method determines if the attack hits based on the weapon's hit rate. If the attack is successful,
     * it deals damage to the target. There is also a 10% chance that the attack will trigger an explosion effect,
     * which damages nearby actors and affects the terrain.
     * </p>
     *
     * @param attacker The actor performing the attack.
     * @param target The actor being attacked.
     * @param map The game map where the attack takes place.
     * @return A description of the result of the attack, including any explosion effects.
     */

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        // Determine if the attack hits based on the hit rate
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        // Deal damage to the target
        target.hurt(this.damage);
        String result = String.format("%s %s %s for %d damage", attacker, verb, target, damage);

        // 10% chance for explosion effect
        if (rand.nextInt(100) < 10) {
            result += "\n" + activateExplosion(attacker, map);
        }

        return result;
    }

    /**
     * Triggers the explosion effect around the attacker.
     * <p>
     * This method causes damage to any actor in adjacent locations and transforms the terrain to FlamingTerrain
     * if the current terrain has the burning capability.
     * </p>
     *
     * @param attacker The actor performing the attack.
     * @param map The game map where the explosion occurs.
     * @return A description of the result of the explosion.
     */

    private String activateExplosion(Actor attacker, GameMap map) {
        String result = "Furnace Golem's stomp attack results in a shockwave in the surrounding environment.";
        Location golemLocation = map.locationOf(attacker);

        // Iterate through all adjacent locations (exits)
        for (Exit exit : golemLocation.getExits()) {
            Location nearbyLocation = exit.getDestination();

            // Damage any actor in the adjacent location
            if (nearbyLocation.containsAnActor()) {
                Actor nearbyActor = nearbyLocation.getActor();
                if (nearbyActor != attacker) {
                    nearbyActor.hurt(DMG_EXPLODE);
                }
            }

            // Transform the terrain to FlamingTerrain if it has the burning capability
            if (nearbyLocation.getGround().hasCapability(Status.IS_BURNING)) {
                nearbyLocation.setGround(new FlamingTerrain(nearbyLocation.getGround()));
            }
        }

        return result;
    }
}

package game.items.weaponry;

import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing a Great Knife weapon, which is a weapon item that has a specific damage value,
 * a hit rate, and performs additional functionality on each tick.
 * <p>
 * The Great Knife is not portable, and it checks its strength at each tick to ensure it remains in
 * proper condition for use.
 * </p>
 *
 * Created by:
 * @author [Your Name]
 * Modified by:
 *
 */
public class GreatKnife extends WeaponItem {

    /**
     * Constructor for the Great Knife.
     * <p>
     * Initializes the Great Knife with a name, display character, damage value, a verb for the attack,
     * and a hit rate. Sets the weapon as non-portable.
     * </p>
     */

    public GreatKnife() {
        super("Great Knife", '†', 75, "verb", 60);
        this.portable = false;
    }

    /**
     * Performs a check on the weapon's strength each turn.
     * <p>
     * This method is called every tick to ensure the Great Knife remains in proper condition.
     * It delegates to the superclass method to perform the strength check with a specified threshold.
     * </p>
     *
     * @param currentLocation The location where the Great Knife is currently situated.
     */

    @Override
    public void tick(Location currentLocation) {
        super.checkStrength(currentLocation, 5);
    }
}

package game.items.weaponry;

import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing a Short Sword weapon, which is a weapon item with specific damage, hit rate,
 * and additional functionality for maintaining its strength.
 * <p>
 * The Short Sword is not portable, and it checks its strength at each tick to ensure it remains
 * effective for use.
 * </p>
 *
 * Created by:
 * @author [Your Name]
 * Modified by:
 *
 */
public class ShortSword extends WeaponItem {

    /**
     * Constructor for the Short Sword.
     * <p>
     * Initializes the Short Sword with a name, display character, damage value, a verb for the attack,
     * and a hit rate. Sets the weapon as non-portable.
     * </p>
     */

    public ShortSword() {
        super("Short Sword", '!', 100, "slashes", 75);
        this.portable = false;
    }

    /**
     * Performs a check on the weapon's strength each turn.
     * <p>
     * This method is called every tick to ensure the Short Sword remains in proper condition. It
     * delegates to the superclass method to perform the strength check with a specified threshold.
     * </p>
     *
     * @param currentLocation The location where the Short Sword is currently situated.
     */

    @Override
    public void tick(Location currentLocation) {
        super.checkStrength(currentLocation, 10);
    }
}

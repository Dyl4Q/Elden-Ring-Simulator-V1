package game.items.edibles;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A class representing a Flask of Healing, an edible item that restores health to an actor when consumed.
 * <p>
 * This item has a limited number of charges. Each time it is consumed, it restores 150 health points to the actor
 * and reduces the number of charges by 1. If the item is empty, it cannot restore health and the appropriate message
 * is returned.
 * </p>
 *
 * Created by:
 * @author [Your Name]
 * Modified by:
 *
 */
public class FlaskOfHealing extends EdibleItem {

    /**
     * Constructor for the Flask of Healing.
     * Initializes the item with a name, display character, initial number of charges, and consumption text.
     */

    public FlaskOfHealing() {
        super("Flask of Healing", 'u', 5, "is healed");
    }

    /**
     * Consumes the Flask of Healing, restoring health to the actor if there are remaining charges.
     * <p>
     * This method first invokes the `consume` method from the superclass to handle charge depletion and
     * return the appropriate message if the item is empty. If the item is not empty, it restores 150 health points
     * to the actor and then returns the consumption message.
     * </p>
     *
     * @param actor The actor consuming the Flask of Healing.
     * @return a message indicating the result of the consumption, including the health restoration if applicable.
     */

    @Override
    public String consume(Actor actor) {
        // Get the consumption message from the superclass method
        String msg = super.consume(actor);

        // If the item is empty, return the empty message
        if (msg.equals(name + " is empty")) {
            return msg;
        }

        // Restore health to the actor
        actor.heal(150);

        // Return the consumption message including the healing effect
        return msg;
    }
}

package game.items.edibles;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * A class representing a Flask of Rejuvenation, an edible item that restores mana to an actor when consumed.
 * <p>
 * This item has a limited number of charges. Each time it is consumed, it restores 100 mana points to the actor
 * and reduces the number of charges by 1. If the item is empty, it cannot restore mana and the appropriate message
 * is returned.
 * </p>
 *
 * Created by:
 * @author [Your Name]
 * Modified by:
 *
 */
public class FlaskOfRejuvenation extends EdibleItem {

    /**
     * Constructor for the Flask of Rejuvenation.
     * Initializes the item with a name, display character, initial number of charges, and consumption text.
     */
    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', 3, "is healed");
    }

    /**
     * Consumes the Flask of Rejuvenation, restoring mana to the actor if there are remaining charges.
     * <p>
     * This method first invokes the `consume` method from the superclass to handle charge depletion and
     * return the appropriate message if the item is empty. If the item is not empty, it restores 100 mana points
     * to the actor and then returns the consumption message.
     * </p>
     *
     * @param actor The actor consuming the Flask of Rejuvenation.
     * @return a message indicating the result of the consumption, including the mana restoration if applicable.
     */


    @Override
    public String consume(Actor actor) {
        // Get the consumption message from the superclass method
        String msg = super.consume(actor);

        // If the item is empty, return the empty message
        if (msg.equals(name + " is empty")) {
            return msg;
        }

        // Restore mana to the actor
        actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 100);

        // Return the consumption message including the mana restoration effect
        return msg;
    }
}

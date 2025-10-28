package game.items.edibles;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.UserAttributes;

/**
 * A class representing a Shadowtree Fragment, an edible item that increases various attributes of an actor when consumed.
 * <p>
 * This item has a single charge. Upon consumption, it increases the actor's maximum health, mana, and strength attributes,
 * and then removes itself from the actor's inventory.
 * </p>
 *
 * Created by:
 * @author [Your Name]
 * Modified by:
 *
 */
public class ShadowTreeFragment extends EdibleItem {

    /**
     * Constructor for the Shadowtree Fragment.
     * Initializes the item with a name, display character, initial number of charges, and consumption text.
     */

    public ShadowTreeFragment() {
        super("Shadowtree Fragment", 'e', 1, "feels stronger");
    }

    /**
     * Consumes the Shadowtree Fragment, increasing various attributes of the actor.
     * <p>
     * This method first invokes the `consume` method from the superclass to handle charge depletion and return the
     * appropriate message if the item is empty. It then increases the actor's maximum health, mana, and strength attributes.
     * After consuming the item, it removes itself from the actor's inventory.
     * </p>
     *
     * @param actor The actor consuming the Shadowtree Fragment.
     * @return a message indicating the result of the consumption, including the attribute increases.
     */

    @Override
    public String consume(Actor actor) {
        // Get the consumption message from the superclass method
        String msg = super.consume(actor);

        // Increase the actor's maximum health by 50
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 50);

        // Increase the actor's maximum mana by 25
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 25);

        // Increase the actor's strength attribute by 5
        actor.modifyAttributeMaximum(UserAttributes.STRENGTH, ActorAttributeOperations.INCREASE, 5);

        // Remove the item from the actor's inventory after consumption
        actor.removeItemFromInventory(this);

        // Return the consumption message with the effects applied
        return msg;
    }
}

package game.items.edibles;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * An abstract class representing an edible item in the game.
 * <p>
 * Edible items can be consumed by actors, which reduces the number of charges and
 * provides a description of the consumption. This class is designed to be extended
 * by specific types of edible items.
 * </p>
 *
 * Created by:
 * @author [Your Name]
 * Modified by:
 *
 */

public abstract class EdibleItem extends Item {

    /**
     * The name of the edible item.
     */

    protected String name;

    /**
     * The number of remaining charges of the edible item.
     * Each consumption of the item decreases the number of charges by 1.
     */

    private int charges;

    /**
     * The text to be displayed when the item is consumed.
     */

    private String text;

    /**
     * Constructor to initialize an edible item with its name, display character,
     * number of charges, and consumption text.
     *
     * @param name        The name of the edible item.
     * @param displayChar The character used to represent the item on the map.
     * @param charges     The initial number of charges of the item.
     * @param text        The text to be displayed when the item is consumed.
     */

    public EdibleItem(String name, char displayChar, int charges, String text) {
        super(name, displayChar, true);
        this.name = name;
        this.charges = charges;
        this.text = text;
    }

    /**
     * Consumes the edible item, reducing its number of charges and returning a description
     * of the consumption.
     * <p>
     * If the item has no charges left, a message indicating that the item is empty is returned.
     * Otherwise, the number of charges is decreased by 1, and a message describing the consumption
     * is returned.
     * </p>
     *
     * @param actor The actor consuming the item.
     * @return a description of the consumption result.
     */

    public String consume(Actor actor) {
        if (charges <= 0) {
            return name + " is empty";
        } else {
            charges -= 1;
            return String.format("%s consumed by %s. %s %s.%n", name, actor, actor, text);
        }
    }

    /**
     * Returns the list of actions that can be performed with this item.
     * <p>
     * This includes the consume action for the item.
     * </p>
     *
     * @param owner The actor who owns this item.
     * @return an ActionList containing the actions that can be performed with this item.
     */

    @Override
    public ActionList allowableActions(Actor owner) {
        // Get the default list of actions
        ActionList actions = super.allowableActions(owner);

        // Add the consume action to the list of allowable actions
        actions.add(new ConsumeAction(this));

        return actions;
    }
}
